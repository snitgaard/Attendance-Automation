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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Troels Klein
 */
public class TeacherMainController implements Initializable
{

    @FXML
    private ImageView btn_close;
    @FXML
    private Button viewAttendance;
    private AnchorPane ancMain;

    StudentAttendanceModel model = new StudentAttendanceModel();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
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
    private void handleAttendance(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/TeacherCourse.fxml"));

        Parent root = (Parent) fxmlLoader.load();
        Object c = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
//            stage.initStyle(StageStyle.DECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setAlwaysOnTop(true);
        stage.setTitle("Login to EASV Student Registration");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
        System.out.println(model.getAllData().toString());
    }

    
    @FXML
    private void showTeacherCourse() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/TeacherCourse.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        Scene currentScene = ancMain.getScene(); 
        currentScene.setRoot(root);     
    }

    @FXML
    private void showTeacherClass() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/TeacherClass.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        Scene currentScene = ancMain.getScene(); 
        currentScene.setRoot(root);     
    }
}
