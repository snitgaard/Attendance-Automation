/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL;

import attendance.automation.BE.Student;
import attendance.automation.DAL.StudentDAO;
<<<<<<< HEAD
<<<<<<< HEAD
import java.sql.SQLException;
=======
import java.io.IOException;
>>>>>>> 3714392a1ab0f5798ca6c9cd7ceae86eaaa5b539
=======
import java.io.IOException;
import java.sql.SQLException;
>>>>>>> 1ce33cf96a8c32fe1c9ffc90b96c28c82caac68d
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jigzi
 */
public class StudentManager {

<<<<<<< HEAD
<<<<<<< HEAD
    StudentDAO studentDAO;
    
    
    public List<Student> getAllData() throws SQLException 
    {
        return studentDAO.getAllStudent();
=======
=======
>>>>>>> 1ce33cf96a8c32fe1c9ffc90b96c28c82caac68d
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
<<<<<<< HEAD
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
>>>>>>> 3714392a1ab0f5798ca6c9cd7ceae86eaaa5b539
=======
        try
        {
            return StudentDAO.getAllStudents();
        } catch (SQLException ex)
        {
            Logger.getLogger(TeacherManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
>>>>>>> 1ce33cf96a8c32fe1c9ffc90b96c28c82caac68d
    }
    
}
