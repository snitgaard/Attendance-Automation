/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL;

import attendance.automation.BE.Student;
import attendance.automation.DAL.StudentDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author The Cowboys
 */
public class StudentManager
{

    private StudentDAO studentDAO;

    public StudentManager()
    {
        try
        {
            studentDAO = new StudentDAO();
        } catch (IOException ex)
        {
            Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Student> getAllData()
    {
        try
        {
            return studentDAO.getAllStudents();
        } catch (SQLException ex)
        {
            Logger.getLogger(TeacherManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean checkLoginCredentials(String studentEmail, String studentPassword) throws SQLException
    {
        return studentDAO.checkLoginCredentials(studentEmail, studentPassword);
    }

    public List<Student> getStudent(String studentEmail) throws SQLServerException
    {
        return studentDAO.getStudent(studentEmail);
    }

    public List<Student> getStudentsInCourse(String course) throws SQLServerException
    {
        return studentDAO.getStudentsInCourse(course);
    }

    public Student getSpecificStudent(String studentEmail) throws SQLServerException
    {
        return studentDAO.getSpecificStudent(studentEmail);
    }

    public List<Student> getStudentClass(int classId) throws SQLException
    {
        return studentDAO.getStudentClass(classId);
    }

    public boolean updateAttendance(double attendance, int studentId) throws SQLException
    {
        return studentDAO.updateAttendace(attendance, studentId);
    }

    public List<Student> getAllStudentsClass(String className) throws SQLException, ParseException
    {
        return studentDAO.getAllStudentsClass(className);
    }

}
