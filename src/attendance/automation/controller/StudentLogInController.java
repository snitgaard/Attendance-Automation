/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author CSnit
 */
public class StudentLogInController implements Initializable
{

    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXTextField passwordField;
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
    private void handleLogInButton(ActionEvent event)
    {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        if(username.equalsIgnoreCase(StudentUsername))
        {
            System.out.println("Logged in as student");
        }
    }

    private void loginSetup()
    {
    }

}
