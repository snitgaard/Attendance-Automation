/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.DAL.database;

import attendance.automation.DAL.DalException;
import java.io.IOException;
import java.sql.*;

/**
 * @author The Cowboys
 */
public class StudentCourseDAO
{

    private DatabaseConnector dbCon;

    public StudentCourseDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }

    public int getAttendance(int studentId, int courseId) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            int attended = -1;
            String sql = "SELECT attended FROM StudentAttendance WHERE studentId = ? AND courseId = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                attended = rs.getInt("attended");
            }

            return attended;
        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not get attendance");
        }
    }

    public boolean updateAttendance(int attendance, int studentId, int courseId) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "UPDATE StudentAttendance SET attended = ? WHERE courseId = ? AND studentId = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, attendance);
            ps.setInt(2, courseId);
            ps.setInt(3, studentId);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not fetch all classes");
        }
    }

    public int getCourseId(String courseDate, int classId, String startTime) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT courseId FROM Course WHERE courseDate = ? AND classId = ? AND startTime = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, courseDate);
            ps.setInt(2, classId);
            ps.setString(3, startTime);
            ResultSet rs = ps.executeQuery();

            int courseId = 0;

            while (rs.next())
            {
                courseId = rs.getInt("courseId");
            }
            return courseId;
        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not get course id");
        }
    }

    public int getStudentId(String studentName) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT studentId FROM Student WHERE studentName = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, studentName);
            ResultSet rs = ps.executeQuery();

            int studentId = 0;

            while (rs.next())
            {
                studentId = rs.getInt("studentId");
            }

            return studentId;
        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not get student id");
        }
    }

    public boolean createAttendance(int courseId, int studentId, int attended) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "INSERT INTO StudentAttendance (courseId, studentId, attended) VALUES (?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, courseId);
            ps.setInt(2, studentId);
            ps.setInt(3, attended);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                return rs.next();
            }
            return false;
        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not create attendance");
        }
    }

    public int getAttendanceFromCourses(int courseId, int studentId) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT attended FROM StudentAttendance WHERE courseId = ? AND studentId = ?;";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, courseId);
            ps.setInt(2, studentId);
            ResultSet rs = ps.executeQuery();
            int attendance = -1;

            while (rs.next())
            {
                attendance = rs.getInt("attended");
            }
            return attendance;
        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not fetch atrtendance from courses");
        }
    }
}

//    public double getAttendancePerDay(int studentId, String weekDay) throws SQLException
//    {
//        try (Connection con = dbCon.getConnection())
//        {
//            String sql = "SELECT attended FROM StudentAttendance WHERE studentId = ? AND courseId IN (SELECT courseId FROM Course WHERE weekDay = ?);";
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, studentId);
//            ps.setString(2, weekDay);
//            ResultSet rs = ps.executeQuery();
//            double attendance = -1;
//            
//            while (rs.next())
//            {
//                 attendance = rs.getDouble("attended");
//                
//            }
//            return attendance;
//        }
//    }
