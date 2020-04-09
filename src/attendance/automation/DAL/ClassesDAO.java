/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.DAL;

import attendance.automation.BE.Classes;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author The Cowboys
 */
public class ClassesDAO
{

    private DatabaseConnector dbCon;

    public ClassesDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }

    /*
     *
     */
    public List<String> getAllClasses() throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM Class;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<String> allClasses = new ArrayList<>();
            while (rs.next())
            {
                int classesId = rs.getInt("classId");
                String classesName = rs.getString("className");

                Classes classes = new Classes(classesId, classesName);
                allClasses.add(classes + "");
            }
            return allClasses;
        }
    }

    //Deletes the class from SQL Database
    public void deleteClasses(Classes classes)
    {
        try (Connection con = dbCon.getConnection())
        {
            int classesId = classes.getClassesId();
            String sql = "DELETE FROM Course WHERE courseId=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, classesId);
            int affectedRows = ps.executeUpdate();
            if (affectedRows != 1)
            {
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();

        }
    }

    /*
     * If called this method will create a connection between the database and the program
     * The SQL statement will be run.
     * A new class will be given with the name chosen.
     */
    public boolean createClasses(int classesId, String classesName, int studentId, int teacherId)
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "INSERT INTO Class (courseName, weekDay, startTime, endTime) VALUES (?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, classesId);
            ps.setString(2, classesName);
            ps.setInt(3, studentId);
            ps.setInt(4, teacherId);

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                {
                    return true;
                }
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    /*
     * If called this method will create a connection between the database and the program
     * The SQL statement will be run.
     * the class with the chosen courseId, will have its className changed
     */
    public boolean updateClasses(String classesName, int classesId)
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "UPDATE Course SET className = ? WHERE classId = ?;";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, classesName);
            ps.setInt(2, classesId);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    public int getClassId(String classesName) throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT classId FROM Class WHERE className = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, classesName);
            ResultSet rs = ps.executeQuery();
            int classId = 0;

            while (rs.next())
            {
                classId = rs.getInt("classId");
            }
            return classId;
        }
    }

    public String getClassName(int classesId) throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT className FROM Class WHERE classId = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, classesId);
            ResultSet rs = ps.executeQuery();
            String className = "";
            while (rs.next())
            {
                className = rs.getString("className");
            }
            return className;
        }
    }

}
