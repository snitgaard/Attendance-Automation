/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.BE.Course;
import attendance.automation.DAL.DalException;
import attendance.automation.gui.Model.CourseModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author CSnit
 */
public class CourseWindowController implements Initializable
{

    
    CourseModel courseModel;

    @FXML
    private TextField txt_courseName;
    @FXML
    private TextField txt_weekDay;
    @FXML
    private ComboBox<?> cb_courseLength;
    @FXML
    private ComboBox<?> cb_selectClass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    }    

    @FXML
    private void createCourse(ActionEvent event) throws DalException
    {
        String course = txt_courseName.getText();
        String weekDay = txt_weekDay.getText();
        int courseLength = (int) cb_courseLength.getSelectionModel().getSelectedItem();
        String selectClass = (String) cb_selectClass.getSelectionModel().getSelectedItem();
        
        
        courseModel.createCourses(course, weekDay, courseLength, selectClass);
        
        
        
    }

    @FXML
    private void courseTime(ActionEvent event)
    {
        courseTime.setItems(courseModel.getAllCourses());
    }

    @FXML
    private void cb_courseLength(ActionEvent event)
    {
    }

    @FXML
    private void cb_selectClass(ActionEvent event) {
    }
    
}
