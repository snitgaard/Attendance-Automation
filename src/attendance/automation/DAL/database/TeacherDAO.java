/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.DAL.database;

import attendance.automation.BE.Teacher;
import attendance.automation.DAL.DalException;

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

    public TeacherDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }

    /**
     * If called this method will create a connection between the database
     * and the program. The SQL statement will be run afterwards. 
     * Checks if the teacher login provided matches with the credentials in the database
     * @param teacherEmail
     * @param teacherPassword
     * @return true if a row was found, false if not
     * @throws DalException 
     */
    public boolean checkTeacherCredentials(String teacherEmail, String teacherPassword) throws DalException
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

        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not check teacher credentials");
        }
    }
    
    /**
     * If called this method will create a connection between the database
     * and the program. The SQL statement will be run afterwards. 
     * Gets a list of teachers based on teacher email
     * @param teacherEmail
     * @return list of teachers
     * @throws DalException 
     */
    public List<Teacher> getTeacher(String teacherEmail) throws DalException
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
        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not get teacher");
        }
    }

    /**
     * If called this method will create a connection between the database
     * and the program. The SQL statement will be run afterwards. 
     * Get index 0 of getTeacher method (the allTeachers list)
     * @param teacherEmail
     * @return
     * @throws DalException 
     */
    public Teacher getSpecificTeacher(String teacherEmail) throws DalException
    {
        return getTeacher(teacherEmail).get(0);
    }
}
