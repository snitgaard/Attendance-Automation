/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.DAL;

import attendance.automation.BE.Person;
import attendance.automation.BE.Student;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jigzi
 */
public class StudentDAO {
    
   private DatabaseConnector dbCon;
   
   public StudentDAO() throws IOException
   {
       dbCon = new DatabaseConnector();
   }

   public List<Student> getAllStudents() throws SQLException
   {
       try (Connection con = dbCon.getConnection())
       {
           String sql = "SELECT * FROM Student;";
           Statement statement = con.createStatement();
           ResultSet rs = statement.executeQuery(sql);
           ArrayList<Student> allStudents = new ArrayList<>();
           while (rs.next())
            {
                int id = rs.getInt("Id");
                String name = rs.getString("name");
                
                Student student = new Student(id, name);
                allStudents.add(student);
            }
            return allStudents;
           
       }
   }
   
   
}
