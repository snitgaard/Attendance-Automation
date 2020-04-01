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
 * @author jigzi
 */
public class TeacherDAO
{

    private DatabaseConnector dbCon;

    public TeacherDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }

    public List<Teacher> getAllTeachers() throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM Teacher;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Teacher> allTeachers = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("Id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String course = rs.getString("course");
                String teacherPassword = rs.getString("teacherPassword");
                int classId = rs.getInt("classId");

                Teacher teacher = new Teacher(id, name, email, course, teacherPassword, classId);
                allTeachers.add(teacher);
            }
            return allTeachers;

        }
    }

    public boolean checkLoginCredentials(String teacherEmail, String teacherPassword) throws SQLException
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
                String course = rs.getString("course");
                String teacherPassword = rs.getString("teacherPassword");
                int classId = rs.getInt("classId");

                Teacher teacher = new Teacher(id, name, email, course, teacherPassword, classId);
                allTeachers.add(teacher);
            }
            return allTeachers;

        }
    }

    public Teacher getSpecificTeacher(String teacherEmail) throws SQLException
    {
        return getTeacher(teacherEmail).get(0);
    }

}
