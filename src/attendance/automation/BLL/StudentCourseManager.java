/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL;

import attendance.automation.BE.Course;
import attendance.automation.DAL.StudentCourseDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author The Cowboys
 */
public class StudentCourseManager
{

    private StudentCourseDAO studentCourseDAO;
    private CourseManager courseManager;

    public StudentCourseManager()
    {
        try
        {
            studentCourseDAO = new StudentCourseDAO();
        } catch (IOException ex)
        {
            Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getAttendance(int studentId, int courseId) throws SQLException
    {
        return studentCourseDAO.getAttendance(studentId, courseId);
    }

    public boolean updateAttendance(int attendance, int studentId, int courseId) throws SQLException
    {
        return studentCourseDAO.updateAttendance(attendance, studentId, courseId);
    }

    public int getCourseId(String courseDate, int classId, String startTime) throws SQLException
    {
        return studentCourseDAO.getCourseId(courseDate, classId, startTime);
    }

    public int getStudentId(String studentName) throws SQLException
    {
        return studentCourseDAO.getStudentId(studentName);
    }

    public boolean createAttendance(int courseId, int studentId, int attended)
    {
        return studentCourseDAO.createAttendance(courseId, studentId, attended);
    }

//    public List<Course> getAttendanceFromCourse(int studentId) throws SQLException
//    {
//        List<Course> courseIds = studentCourseDAO.getAttendanceFromCourse(studentId);
//        List<Course> result = new ArrayList<>();
//
//        LocalDate todaysDate = LocalDate.now();
//
//        for (Course course : courseIds)
//        {
//            if (course.getCourseDate() != null)
//            {
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//                LocalDate localCourseDate = LocalDate.parse(course.getCourseDate(), formatter);
//
//                if (localCourseDate.isBefore(todaysDate) == true)
//                {
//                    result.add(ints);
//                }
//            }
//        }
//        System.out.println(result + "er det her nigga");
//        return result;
//    }

    public int getAllCourseIds(int courseId, int studentId) throws SQLException
    {
        return studentCourseDAO.getAttendanceFromCourses(courseId, studentId);
    }

//    public double getAttendancePerDay(int studentId, String weekDay) throws SQLException
//    {
//        return studentCourseDAO.getAttendancePerDay(studentId, weekDay);
//    }

}
