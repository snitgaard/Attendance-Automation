/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.BE.Course;
import attendance.automation.BE.Student;
import attendance.automation.gui.Model.CourseModel;
import attendance.automation.gui.Model.StudentCourseModel;
import attendance.automation.gui.Model.StudentModel;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.print.PrintException;

/**
 * FXML Controller class
 *
 * @author The Best Group
 */
public class StudentAttendanceController implements Initializable {

    private StudentModel studentModel;
    private LoginController controller;
    private Student selectedStudent;
    private String IpAddress;
    private StudentCourseModel studentCourseModel;
    private Course selectedCourse;
    private CourseModel courseModel;

    @FXML
    private ImageView btn_close;
    
    private double xOffset = 0;
    private double yOffset = 0;
    private String courseDate;
    private int attendance = 0;

    @FXML
    private JFXProgressBar progressBar;
    @FXML
    private Label studentAttendancePercentage;
    @FXML
    private JFXToggleButton attendanceButton;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            courseModel = new CourseModel();

            checker();
            
            

            //courseModel.getAllCourseDates(courseDate);
        } catch (UnknownHostException ex) {
            Logger.getLogger(StudentAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    //This method makes sure that we get the correct data object when logging in as a student
    public void ApplyImportantData(StudentModel studentModel, LoginController controller, Student selectedStudent) throws SQLException {
        this.studentModel = studentModel;
        this.controller = controller;
        this.selectedStudent = selectedStudent;

        nameTag.setText(selectedStudent.getName());
        progressBar.setProgress(selectedStudent.getAttendance() / 100);
        studentAttendancePercentage.setText(selectedStudent.getAttendance() + " %");
        studentClassName.setText(selectedStudent.getStudentClass());
        calendar.setValue(LocalDate.now());
            generateAttendanceButtons();
        
        System.out.println("Inde i studentAtteandaceController" + this.selectedStudent);

    }
    
  

    public void generateAttendanceButtons() throws SQLException 
    {
        for (int i = 0; i < courseModel.getAllCourseDates(calendar.getValue().toString(), studentClassName.getText()); i++)
        {
            JFXToggleButton attButton = new JFXToggleButton();
            attButtons.add(attButton);
        }
        
        listView.setItems(attButtons);
        if (attButtons.isEmpty())
        {
            listView.setVisible(false);
        }
        
        listView.setPrefHeight(attButtons.size() * 62);

    }

    @FXML
    private void handleOverview(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/StudentAttendanceOverview.fxml"));
        Parent root = (Parent) fxmlLoader.load();
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

    @FXML
    private void submitAttendance(ActionEvent event) throws SQLException {
        try {
            if (checker() == true) {
                /*Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String strDate = dateFormat.format(date);
                System.out.println("Converted String: " + strDate);*/

                attendance = 1;
                int studentId = selectedStudent.getId();
                int courseId = selectedCourse.getCourseId(); //LAV METODE TIL DETTE I SCENEBUILDER UD FRA KNAPPER ELLER NOGET

                this.studentCourseModel.updateAttendance(attendance, studentId, courseId);
            }
            else if (checker() == false)
            {
                attendance = 0;
                int studentId = selectedStudent.getId();
                int courseId = selectedCourse.getCourseId();
                
                this.studentCourseModel.updateAttendance(attendance, studentId, courseId);
            }
            
            
        } catch (UnknownHostException ex) {
            System.out.println("Smth went wrong in the submitAttendance 1st catch");
            Logger.getLogger(StudentAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (checker() == false) {

                attendanceButton.setSelected(false);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setAlwaysOnTop(true);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Cannot submit attendance");
                alert.setHeaderText(null);
                alert.setContentText("Current location does not match with the school");
                alert.initOwner(stage);
                alert.showAndWait();
                stage.setAlwaysOnTop(false);
            }

        } catch (UnknownHostException ex) {
            System.out.println("Smth went wrong in the submitAttendance 2d catch");
            Logger.getLogger(StudentAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean checker() throws UnknownHostException {
        IpAddress = InetAddress.getLocalHost().getHostAddress();
        System.out.println(IpAddress);

        String[] adr = IpAddress.split("\\.");
        for (int i = 0; i < adr.length - 1; i++) {
            if (adr[0].equals("10") && adr[1].equals("176") && adr[2].equals("161")) {
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
}
