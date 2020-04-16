/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.BE.Course;
import attendance.automation.BE.Student;
import attendance.automation.DAL.DalException;
import attendance.automation.gui.Model.Model;
import attendance.automation.gui.Model.ModelException;
import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author The Cowboys
 */
public class CourseWindowController implements Initializable
{

    Course course;
    private Model model;
    private int NOT_ATTENDED_YET = 0;

    @FXML
    private TextField txt_courseName;
    @FXML
    private TextField txt_weekDay;
    @FXML
    private ComboBox<String> cb_selectClass;
    @FXML
    private ImageView btn_close;
    @FXML
    private TextField txt_startTime;
    @FXML
    private TextField txt_endTime;
    @FXML
    private JFXDatePicker datePicker;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            model = new Model();
            cb_selectClass.setItems(model.getAllClasses());

        } catch (IOException ex)
        {
            Logger.getLogger(CourseWindowController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ModelException ex)
        {
            Logger.getLogger(CourseWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates a course that gets added to the database. Gives an error if
     * a course name hasnt been put. Automatically sets attendance to be
     * "NOT_ATTENDED_YET" which corresponds to a 0.
     *
     * @param event
     * @throws DalException
     * @throws SQLException
     * @throws ModelException
     */
    @FXML
    private void createCourse(ActionEvent event) throws DalException, SQLException, ModelException
    {
        String course = txt_courseName.getText();
        String weekDay = txt_weekDay.getText();
        String startTime = txt_startTime.getText();
        String endTime = txt_endTime.getText();
        String className = cb_selectClass.getSelectionModel().getSelectedItem();
        String courseDate = datePicker.getValue().toString();

        if (course.length() == 0 && weekDay.length() == 0)
        {
            Border warning = new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2)));

            txt_courseName.setBorder(warning);
            txt_weekDay.setBorder(warning);
        } else
        {
            int classId = model.getClassId(className);
            model.createCourses(course, weekDay, startTime, endTime, classId, courseDate);

            for (Student student : model.getStudentClass(model.getClassId(className)))
            {

                model.createAttendance(model.getSpecificCourse(course, weekDay, model.getClassId(className), startTime, endTime,
                        courseDate), student.getId(), NOT_ATTENDED_YET);
            }

        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

    @FXML
    private void cb_selectClass(ActionEvent event)
    {

    }


    /**
     * Closes the stage, since this is the very first stage, it will close the program completely.
     *
     * @param event
     */
    @FXML
    private void close_app(MouseEvent event)
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Minimizes the app.
     *
     * @param event
     */
    @FXML
    private void minimize_app(MouseEvent event)
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

}
