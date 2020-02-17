/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author CSnit
 */
public class StudentAttendanceOverviewController implements Initializable
{

    @FXML
    private LineChart<?, ?> attendanceChart;
    @FXML
    private Label studentName;
    @FXML
    private Label studentEducation;
    @FXML
    private Label studentLowestDay;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
}
