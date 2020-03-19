/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.BE.Student;
import attendance.automation.gui.Model.StudentModel;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author The Best Group
 */
public class StudentAttendanceController implements Initializable {

    StudentModel studentModel;
    Student student;
    private String IpAddress;

    @FXML
    private ImageView btn_close;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private JFXProgressBar progressBar;
    @FXML
    private Label studentAttendancePercentage;
    @FXML
    private JFXToggleButton attendanceButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            checker();
        } catch (UnknownHostException ex) {
            Logger.getLogger(StudentAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleOverview(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/StudentAttendanceOverview.fxml"));

        Parent root = (Parent) fxmlLoader.load();
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
                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String strDate = dateFormat.format(date);
                System.out.println("Converted String: " + strDate);

                int id = student.getId();
                this.studentModel.updateAttendance(strDate, id);
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(StudentAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (checker() == false) {

                attendanceButton.setSelected(false);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setIconified(true);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Cannot submit attendance");
                alert.setHeaderText(null);
                alert.setContentText("Current location does not match with the school");

                alert.showAndWait();
            }

        } catch (UnknownHostException ex) {
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
//                InetAddress addr = adresse.();
        System.out.println("Location does not match the school");
        return false;
    }

}
