/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.gui.Model.StudentAttendanceModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Troels Klein
 */
public class TeacherClassController implements Initializable
{

    @FXML
    private ImageView btn_close;
    @FXML
    private AnchorPane ancMain;
    @FXML
    private Label nameFour;
    @FXML
    private Button StudentFour;
    @FXML
    private Label nameTwo;
    @FXML
    private Button StudentTwo;
    @FXML
    private Label nameOne;
    @FXML
    private Button studentOne;
    @FXML
    private Label nameThree;
    @FXML
    private Button StudentThree;

    StudentAttendanceModel model = new StudentAttendanceModel();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        studentOverview();
    }

    @FXML
    private void close_app(MouseEvent event)
    {
        System.exit(0);
    }

    @FXML
    private void minimize_app(MouseEvent event)
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void showTeacherMain() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/TeacherMain.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        Scene currentScene = ancMain.getScene();
        currentScene.setRoot(root);
    }

    @FXML
    private void showTeacherStudent() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/TeacherStudent.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        Scene currentScene = ancMain.getScene();
        currentScene.setRoot(root);
    }

    private void studentOverview()
    {
        nameOne.setText(model.getAllData().get(0).toString());
        nameTwo.setText(model.getAllData().get(1).toString());
        nameThree.setText(model.getAllData().get(2).toString());
        nameFour.setText(model.getAllData().get(3).toString());
    }
}
