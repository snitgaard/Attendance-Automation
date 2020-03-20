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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

    private CourseModel courseModel;
    Course course;

    @FXML
    private TextField txt_courseName;
    @FXML
    private TextField txt_weekDay;
    @FXML
    private ComboBox<Course> cb_selectClass;
    @FXML
    private ImageView btn_close;
    @FXML
    private TextField courseLengthField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        courseModel = new CourseModel();
        cb_selectClass.setItems(courseModel.getAllCourses());
    }

    @FXML
    private void createCourse(ActionEvent event) throws DalException
    {
        String course = txt_courseName.getText();
        String weekDay = txt_weekDay.getText();
        String courseLength = courseLengthField.getText();
        Course selectClass = (Course) cb_selectClass.getSelectionModel().getSelectedItem();

        if (course.length() == 0 && weekDay.length() == 0)
        {
            Border warning = new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2)));

            txt_courseName.setBorder(warning);
            txt_weekDay.setBorder(warning);
        } else
        {
            courseModel.createCourses(course, weekDay, courseLength, selectClass + "");
        }

    }

    @FXML
    private void cb_selectClass(ActionEvent event)
    {

    }

    @FXML
    private void close_app(MouseEvent event)
    {
    }

    @FXML
    private void minimize_app(MouseEvent event)
    {
    }

}
