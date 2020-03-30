/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.DAL;

import java.io.IOException;
import java.sql.*;

/**
 * @author Mads
 */
public class StudentCourseDAO
{

    private DatabaseConnector dbCon;

    public StudentCourseDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }

    public boolean updateAttendance(int attendance, int studentId, int courseId) throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "UPDATE StudentAttendance SET attendance = ? WHERE studentId = ? AND courseId = ?;";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, attendance);
            ps.setInt(2, studentId);
            ps.setInt(3, courseId);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

}
