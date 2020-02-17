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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    private String StudentUsername = "student";
    private String StudentPassword = "student";
    private String TeacherUsername = "teacher";
    private String TeacherPassword = "teacher";
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane gridPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //TODO
    }

    @FXML
    private void handleLogInButton(ActionEvent event) throws IOException
    {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equalsIgnoreCase(StudentUsername) && password.equalsIgnoreCase(StudentPassword))
        {
            System.out.println("Logged in as student");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/StudentAttendance.fxml"));

            Parent root = (Parent) fxmlLoader.load();
            StudentAttendanceController c = fxmlLoader.getController();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setAlwaysOnTop(true);
            stage.setTitle("Add Movie");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        } else if (username.equalsIgnoreCase(TeacherUsername) && password.equalsIgnoreCase(TeacherPassword))
        {
            System.out.println("Logged in as teacher");
        }
    }

}
