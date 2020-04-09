/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.BE.Course;
import attendance.automation.BE.Student;
import attendance.automation.gui.Model.*;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;

import java.io.IOException;
import java.net.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author The Cowboys
 */
public class StudentAttendanceController implements Initializable
{

    public static final long MSEC_SINCE_EPOCH = System.currentTimeMillis();
    private StudentModel studentModel;
    private LoginController controller;
    private Student selectedStudent;
    private String IpAddress;
    private StudentCourseModel studentCourseModel;
    private Course selectedCourse;
    private CourseModel courseModel;
    @FXML
    private ImageView btn_close;
    private Course course;
    private double xOffset = 0;
    private double yOffset = 0;
    private String courseDate;
    private int attendance = 0;
    @FXML
    private JFXProgressBar progressBar;
    @FXML
    private Label studentAttendancePercentage;
    @FXML
    private Label nameTag;
    @FXML
    private JFXDatePicker calendar;
    @FXML
    private AnchorPane studentPane;

    private ObservableList<JFXToggleButton> attButtons = FXCollections.observableArrayList();

    @FXML
    private JFXListView<JFXToggleButton> listView;
    @FXML
    private Label studentClassName;
    private JFXToggleButton attButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        courseModel = new CourseModel();
        studentCourseModel = new StudentCourseModel();

    }

    //This method makes sure that we get the correct data object when logging in as a student
    public void ApplyImportantData(StudentModel studentModel, LoginController controller, Student selectedStudent) throws SQLException
    {
        this.studentModel = studentModel;
        this.controller = controller;
        this.selectedStudent = selectedStudent;

        nameTag.setText(selectedStudent.getName());

        studentClassName.setText(selectedStudent.getClassId() + "");

        calendar.setValue(LocalDate.now());

        getAttendanceFromCourse();

    }

    public void getAttendanceFromCourse() throws SQLException
    {
        List<Course> courseIds = courseModel.getAllCourses();
        List<Course> result = new ArrayList<>();

        LocalDate todaysDate = LocalDate.now();

        for (Course courses : courseIds)
        {
            if (courses.getCourseDate() != null)
            {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localCourseDate = LocalDate.parse(courses.getCourseDate(), formatter);

                if (localCourseDate.isBefore(todaysDate) || localCourseDate.equals(todaysDate))
                {
                    result.add(courses);
                }

            }
        }
        int studentId = selectedStudent.getId();
        double attendedCounter = 0;

        for (int i = 0; i < result.size(); i++)
        {
            if (studentCourseModel.getAllCourseIds(result.get(i).getCourseId(), studentId) == 1)
            {
                attendedCounter++;
            }

        }

        double realAttendance = attendedCounter / result.size();
        double attendancePercentage = realAttendance * 100;
        String roundedAttendance = String.format("%.2f", attendancePercentage);
        studentModel.updateAttendance(realAttendance * 100, studentId);
        progressBar.setProgress(realAttendance);
        studentAttendancePercentage.setText(roundedAttendance + " %");

    }

    // This method opens up a new stage with the Student Attendance Overview.
    @FXML
    private void handleOverview(ActionEvent event) throws IOException, SQLException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/StudentAttendanceOverview.fxml"));
        Parent root = fxmlLoader.load();
        StudentAttendanceOverviewController studentcontroller = fxmlLoader.getController();
        // Here the edit controller is given important data objects,
        // This secures that it is the correct ones we are working with.
        studentcontroller.ApplyImportantData(studentModel, this, selectedStudent);
        StudentAttendanceOverviewController c = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        root.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        stage.setAlwaysOnTop(true);
        stage.setTitle("Student Attendance Overview");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();

    }

    // This method closes the program.
    @FXML
    private void close_app(MouseEvent event)
    {
        System.exit(0);
    }

    // This method minimizes the stage.
    @FXML
    private void minimize_app(MouseEvent event)
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    /*
    * This method checks the date, the students class and which courses that specific class, have that specific day
    * For each course found within the day, a JFXToggleButton is created.
    */
    public void generateAttendanceButtons() throws SQLException
    {

        String classId = studentClassName.getText();
        int realClassId = Integer.parseInt(classId);
        for (int i = 0; i < courseModel.getAllCourseDates(calendar.getValue().toString(), realClassId); i++)
        {

            attButton = new JFXToggleButton();
            attButtons.add(attButton);
            attButton.setUserData(courseModel.getStartEndTime(calendar.getValue().toString(), realClassId).get(i));
            attButton.setText(attButton.getUserData() + "");

            checkDate();
        }

        Comparator<JFXToggleButton> byUserData = (JFXToggleButton b1, JFXToggleButton b2) -> b1.getUserData().toString().compareTo(b2.getUserData().toString());
        Collections.sort(attButtons, byUserData);

        listView.setItems(attButtons);

        if (attButtons.isEmpty())
        {
            listView.setVisible(false);
        }

        listView.setPrefHeight(attButtons.size() * 62);

        listView.setItems(attButtons);

    }

    // This method checks if the users IP Adress is equal to that of EASV.
    private boolean checker() throws UnknownHostException
    {
        IpAddress = InetAddress.getLocalHost().getHostAddress();

        String[] adr = IpAddress.split("\\.");
        for (int i = 0; i < adr.length - 1; i++)
        {
            if (adr[0].equals("172") && adr[1].equals("17") && adr[2].equals("176"))
            {
                return true;
            }
        }
        return false;
    }

    @FXML
    private void changeDate(ActionEvent event) throws SQLException
    {

        attButtons.clear();
        listView.getItems().clear();
        generateAttendanceButtons();

        if (!attButtons.isEmpty())
        {
            listView.setVisible(true);
        }
    }

    private void checkDate() throws SQLException
    {
        String studentId = studentClassName.getText();
        int realStudentId = Integer.parseInt(studentId);
        try
        {
            String time1 = LocalTime.now().toString();
            Date date1 = new SimpleDateFormat("HH:mm").parse(time1);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(date1);
            calendar1.add(Calendar.DATE, 1);

            Date date2 = new SimpleDateFormat("HH:mm").parse(attButton.getUserData().toString().substring(0, 5).trim());
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(date2);
            calendar2.add(Calendar.DATE, 1);

            Date date3 = new SimpleDateFormat("HH:mm").parse(attButton.getUserData().toString().substring(8, 13).trim());
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(date3);
            calendar3.add(Calendar.DATE, 1);

            Date x = calendar1.getTime();

            if (calendar.getValue().toString().equals(LocalDate.now().toString()))
            {
                try
                {
                    if (checker() == true)
                    {
                        if (x.after(calendar2.getTime()) && x.before(calendar3.getTime()))
                        {
                            attButton.setSelected(true);
                            attButton.setDisable(true);

                            studentCourseModel.updateAttendance(1, studentCourseModel.getStudentId(nameTag.getText()), studentCourseModel.getCourseId(calendar.getValue().toString(), realStudentId, attButton.getUserData().toString().substring(0, 5).trim()));

                        } else if (studentCourseModel.getAttendance(studentCourseModel.getStudentId(nameTag.getText()), studentCourseModel.getCourseId(calendar.getValue().toString(), realStudentId, attButton.getUserData().toString().substring(0, 5).trim())) == 1)
                        {
                            attButton.setSelected(true);
                            attButton.setDisable(true);
                        } else
                        {
                            attButton.setSelected(false);
                            attButton.setDisable(true);

                        }
                    } else
                    {
                        Stage onTop = (Stage) nameTag.getScene().getWindow();
                        onTop.setAlwaysOnTop(true);
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Cannot submit attendance");
                        alert.setHeaderText(null);
                        alert.initOwner(onTop);
                        alert.setContentText("Current location does not match with the school");
                        alert.showAndWait();
                    }

                } catch (UnknownHostException ex)
                {
                    Logger.getLogger(StudentAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else
            {
                for (JFXToggleButton attBut : attButtons)
                {
                    if (studentCourseModel.getAttendance(studentCourseModel.getStudentId(nameTag.getText()), studentCourseModel.getCourseId(calendar.getValue().toString(), realStudentId, attBut.getUserData().toString().substring(0, 5).trim())) == 1)
                    {
                        attBut.setSelected(true);
                    } else
                    {
                        attBut.setSelected(false);
                    }

                }

                attButton.setDisable(true);
            }

        } catch (ParseException ex)
        {
            Logger.getLogger(StudentAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
