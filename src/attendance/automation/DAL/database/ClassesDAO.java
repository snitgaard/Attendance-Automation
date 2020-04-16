/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.DAL.database;

import attendance.automation.BE.Classes;
import attendance.automation.DAL.DalException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classes Database Class
 * @author The Cowboys
 */
public class ClassesDAO
{

    private DatabaseConnector dbCon;

    public ClassesDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }
    
    /**
     * If called this method will create a connection between the database
     * and the program. The SQL statement will be run afterwards. 
     * Gets a list of strings containing all classes from the database
     * @return a list of all classes
     * @throws DalException 
     */
    public List<String> getAllClasses() throws DalException
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
        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not fetch all classes");
        }
    }
    
    /**
     * If called this method will create a connection between the database
     * and the program. The SQL statement will be run afterwards. 
     * Gets a classId int from the database based on specific className
     * @param classesName
     * @return classId int
     * @throws DalException 
     */
    public int getClassId(String classesName) throws DalException
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
        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not fetch class id");
        }
    }
    
    /**
     * If called this method will create a connection between the database
     * and the program. The SQL statement will be run afterwards. 
     * Gets a className string from the database based on a specific classId
     * @param classesId
     * @return className
     * @throws DalException 
     */
    public String getClassName(int classesId) throws DalException
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
        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not fetch class name");
        }
    }
}

