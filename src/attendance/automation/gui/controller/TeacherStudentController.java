/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Troels Klein
 */
public class TeacherStudentController implements Initializable {

    @FXML
    private AnchorPane ancMain;
    @FXML
    private ImageView btn_close;
    @FXML
    private LineChart<String, Number> attendanceChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buildBarChart();
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
    private void showTeacherMain() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/TeacherMain.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        Scene currentScene = ancMain.getScene(); 
        currentScene.setRoot(root);     
    }
    
    private void buildBarChart()
    {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Days");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Attendance");

        XYChart.Series data = new XYChart.Series();
        data.setName("Attendance Chart");

        //Provide data
        data.getData().add(new XYChart.Data("Monday", 10));
        data.getData().add(new XYChart.Data("Tuesday", 67));
        data.getData().add(new XYChart.Data("Wednesday", 75));
        data.getData().add(new XYChart.Data("Thursday", 100));
        data.getData().add(new XYChart.Data("Friday", 36));
        
        attendanceChart.setMinHeight(300);

        attendanceChart.getData().add(data);
    }
}
