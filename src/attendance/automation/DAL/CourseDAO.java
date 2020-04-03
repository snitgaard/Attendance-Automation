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
 * @author jigzi
 */
public class CourseDAO
{

    private DatabaseConnector dbCon;

    public CourseDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }

    /*
     *
     */
    public List<Course> getAllCourses() throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM Course;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Course> allCourses = new ArrayList<>();
            while (rs.next())
            {
                int courseId = rs.getInt("courseId");
                String courseName = rs.getString("courseName");
                String weekDay = rs.getString("weekDay");
                int classId = rs.getInt("classId");
                String startTime = rs.getString("startTime");
                String endTime = rs.getString("endTime");
                String courseDate = rs.getString("courseDate");
                Course course = new Course(courseId, courseName, weekDay, classId, startTime, endTime, courseDate);
                allCourses.add(course);
            }
            System.out.println(allCourses + "DET ER HEAFHeaswgfewayhfaewjgvheawghvfaweghjfeawghffeawhgf");
            return allCourses;
        }
    }

    //Deletes the course from SQL Database
    public void deleteCourse(Course course)
    {
        try (Connection con = dbCon.getConnection())
        {
            int courseId = course.getCourseId();
            String sql = "DELETE FROM Course WHERE courseId=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, courseId);
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
     * A new course will be given with the name chosen.
     */
    public boolean createCourse(String courseName, String weekDay, String startTime, String endTime, int classId, String courseDate)
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "INSERT INTO Course (courseName, weekDay, startTime, endTime, classId, courseDate) VALUES (?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, courseName);
            ps.setString(2, weekDay);
            ps.setString(3, startTime);
            ps.setString(4, endTime);
            ps.setInt(5, classId);
            ps.setString(6, courseDate);
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
     * the course with the chosen courseId, will have its courseName changed
     */
    public boolean updateCourse(String courseName, int courseId)
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "UPDATE Course SET courseName = ? WHERE courseId = ?;";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, courseName);
            ps.setInt(2, courseId);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    public int getAllCourseDates(String courseDate, int classId) throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {

            String sql = "SELECT * FROM Course WHERE courseDate = ? AND classId = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, courseDate);
            ps.setInt(2, classId);
            ResultSet rs = ps.executeQuery();

            int courseCount = 0;

            while (rs.next())
            {
                courseCount++;
            }

            return courseCount;
        }
    }

    public List<String> getAllClassNames() throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT Distinct classId FROM Course;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<String> allClasses = new ArrayList<>();
            while (rs.next())
            {
                int classId = rs.getInt("classId");
                allClasses.add(classId + "");
            }
            return allClasses;
        }
    }

    public List<Course> getStartEndTime(String courseDate, int classId) throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT startTime, endTime, courseName FROM Course WHERE courseDate = ? AND classId = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, courseDate);
            ps.setInt(2, classId);
            ResultSet rs = ps.executeQuery();
            String startTime = null;
            String endTime = null;
            String courseName = null;
            ArrayList<Course> startEndTimes = new ArrayList<>();

            while (rs.next())
            {
                startTime = rs.getString("startTime");
                endTime = rs.getString(("endTime"));
                courseName = rs.getString("courseName");
                Course course = new Course(startTime, endTime, courseName);
                startEndTimes.add(course);
            }
            return startEndTimes;
        }
    }

    public List<Course> getCourse(String courseName, String weekDay, int classId, String startTime, String endTime, String courseDate) throws SQLServerException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM Course WHERE courseName = ? AND weekDay = ? AND classId = ? AND startTime = ? AND endTime = ? AND courseDate = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, courseName);
            ps.setString(2, weekDay);
            ps.setInt(3, classId);
            ps.setString(4, startTime);
            ps.setString(5, endTime);
            ps.setString(6, courseDate);
            ResultSet rs = ps.executeQuery();
            ArrayList<Course> courseList = new ArrayList<>();
            while (rs.next())
            {
                int courseId = rs.getInt("courseId");
                Course course = new Course(courseId, courseName, weekDay, classId, startTime, endTime, courseDate);
                courseList.add(course);
            }
            return courseList;

        } catch (SQLException ex)
        {
            return null;
        }

    }

    public int getSpecificCourse(String courseName, String weekDay, int classId, String startTime, String endTime,  String courseDate) throws SQLServerException
    {
        return getCourse(courseName, weekDay, classId, startTime, endTime, courseDate).get(0).getCourseId();
    }
    
     public List<Course> getAllCourseIds() throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {

            String sql = "SELECT courseId, courseDate FROM Course;";

            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Course> courseIdList = new ArrayList<>();
            
            while (rs.next())
            {
                int courseId = rs.getInt("courseId");
                String courseDate = rs.getString("courseDate");
                Course course = new Course(courseId, courseDate);
                courseIdList.add(course);
                
            }
            System.out.println(courseIdList + "DET ER HER ENERGJEWGNEWGHSEGHESRGHESRGJERSGRHSE");
            return courseIdList;
        }
    }
    

}
