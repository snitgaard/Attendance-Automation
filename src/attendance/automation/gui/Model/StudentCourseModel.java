/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.Model;

import attendance.automation.BLL.StudentCourseManager;
import attendance.automation.BLL.StudentManager;
import java.sql.SQLException;

/**
 *
 * @author Mads
 */
public class StudentCourseModel {
    
    private StudentCourseManager studentCourseManager = new StudentCourseManager();
    
    public boolean updateAttendance(int attendance, int studentId, int courseId) throws SQLException
    {
        return studentCourseManager.updateAttendance(attendance, studentId, courseId);
    }
    
}
