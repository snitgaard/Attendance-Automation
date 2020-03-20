/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import attendance.automation.BE.Student;
import attendance.automation.gui.Model.StudentModel;
import attendance.automation.gui.Model.TeacherModel;
import java.sql.SQLException;

/**
 * FXML Controller class
 *
 * @author CSnit
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
    private String StudentUsername = "student";
    private String StudentPassword = "32ade5e7c36fa329ea39dbc352743db40da5aa7460ec55f95b999d6371ad20170094d88d9296643f192e9d5433b8d6d817d6777632e556e96e58f741dc5b3550";
    private String TeacherUsername = "teacher";
    private String TeacherPassword = "50ecc45020be014e68d714cd076007e84a9621d9a5e589a916e45273014830b399d143a57f525554bfe9e751d97fe0fa884dbdea7b07721723b4eff39e9d28ad";
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView btn_close;
    
    private StudentModel studentModel; 
    private TeacherModel teacherModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  
    {
        studentModel = new StudentModel();
        teacherModel = new TeacherModel();
    }
    
    public static String encryptThisString(String input) 
    { 
        try { 
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
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
  
            // return the HashText 
            return hashtext; 
        } 
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    }     
    

    @FXML
    private void handleLogInButton(ActionEvent event) throws IOException, SQLException
    {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        if (studentModel.checkLoginCredentials(username, password) == true)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/StudentAttendance.fxml"));
            redirectToStage(fxmlLoader);
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.close();

        } else if (teacherModel.checkLoginCredentials(username, password))
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/TeacherMain.fxml"));
            redirectToStage(fxmlLoader);
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.close();
        } else {
            Border warning = new Border(new BorderStroke(javafx.scene.paint.Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2)));
            usernameField.setBorder(warning);
            passwordField.setBorder(warning);
        }
    }

    private void redirectToStage(FXMLLoader fxmlLoader) throws IOException
    {
            Parent root = (Parent) fxmlLoader.load();
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

    @FXML
    private void close_app(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void minimize_app(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    
    
}
