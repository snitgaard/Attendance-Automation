/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.gui.Model.StudentAttendanceModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Troels Klein
 */
public class TeacherCourseController implements Initializable
{

    @FXML
    private Label nameFour;
    @FXML
    private Label nameTwo;
    @FXML
    private Label nameOne;
    @FXML
    private Label nameThree;
    
    private StudentAttendanceModel model = new StudentAttendanceModel();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        studentOverview();
    }

    private void studentOverview()
    {
        nameOne.setText(model.getAllData().get(0).toString());
        nameTwo.setText(model.getAllData().get(1).toString());
        nameThree.setText(model.getAllData().get(2).toString());
        nameFour.setText(model.getAllData().get(3).toString());
    }

}
