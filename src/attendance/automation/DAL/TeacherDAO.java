/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.DAL;

import attendance.automation.BE.Student;
import attendance.automation.BE.Teacher;
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
public class TeacherDAO {

    private DatabaseConnector dbCon;

    public TeacherDAO() throws IOException {
        dbCon = new DatabaseConnector();
    }

    public List<Teacher> getAllTeachers() throws SQLException {
        try ( Connection con = dbCon.getConnection()) {
            String sql = "SELECT * FROM Teacher;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Teacher> allTeachers = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("name");

                Teacher teacher = new Teacher(id, name);
                allTeachers.add(teacher);
            }
            return allTeachers;

        }
    }

}
