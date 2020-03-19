/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.DAL.DalException;
import attendance.automation.gui.Model.CourseModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

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
        
        if (course.length() == 0 && weekDay.length() == 0)
        {
            Border warning = new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2)));

            txt_courseName.setBorder(warning);
            txt_weekDay.setBorder(warning);
        } else
        {
            courseModel.createCourses(course, weekDay, courseLength, selectClass);
        }
        
        
        
        
    }

    @FXML
    private void cb_courseLength(ActionEvent event)
    {
//        cb_courseLength.setItems(courseModel.getAllCourses());
    }

    @FXML
    private void cb_selectClass(ActionEvent event) {
    }
    
}
