/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.Model;

import attendance.automation.BE.Student;
import attendance.automation.BLL.StudentManager;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author The Best Group
 */
public class StudentModel {
    
    private StudentManager StudentManager = new StudentManager();
    
    public List<Student> getAllData()
    {
        try {
            return StudentManager.getAllData();
        } catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }
    
    public boolean updateAttendance(String date, int id) throws SQLException
    {
        return StudentManager.updateAttendance(date, id);
    }
    
    public boolean checkLoginCredentials(String studentEmail, String studentPassword) throws SQLException
    {
        System.out.println("MODEL");
        return StudentManager.checkLoginCredentials(studentEmail, studentPassword);
    }
    
}
