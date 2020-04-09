/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.Model;

import attendance.automation.BE.Teacher;
import attendance.automation.BLL.TeacherManager;

import java.sql.SQLException;
import java.util.List;

/**
 * @author The Cowboys
 */
public class TeacherModel
{

    private TeacherManager teacherManager = new TeacherManager();


    public boolean checkLoginCredentials(String teacherEmail, String teacherPassword) throws SQLException
    {
        return teacherManager.checkLoginCredentials(teacherEmail, teacherPassword);
    }

    public Teacher getSpecificTeacher(String teacherEmail) throws SQLException
    {
        return teacherManager.getSpecificTeacher(teacherEmail);
    }
}
