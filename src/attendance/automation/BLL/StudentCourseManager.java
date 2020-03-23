/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL;

import attendance.automation.DAL.StudentCourseDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mads
 */
public class StudentCourseManager {
    
     private StudentCourseDAO studentCourseDAO;
    
    public StudentCourseManager() {
        try
        {
            studentCourseDAO = new StudentCourseDAO();
        } catch (IOException ex)
        {
            Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean updateAttendance(int attendance, int studentId, int courseId) throws SQLException
    {
        return studentCourseDAO.updateAttendance(attendance, studentId, courseId);
    }
    
}
