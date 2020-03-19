/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL;

import attendance.automation.BE.Student;
import attendance.automation.DAL.StudentDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jigzi
 */
public class StudentManager {
    
    private StudentDAO studentDAO;
    
    public StudentManager() {
        try
        {
            studentDAO = new StudentDAO();
        } catch (IOException ex)
        {
            Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    public List<Student> getAllData() {
        try
        {
            return studentDAO.getAllStudents();
        } catch (SQLException ex)
        {
            Logger.getLogger(TeacherManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean updateAttendance(String date, int id) throws SQLException
    {
        return studentDAO.updateAttendance(date, id);
    }
    
    public boolean checkLoginCredentials(String studentEmail, String studentPassword) throws SQLException
    {
        System.out.println("MANAGER");
        return studentDAO.checkLoginCredentials(studentEmail, studentPassword);
    }
    
}
