/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.DAL;

import attendance.automation.BE.Teacher;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author The Cowboys
 */
public class TeacherDAO
{

    private DatabaseConnector dbCon;

    // Initializer for TeacherDAO, creates a connection with the databaseconnector, allowing the class to speak with the database
    public TeacherDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }

    public boolean checkTeacherCredentials(String teacherEmail, String teacherPassword) throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {

            String sql = "SELECT * FROM Teacher WHERE teacherEmail = ? AND teacherPassword = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, teacherEmail);
            ps.setString(2, teacherPassword);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                return true;

            }
            return false;

        } catch (SQLServerException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    // This method gathers a list of all the Teachers, where the email is the parameter sent in.
    public List<Teacher> getTeacher(String teacherEmail) throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM Teacher WHERE teacherEmail = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, teacherEmail);
            ResultSet rs = ps.executeQuery();
            ArrayList<Teacher> allTeachers = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("teacherId");
                String name = rs.getString("teacherName");
                String email = rs.getString("teacherEmail");
                int courseId = rs.getInt("courseId");
                String teacherPassword = rs.getString("teacherPassword");
                int classId = rs.getInt("classId");

                Teacher teacher = new Teacher(id, name, email, courseId, teacherPassword, classId);
                allTeachers.add(teacher);
            }
            return allTeachers;

        }
    }

    //This method grabs the first teacher on the list of teachers with that specific email
    public Teacher getSpecificTeacher(String teacherEmail) throws SQLException
    {
        return getTeacher(teacherEmail).get(0);
    }

}
