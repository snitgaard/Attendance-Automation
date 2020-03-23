/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.DAL;

import attendance.automation.BE.Course;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
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
                String className = rs.getString("className");
                int courseLength = rs.getInt("courseLength");
                Date courseDate = rs.getDate("courseDate");
                Course course = new Course(courseId, courseName, weekDay, className, courseLength, courseDate);
                allCourses.add(course);
            }
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
    public boolean createCourse(String courseName, String weekDay, String courseLength, String className)
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "INSERT INTO Course (courseName, weekDay, courseLength, className) VALUES (?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, courseName);
            ps.setString(2, weekDay);
            ps.setString(3, courseLength);
            ps.setString(4, className);
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

    public int getAllCourseDates(Date courseDate) throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate today = LocalDate.now();
            Date currentDate = Date.from(today.atStartOfDay(defaultZoneId).toInstant());

            if (courseDate == currentDate)
            {
                String sql = "SELECT courseDate, count(*) as courseCount FROM Course\n"
                        + "group by courseDate;";
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                int courseCount = 0;
                while (rs.next())
                {
                    courseCount++;
                }
                System.out.println(courseCount);
                return courseCount;
            }
            else
            {
                return 0;
            }

        }
    }

}
