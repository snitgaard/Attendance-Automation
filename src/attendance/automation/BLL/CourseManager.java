/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL;

import attendance.automation.BE.Course;
import attendance.automation.DAL.CourseDAO;
import attendance.automation.DAL.DalException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jigzi
 */
public class CourseManager
{

    private CourseDAO courseDao;

    public CourseManager()
    {

        try
        {
            courseDao = new CourseDAO();
        } catch (IOException ex)
        {
            Logger.getLogger(CourseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // This method calls the createCourse method in the courseDao
    public boolean createCourse(String courseName, String weekDay, String startTime, String endTime, String className, String courseDate)
    {
        return courseDao.createCourse(courseName, weekDay, startTime, endTime, className, courseDate);
    }

    // This method tries to call getAllCourses method from courseDao
    public List<Course> getAllCourses()
    {
        try
        {
            return courseDao.getAllCourses();
        } catch (SQLException ex)
        {
            Logger.getLogger(CourseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // This method calls the method deleteCourse in the courseDao
    public void deleteSong(Course course) throws DalException
    {
        courseDao.deleteCourse(course);
    }

    //This method calls the method updateCourse method from the courseDao
    public boolean updateCourse(String courseName, int courseId)
    {
        return courseDao.updateCourse(courseName, courseId);
    }

    public int getAllCourseDates(String courseDate, String className) throws SQLException
    {
        return courseDao.getAllCourseDates(courseDate, className);
    }
    
    public List<String> getAllClassNames() throws SQLException
    {
        return courseDao.getAllClassNames();
    }
    
    public List<Course> getStartEndTime(String courseDate, String className) throws SQLException
    {
        return courseDao.getStartEndTime(courseDate, className);
    }

}
