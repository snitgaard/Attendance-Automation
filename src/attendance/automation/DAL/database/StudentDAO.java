/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.DAL.database;

import attendance.automation.BE.Student;
import attendance.automation.DAL.DalException;

import java.io.IOException;
import java.sql.*;
import java.text.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author The Cowboys
 */
public class StudentDAO
{

    private DatabaseConnector dbCon;

    public StudentDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }

    /**
     * If called this method will create a connection between the database
     * and the program. The SQL statement will be run afterwards.
     * Gets a list of all students and formats the attendance to be 2 decimals
     *
     * @param className
     * @return list of all students
     * @throws DalException
     * @throws ParseException
     */
    public List<Student> getAllStudentsClass(String className) throws DalException, ParseException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM Student WHERE classId IN (SELECT classId FROM Class WHERE className = ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, className);
            ResultSet rs = ps.executeQuery();
            ArrayList<Student> allStudents = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("studentId");
                String name = rs.getString("studentName");
                String email = rs.getString("studentEmail");
                int classId = rs.getInt("classId");
                double attendance = rs.getDouble("attendance");
                int semester = rs.getInt("semester");
                String studentPassword = rs.getString("studentPassword");
                String studentEducation = rs.getString("studentEducation");

                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setDecimalSeparator(',');
                DecimalFormat format = new DecimalFormat("0.#");
                format.setDecimalFormatSymbols(symbols);
                String attendanceTwoDecimals = new DecimalFormat("##.##").format(attendance);
                double twoDecimals = format.parse(attendanceTwoDecimals).doubleValue();


                Student student = new Student(id, name, email, classId, twoDecimals, semester, studentPassword, studentEducation);
                allStudents.add(student);
            }
            return allStudents;
        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not fetch all students from classes");
        } catch (ParseException exx)
        {
            System.out.println(exx);
            throw new DalException("Could not parse decimals");
        }
    }

    /**
     * If called this method will create a connection between the database
     * and the program. The SQL statement will be run afterwards.
     * Checks the student credentials based on student email and password
     *
     * @param studentEmail
     * @param studentPassword
     * @return true if credentials match with the database and false if not
     * @throws DalException
     */
    public boolean checkStudentCredentials(String studentEmail, String studentPassword) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {

            String sql = "SELECT * FROM Student WHERE studentEmail = ? AND studentPassword = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, studentEmail);
            ps.setString(2, studentPassword);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                return true;

            }
            return false;

        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not check student credentials");
        }
    }

    /**
     * If called this method will create a connection between the database
     * and the program. The SQL statement will be run afterwards.
     * Gets a list of students from studentEmail
     *
     * @param studentEmail
     * @return list of students called selectedStudent
     * @throws DalException
     */
    public List<Student> getStudent(String studentEmail) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {

            String sql = "SELECT * FROM Student WHERE studentEmail = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, studentEmail);
            ResultSet rs = ps.executeQuery();
            ArrayList<Student> selectedStudent = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("studentId");
                String name = rs.getString("studentName");
                String email = rs.getString("studentEmail");
                int classId = rs.getInt("classId");
                double attendance = rs.getDouble("attendance");
                int semester = rs.getInt("semester");
                String studentPassword = rs.getString("studentPassword");
                String studentEducation = rs.getString("studentEducation");

                Student student = new Student(id, name, email, classId, attendance, semester, studentPassword, studentEducation);
                selectedStudent.add(student);
            }
            return selectedStudent;

        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not get student");
        }
    }

    /**
     * If called this method will create a connection between the database
     * and the program. The SQL statement will be run afterwards.
     * Gets specific student on index 0
     *
     * @param studentEmail
     * @return index 0 of getStudent method
     * @throws DalException
     */
    public Student getSpecificStudent(String studentEmail) throws DalException
    {
        return getStudent(studentEmail).get(0);
    }

    /**
     * If called this method will create a connection between the database
     * and the program. The SQL statement will be run afterwards.
     * Gets a list of students from classId
     *
     * @param classId
     * @return list of students
     * @throws DalException
     */
    public List<Student> getStudentClass(int classId) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT studentId FROM Student WHERE classId = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, classId);
            ResultSet rs = ps.executeQuery();
            ArrayList<Student> studentList = new ArrayList<>();

            int studentId = 0;
            while (rs.next())
            {
                studentId = rs.getInt("studentId");
                Student students = new Student(studentId);
                studentList.add(students);
            }
            return studentList;
        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not get student class");
        }
    }

    /**
     * If called this method will create a connection between the database
     * and the program. The SQL statement will be run afterwards.
     * Updates attendance percentage based on attendance and studentId
     *
     * @param attendance
     * @param studentId
     * @return true if attendance was updated
     * @throws DalException
     */
    public boolean updateAttendancePercentage(double attendance, int studentId) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "UPDATE Student SET attendance = ? WHERE studentId = ?;";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, attendance);
            ps.setInt(2, studentId);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not update attendance");
        }
    }
}
