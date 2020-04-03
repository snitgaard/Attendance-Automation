/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.DAL;

import attendance.automation.BE.Course;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public int getAttendance(int studentId, int courseId) throws SQLException
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
        }
    }

    public boolean updateAttendance(int attendance, int studentId, int courseId) throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "UPDATE StudentAttendance SET attended WHERE courseId = ? AND studentId = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, courseId);
            ps.setInt(2, studentId);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex)
        {
            return false;
        }
    }

    public int getCourseId(String courseDate, int classId, String startTime) throws SQLException
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
            System.out.println(courseId + "DET ER HER DET ER");
            return courseId;

        }
    }

    public int getStudentId(String studentName) throws SQLException
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

        }
    }

    public boolean createAttendance(int courseId, int studentId, int attended)
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
            return false;
        }

    }

    public List<Course> getAttendanceFromCourse(int studentId) throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT courseDate, attended FROM StudentAttendance as studentattendance \n"
                    + "JOIN Course as course ON studentattendance.courseId = course.courseId\n"
                    + "WHERE studentattendance.studentId = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            ArrayList<Course> attendanceList = new ArrayList<>();

            while (rs.next())
            {
                int attendance = rs.getInt("attended");
                rs.getString("courseDate");
                attendanceList.add(attendance);
            }
            System.out.println(attendanceList + "yup");
            return attendanceList;

        }
    }

}
