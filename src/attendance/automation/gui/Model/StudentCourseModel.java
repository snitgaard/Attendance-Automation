/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.Model;

import attendance.automation.BLL.StudentCourseManager;

import java.sql.SQLException;

/**
 * @author Mads
 */
public class StudentCourseModel
{

    private StudentCourseManager studentCourseManager = new StudentCourseManager();
    
    public int getAttendance(int studentId, int courseId) throws SQLException
    {
        return studentCourseManager.getAttendance(studentId, courseId);
    }

    public boolean updateAttendance(int attendance, int studentId, int courseId) throws SQLException
    {
        return studentCourseManager.updateAttendance(attendance, studentId, courseId);
    }

    public int getCourseId(String courseDate, int classId, String startTime) throws SQLException
    {
        return studentCourseManager.getCourseId(courseDate, classId, startTime);
    }

    public int getStudentId(String studentName) throws SQLException
    {
        return studentCourseManager.getStudentId(studentName);
    }
    
    public boolean createAttendance(int courseId, int studentId, int attended)
    {
        return studentCourseManager.createAttendance(courseId, studentId, attended);
    }

}
