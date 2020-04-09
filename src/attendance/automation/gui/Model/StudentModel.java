/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.Model;

import attendance.automation.BE.Student;
import attendance.automation.BLL.StudentManager;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * @author The Best Group
 */
public class StudentModel
{

    private StudentManager studentManager = new StudentManager();
    private ObservableList<Student> allStudents;
    private ObservableList<Student> allStudentsClass;

//    public ObservableList<Student> getAllStudents()
//    {
//        allStudents = FXCollections.observableArrayList();
//        allStudents.addAll(studentManager.getAllData());
//        return allStudents;
//    }

    public boolean checkStudentCredentials(String studentEmail, String studentPassword) throws SQLException
    {
        return studentManager.checkStudentCredentials(studentEmail, studentPassword);
    }

    public List<Student> getStudent(String studentEmail) throws SQLServerException
    {
        return studentManager.getStudent(studentEmail);
    }

    //    public List<Student> getStudentsInCourse()
//    {
//        return studentManager
//    }
    public Student getSpecificStudent(String studentEmail) throws SQLServerException
    {
        return studentManager.getSpecificStudent(studentEmail);
    }

    public List<Student> getStudentClass(int classId) throws SQLException
    {
        return studentManager.getStudentClass(classId);
    }

    public boolean updateAttendance(double attendance, int studentId) throws SQLException
    {
        return studentManager.updateAttendance(attendance, studentId);
    }

    public ObservableList<Student> getAllStudentsClass(String className) throws SQLException, ParseException
    {
        allStudentsClass = FXCollections.observableArrayList();
        allStudentsClass.addAll(studentManager.getAllStudentsClass(className));
        return allStudentsClass;
    }
}
