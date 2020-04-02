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
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.time.LocalTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.SortedList;

/**
 * FXML Controller class
 *
 * @author The Best Group
 */
public class StudentAttendanceController implements Initializable {

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
    public void initialize(URL url, ResourceBundle rb) {

        courseModel = new CourseModel();
        studentCourseModel = new StudentCourseModel();

        //courseModel.getAllCourseDates(courseDate);
    }

    //This method makes sure that we get the correct data object when logging in as a student
    public void ApplyImportantData(StudentModel studentModel, LoginController controller, Student selectedStudent) throws SQLException {
        this.studentModel = studentModel;
        this.controller = controller;
        this.selectedStudent = selectedStudent;

        nameTag.setText(selectedStudent.getName());
        progressBar.setProgress(selectedStudent.getAttendance() / 100);
        studentAttendancePercentage.setText(selectedStudent.getAttendance() + " %");
        studentClassName.setText(selectedStudent.getClassId() + "");

        calendar.setValue(LocalDate.now());
        //generateAttendanceButtons();
        System.out.println("Inde i studentAtteandaceController" + this.selectedStudent);

    }

    @FXML
    private void handleOverview(ActionEvent event) throws IOException, SQLException {
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
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
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

    @FXML
    private void close_app(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void minimize_app(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    public void generateAttendanceButtons() throws SQLException {

        String studentId = studentClassName.getText();
        int realStudentId = Integer.parseInt(studentId);
        for (int i = 0; i < courseModel.getAllCourseDates(calendar.getValue().toString(), realStudentId); i++) {

            attButton = new JFXToggleButton();
            attButtons.add(attButton);
            attButton.setUserData(courseModel.getStartEndTime(calendar.getValue().toString(), realStudentId).get(i));
            System.out.println("det fucking her" + attButton.getUserData());
            attButton.setText(attButton.getUserData() + "");
            
            checkDate();
        }

        Comparator<JFXToggleButton> byUserData = (JFXToggleButton b1, JFXToggleButton b2) -> b1.getUserData().toString().compareTo(b2.getUserData().toString());
        Collections.sort(attButtons, byUserData);

        listView.setItems(attButtons);

        if (attButtons.isEmpty()) {
            listView.setVisible(false);
        }

        listView.setPrefHeight(attButtons.size() * 62);

        listView.setItems(attButtons);

    }

    private boolean checker() throws UnknownHostException {
        IpAddress = InetAddress.getLocalHost().getHostAddress();
        System.out.println(IpAddress);

        String[] adr = IpAddress.split("\\.");
        for (int i = 0; i < adr.length - 1; i++) {
            if (adr[0].equals("172") && adr[1].equals("17") && adr[2].equals("176")) {
                System.out.println("Location matches the school");
                return true;
            }
        }
        System.out.println("Location does not match the school");
        return false;
    }

//    void ApplyImportantData(StudentModel studentModel, LoginController aThis, Student selectedStudent) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    @FXML
    private void changeDate(ActionEvent event) throws SQLException {

        attButtons.clear();
        listView.getItems().clear();
        generateAttendanceButtons();

        if (!attButtons.isEmpty()) {
            listView.setVisible(true);
        }
    }

    private void checkDate() throws SQLException {
        String studentId = studentClassName.getText();
        int realStudentId = Integer.parseInt(studentId);
        try {
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

            if (calendar.getValue().toString().equals(LocalDate.now().toString())) {
                try {
                    if (checker() == true) {
                        if (x.after(calendar2.getTime()) && x.before(calendar3.getTime())) {
                            attButton.setSelected(true);
                            attButton.setDisable(true);

                            studentCourseModel.updateAttendance(1, studentCourseModel.getStudentId(nameTag.getText()), studentCourseModel.getCourseId(calendar.getValue().toString(), realStudentId, attButton.getUserData().toString().substring(0, 5).trim()));
                            System.out.println("TEST TRUE");
                        
                        } else {
                            attButton.setSelected(false);
                            attButton.setDisable(true);
                            System.out.println("TEST FALSE");

                        }
                    } else {
                        Stage onTop = (Stage) nameTag.getScene().getWindow();
                        onTop.setAlwaysOnTop(true);
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Cannot submit attendance");
                        alert.setHeaderText(null);
                        alert.initOwner(onTop);
                        alert.setContentText("Current location does not match with the school");
                        alert.showAndWait();
                    }

                } catch (UnknownHostException ex) {
                    Logger.getLogger(StudentAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                for (JFXToggleButton attBut : attButtons) {
                    if (studentCourseModel.getAttendance(studentCourseModel.getStudentId(nameTag.getText()), studentCourseModel.getCourseId(calendar.getValue().toString(), realStudentId, attBut.getUserData().toString().substring(0, 5).trim())) == 1)
                    {
                        attBut.setSelected(true);
                    }
                    else
                    {
                        attBut.setSelected(false);
                    }
        
                }
                
                attButton.setDisable(true);
            }

        } catch (ParseException ex) {
            Logger.getLogger(StudentAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
