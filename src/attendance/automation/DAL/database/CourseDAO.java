/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.DAL.database;

import attendance.automation.BE.Course;
import attendance.automation.DAL.DalException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Course BE Class
 *
 * @author The Cowboys
 */
public class CourseDAO
{

    private DatabaseConnector dbCon;

    public CourseDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }

    /**
     * If called this method will create a connection between the database
     * and the program. The SQL statement will be run afterwards. 
     * Gets a list of all courses from the database
     *
     * @return list of all courses
     * @throws DalException
     */
    public List<Course> getAllCourses() throws DalException
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
            return allCourses;
        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not fetch all courses");
        }
    }

    /**
     * If called this method will create a connection between the database
     * and the program. The SQL statement will be run afterwards. 
     * Creates a new course based on a list of parameters
     *
     * @param courseName
     * @param weekDay
     * @param startTime
     * @param endTime
     * @param classId
     * @param courseDate
     * @return true if a row was affected, false if not
     * @throws DalException
     */
    public boolean createCourse(String courseName, String weekDay, String startTime, String endTime, int classId, String courseDate) throws DalException
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
            System.out.println(ex);
            throw new DalException("Could not create course");
        }
        return false;
    }
    
    /**
     * If called this method will create a connection between the database
     * and the program. The SQL statement will be run afterwards. 
     * Gets an int of all courses on a specific day based on courseDate and classId.
     * Counts the amount of rows in the database based on the query
     * @param courseDate
     * @param classId
     * @return the amount of rows counted in the database
     * @throws DalException 
     */
    public int getAllCourseDates(String courseDate, int classId) throws DalException
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
        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not fetch all course dates");
        }
    }
    
    /**
     * If called this method will create a connection between the database
     * and the program. The SQL statement will be run afterwards. 
     * Gets a list of all classNames from the database
     * @return a list of all classes
     * @throws DalException 
     */
    public List<String> getAllClassNames() throws DalException
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
        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not fetch all classe names");
        }
    }
    
    /**
     * If called this method will create a connection between the database
     * and the program. The SQL statement will be run afterwards. 
     * Gets a list of start and end times based on courseDate and classId from the database
     * @param courseDate
     * @param classId
     * @return list of all start and end times for all courses registered in the database
     * @throws DalException 
     */

    public List<Course> getStartEndTime(String courseDate, int classId) throws DalException
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
        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not fetch start and end time");
        }
    }
    
    /**
     * If called this method will create a connection between the database
     * and the program. The SQL statement will be run afterwards. 
     * Gets a list of courses based on a list of parameters
     * @param courseName
     * @param weekDay
     * @param classId
     * @param startTime
     * @param endTime
     * @param courseDate
     * @return a list of courses
     * @throws DalException 
     */
    public List<Course> getCourse(String courseName, String weekDay, int classId, String startTime, String endTime, String courseDate) throws DalException
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
            System.out.println(ex);
            throw new DalException("Could not get course");
        }
    }
    
    /**
     * If called this method will create a connection between the database
     * and the program. The SQL statement will be run afterwards. 
     * Gets a specific course from index 0 on the getCourse method
     * @param courseName
     * @param weekDay
     * @param classId
     * @param startTime
     * @param endTime
     * @param courseDate
     * @return index 0 of getCourse
     * @throws DalException 
     */
    public int getSpecificCourse(String courseName, String weekDay, int classId, String startTime, String endTime, String courseDate) throws DalException
    {
        return getCourse(courseName, weekDay, classId, startTime, endTime, courseDate).get(0).getCourseId();
    }
    
    /**
     * If called this method will create a connection between the database
     * and the program. The SQL statement will be run afterwards. 
     * Gets a list course ids and course date from the Course database
     * @return list of course Ids / course dates
     * @throws DalException 
     */
    public List<Course> getAllCourseIds() throws DalException
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
            return courseIdList;
        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not fetch all course ids");
        }
    }
}
