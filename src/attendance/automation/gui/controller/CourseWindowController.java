/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.BE.Course;
import attendance.automation.BE.Student;
import attendance.automation.DAL.DalException;
import attendance.automation.gui.Model.ClassesModel;
import attendance.automation.gui.Model.CourseModel;
import attendance.automation.gui.Model.StudentCourseModel;
import attendance.automation.gui.Model.StudentModel;
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
 * @author CSnit
 */
public class CourseWindowController implements Initializable {

    Course course;
    private CourseModel courseModel;
    private ClassesModel classesModel;
    private StudentCourseModel studentCourseModel;
    private StudentModel studentModel;

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
    public void initialize(URL url, ResourceBundle rb) {
        courseModel = new CourseModel();
        try {
            classesModel = new ClassesModel();
            studentModel = new StudentModel();
            studentCourseModel = new StudentCourseModel();
            cb_selectClass.setItems(classesModel.getAllClasses());

        } catch (IOException ex) {
            Logger.getLogger(CourseWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void createCourse(ActionEvent event) throws DalException, SQLException {
        String course = txt_courseName.getText();
        String weekDay = txt_weekDay.getText();
        String startTime = txt_startTime.getText();
        String endTime = txt_endTime.getText();
        String className = cb_selectClass.getSelectionModel().getSelectedItem();
        String courseDate = datePicker.getValue().toString();

        if (course.length() == 0 && weekDay.length() == 0) {
            Border warning = new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2)));

            txt_courseName.setBorder(warning);
            txt_weekDay.setBorder(warning);
        } else {
            int classId = classesModel.getClassId(className);
            courseModel.createCourses(course, weekDay, startTime, endTime, classId, courseDate);

            System.out.println(courseModel.getSpecificCourse("SCO", "Thursday", 1, "15:00", "17:00", "2020-04-02") + "Er det her???");

            for (Student student : studentModel.getStudentClass(classesModel.getClassId(className))) {

                studentCourseModel.createAttendance(courseModel.getSpecificCourse(course, weekDay, classesModel.getClassId(className), startTime, endTime,
                        courseDate), student.getId(), 0);
            }

        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

    @FXML
    private void cb_selectClass(ActionEvent event) {

    }

    @FXML
    private void close_app(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minimize_app(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

}
