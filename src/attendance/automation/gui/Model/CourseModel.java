/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.Model;

import attendance.automation.BE.Course;
import attendance.automation.BLL.CourseManager;
import attendance.automation.DAL.DalException;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jigzi
 */
public class CourseModel {
    private ObservableList<Course> allCourses;
    private CourseManager courseManager;

    
    public CourseModel()
    {
        courseManager = new CourseManager();
    }

    //This is what the controller calls when trying to show a list of all Courses. This calls a method in the CourseManager
    public ObservableList<Course> getAllCourses()
    {
        allCourses = FXCollections.observableArrayList();
        allCourses.addAll(courseManager.getAllCourses());
        return allCourses;
    }


    //This is what the controller calls when creating a Course. This calls a method in the CourseManager
    public void createCourses(String courseName, String weekDay, int courseLength, String selectClass) throws DalException
    {
        boolean courseIsCreated = courseManager.createCourse(courseName, weekDay, courseLength, selectClass);
        if (courseIsCreated)
        {
            System.out.println("Course Created");
        }
    }

    //This is what the controller calls when deleting a course. This calls a method in the CourseManager
    public void deleteCourse(Course selectedCourse) throws DalException
    {
        courseManager.deleteSong(selectedCourse);
        if (allCourses.remove(selectedCourse))
        {
            allCourses.remove(selectedCourse);
        }
    }

    //This is what the controller calls when updating the name of a course. This calls a method in the CourseManager
    public void updateCourse(String courseName, int courseId)
    {
        boolean courseIsUpdated = courseManager.updateCourse(courseName, courseId);
        if (courseIsUpdated)
        {
            System.out.println("course Is Updated");
        }
    }

}
