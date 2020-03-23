/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.DAL;

import attendance.automation.BE.Person;
import attendance.automation.BE.Student;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jigzi
 */
public class StudentDAO {

    private DatabaseConnector dbCon;

    public StudentDAO() throws IOException {
        dbCon = new DatabaseConnector();
    }

    public List<Student> getAllStudents() throws SQLException {
        try (Connection con = dbCon.getConnection()) {
            String sql = "SELECT * FROM Student;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Student> allStudents = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("studentId");
                String name = rs.getString("studentName");
                String email = rs.getString("studentEmail");
                String course = rs.getString("course");
                int attendance = rs.getInt(("attendance"));
                String date = rs.getString("attendance");
                int courseAttended = rs.getInt("courseAttended");
                String studentPassword = rs.getString("studentPassword");
                String studentEducation = rs.getString("studentEducation");

                Student student = new Student(id, name, email, course, attendance, date, courseAttended, studentPassword, studentEducation);
                allStudents.add(student);
            }
            return allStudents;
        }
    }

    public boolean updateAttendance(String date, int id) throws SQLException {
        try (Connection con = dbCon.getConnection()) {
            String sql = "UPDATE Student SET date = ? WHERE studentId = ?;";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, date);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean checkLoginCredentials(String studentEmail, String studentPassword) throws SQLException {
        try (Connection con = dbCon.getConnection()) {

            String sql = "SELECT * FROM Student WHERE studentEmail = ? AND studentPassword = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, studentEmail);
            ps.setString(2, studentPassword);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return true;
                
            }
            return false;

        } catch (SQLServerException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
        
        public List<Student> getStudent(String studentEmail) throws SQLServerException
        {
            try (Connection con = dbCon.getConnection()) {

            String sql = "SELECT * FROM Student WHERE studentEmail = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, studentEmail);
            ResultSet rs = ps.executeQuery();
            ArrayList<Student> selectedStudent = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("studentId");
                String name = rs.getString("studentName");
                String email = rs.getString("studentEmail");
                String course = rs.getString("course");
                int attendance = rs.getInt(("attendance"));
                String date = rs.getString("attendance");
                int courseAttended = rs.getInt("courseAttended");
                String studentPassword = rs.getString("studentPassword");
                String studentEducation = rs.getString("studentEducation");

                Student student = new Student(id, name, email, course, attendance, date, courseAttended, studentPassword, studentEducation);
                selectedStudent.add(student);
        }
        return selectedStudent;
            
        } catch (SQLException ex) {
            return null;
        }
        
        }
        public Student getSpecificStudent(String studentEmail) throws SQLServerException
    {
        return getStudent(studentEmail).get(0);
    }

}
