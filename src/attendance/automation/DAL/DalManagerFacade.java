/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.DAL;

import attendance.automation.BE.Course;
import attendance.automation.BE.Student;
import attendance.automation.BE.Teacher;
import java.util.List;

/**
 *
 * @author CSnit
 */
public interface DalManagerFacade
{
    public List<String> getAllClasses();
    public int getClassId(String classesName);
    public String getClassName(int classesId);
    
    public List<Course> getAllCourses();
    public boolean createCourse(String courseName, String weekDay, String startTime, String endTime, int classId, String courseDate);
    public int getAllCourseDates(String courseDate, int classId);
    public int getSpecificCourse(String courseName, String weekDay, int classId, String startTime, String endTime, String courseDate);
    public List<Course> getStartEndTime(String courseDate, int classId);
    public List<Course> getAllCourseIds();
    
    
    public int getAttendance(int studentId, int courseId);
    public boolean updateAttendance(int attendance, int studentId, int courseId);
    public int getCourseId(String courseDate, int classId, String startTime);
    public int getStudentId(String studentName);
    public boolean createAttendance(int courseId, int studentId, int attended);
    public int getAttendanceFromCourses(int courseId, int studentId);
    public double getAttendancePerDay(int studentId, String weekDay);
    
    public List<Student> getAllStudentsClass(String className);
    public boolean checkStudentCredentials(String studentEmail, String studentPassword);
    public List<Student> getStudent(String studentEmail);
    public Student getSpecificStudent(String studentEmail);
    public boolean updateAttendace(double attendance, int studentId);
    
    public boolean checkTeacherCredentials(String teacherEmail, String teacherPassword);
    public List<Teacher> getTeacher(String teacherEmail);
    public Teacher getSpecificTeacher(String teacherEmail);
    
    
}
