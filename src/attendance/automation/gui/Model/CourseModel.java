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
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jigzi
 */
public class CourseModel {
    private ObservableList<Course> allCourses;
    private ObservableList<String> allClassnames;
    private ObservableList<Course> allStartEndTimes;
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
    
    public int getAllCourseDates(String courseDate, String className) throws SQLException
    {
        return courseManager.getAllCourseDates(courseDate, className);
    }


    //This is what the controller calls when creating a Course. This calls a method in the CourseManager
    public void createCourses(String courseName, String weekDay, String startTime, String endTime, String className, String courseDate) throws DalException
    {
        boolean courseIsCreated = courseManager.createCourse(courseName, weekDay, startTime, endTime, className, courseDate);
        if (courseIsCreated)
        {
            System.out.println("Course Created");
        }
    }

    //This is what the controller calls when deleting a course. This calls a method in the CourseManager
    public void deleteCourse(Course selectedCourse) throws DalException
    {
        courseManager.deleteCourse(selectedCourse);
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
    
    public ObservableList<String> getAllClassNames() throws SQLException
    {
        allClassnames = FXCollections.observableArrayList();
        allClassnames.addAll(courseManager.getAllClassNames());
        return allClassnames;
    }
    
    public ObservableList<Course> getStartEndTime(String courseDate, String className) throws SQLException
    {
        allStartEndTimes = FXCollections.observableArrayList();
        allStartEndTimes.addAll(courseManager.getStartEndTime(courseDate, className));
        return allStartEndTimes;
    }
}
