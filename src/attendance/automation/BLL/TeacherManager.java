/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL;

import attendance.automation.BE.Teacher;
import attendance.automation.DAL.TeacherDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jigzi
 */
public class TeacherManager {
    
    private TeacherDAO TeacherDAO;

    public TeacherManager() {
        try
        {
            TeacherDAO = new TeacherDAO();
        } catch (IOException ex)
        {
            Logger.getLogger(TeacherManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public List<Teacher> getAllData() {
       try
        {
            return TeacherDAO.getAllTeachers();
        } catch (SQLException ex)
        {
            Logger.getLogger(TeacherManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
