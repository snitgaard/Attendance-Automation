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

    @FXML
    private TextField courseName;
    
    CourseModel courseModel;
    @FXML
    private TextField weekDay;
    @FXML
    private ComboBox<Course> courseTime;
    @FXML
    private ComboBox<?> selectClass;

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
        String course = courseName.getText();
        
        
        
    }

    @FXML
    private void courseTime(ActionEvent event)
    {
        courseTime.setItems(courseModel.getAllCourses());
    }

    @FXML
    private void selectClass(ActionEvent event)
    {
    }
    
}
