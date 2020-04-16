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
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private Model model;
    
    /**
     * Algorithm "SHA-512" is called through getInstance. Digest method then gets called
     * to calculate the message diest of the input string and returned as an array of byte.
     * The byte gets converted into BigInteger, and the message is converted into a
     * hex value. Lastly it adds preceding 0's to make the hashed text 32-bit 
     * @param input
     * @return the hashtext
     */
    public static String encryptThisString(String input)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32)
            {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
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
        try
        {
            model = new Model();
        } catch (IOException ex)
        {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
    * This method checks what is written in the username and password fields. If they fit with a student login
    * It will try to load the Student Main Menu as the correct student logged in as
    * If not, it checks the same for teacher.
    * else, it will tell you that something is wrong with the username and/or password.
    * Hashes the password using encryptThisString method.
     * @param event
     * @throws IOException
     * @throws SQLException
     * @throws ModelException 
     */
    @FXML
    private void handleLogInButton(ActionEvent event) throws IOException, SQLException, ModelException
    {
        String username = usernameField.getText();
        String password = encryptThisString(passwordField.getText());

        if (model.checkStudentCredentials(username, password))
        {
            Student selectedStudent = model.getSpecificStudent(username);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/StudentAttendance.fxml"));
            redirectToStage(fxmlLoader);
            StudentAttendanceController studentcontroller = fxmlLoader.getController();
            // Here the StudentAttendanceController is given important data objects,
            // This secures that it is the correct ones we are working with.
            studentcontroller.ApplyImportantData(model, this, selectedStudent);

            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.close();

        } else if (model.checkTeacherCredentials(username, password))
        {
            Teacher selectedTeacher = model.getSpecificTeacher(username);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/TeacherMain.fxml"));
            redirectToStage(fxmlLoader);
            TeacherMainController teachercontroller = fxmlLoader.getController();
            // Here the TeacherMainController is given important data objects,
            // This secures that it is the correct ones we are working with.
            teachercontroller.ApplyImportantData(model, this, selectedTeacher);
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

    /**
     * Closes the stage, since this is the very first stage, it will close the program completely.
     * @param event 
     */
    @FXML
    private void close_app(MouseEvent event)
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    
   /**
    * Minimizes the stage
    * @param event 
    */
    @FXML
    private void minimize_app(MouseEvent event)
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }


}
