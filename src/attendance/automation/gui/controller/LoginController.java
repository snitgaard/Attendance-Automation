/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.BE.Student;
import attendance.automation.BE.Teacher;
import attendance.automation.gui.Model.*;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author The Cowboys
 */
public class LoginController implements Initializable
{

    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXButton btnLogin;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView btn_close;

    private StudentModel studentModel;
    private TeacherModel teacherModel;
    private CourseModel courseModel;

    public static String encryptThisString(String input)
    {
        try
        {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32)
            {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        studentModel = new StudentModel();
        teacherModel = new TeacherModel();
    }

    /*
    * This method checks what is written in the username and password fields. If they fit with a student login
    * It will try to load the Student Main Menu as the correct student logged in as
    * If not, it checks the same for teacher.
    * else, it will tell you that something is wrong with the username and/or password
    */
    @FXML
    private void handleLogInButton(ActionEvent event) throws IOException, SQLException
    {
        String username = usernameField.getText();
        String password = encryptThisString(passwordField.getText());

        if (studentModel.checkLoginCredentials(username, password))
        {
            Student selectedStudent = studentModel.getSpecificStudent(username);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/StudentAttendance.fxml"));
            redirectToStage(fxmlLoader);
            StudentAttendanceController studentcontroller = fxmlLoader.getController();
            // Here the StudentAttendanceController is given important data objects,
            // This secures that it is the correct ones we are working with.
            studentcontroller.ApplyImportantData(studentModel, this, selectedStudent);

            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.close();

        } else if (teacherModel.checkLoginCredentials(username, password))
        {
            Teacher selectedTeacher = teacherModel.getSpecificTeacher(username);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/TeacherMain.fxml"));
            redirectToStage(fxmlLoader);
            TeacherMainController teachercontroller = fxmlLoader.getController();
            // Here the TeacherMainController is given important data objects,
            // This secures that it is the correct ones we are working with.
            teachercontroller.ApplyImportantData(teacherModel, this, selectedTeacher);
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.close();
        } else
        {
            Border warning = new Border(new BorderStroke(javafx.scene.paint.Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2)));
            usernameField.setBorder(warning);
            passwordField.setBorder(warning);
        }
    }

    /*
    * This method ensures proper creating of a new stage.
    * It is called in the checkLoginCredentials method to open up a new window of teachermain or studentattendance
    */
    private void redirectToStage(FXMLLoader fxmlLoader) throws IOException
    {
        Parent root = fxmlLoader.load();
        Object c = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
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
    }

    // closes the stage, since this is the very first stage, it will close the program completely.
    @FXML
    private void close_app(MouseEvent event)
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    // minimizes the current stage
    @FXML
    private void minimize_app(MouseEvent event)
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }


}
