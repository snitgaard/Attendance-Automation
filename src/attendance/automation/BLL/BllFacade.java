/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL;

import attendance.automation.BE.Course;
import attendance.automation.BE.Student;
import attendance.automation.BE.Teacher;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author CSnit
 */
public interface BllFacade
{
    public List<String> getAllClasses() throws BllException;
    public int getClassId(String classesName) throws BllException;
    public String getClassName(int classesId) throws BllException;
    
    public List<Course> getAllCourses() throws BllException;
    public boolean createCourse(String courseName, String weekDay, String startTime, String endTime, int classId, String courseDate) throws BllException;
    public int getAllCourseDates(String courseDate, int classId) throws BllException;
    public int getSpecificCourse(String courseName, String weekDay, int classId, String startTime, String endTime, String courseDate) throws BllException;
    public List<Course> getStartEndTime(String courseDate, int classId) throws BllException;
    public List<Course> getAllCourseIds() throws BllException;
    public List<String> getAllClassNames() throws BllException;
    
    
    public int getAttendance(int studentId, int courseId) throws BllException;
    public int getCourseId(String courseDate, int classId, String startTime) throws BllException;
        public boolean updateAttendance(int attendance, int studentId, int courseId) throws BllException;
    public int getStudentId(String studentName) throws BllException;
    public boolean createAttendance(int courseId, int studentId, int attended) throws BllException;
    public int getAttendanceFromCourses(int courseId, int studentId) throws BllException;

    
    public List<Student> getAllStudentsClass(String className) throws BllException, ParseException;
    public boolean checkStudentCredentials(String studentEmail, String studentPassword) throws BllException;
    public List<Student> getStudent(String studentEmail) throws BllException;
    public Student getSpecificStudent(String studentEmail) throws BllException;
    public boolean updateAttendancePercentage(double attendance, int studentId) throws BllException;
    public List<Student> getStudentClass(int classId) throws BllException;
    
    public boolean checkTeacherCredentials(String teacherEmail, String teacherPassword) throws BllException;
    public List<Teacher> getTeacher(String teacherEmail) throws BllException;
    public Teacher getSpecificTeacher(String teacherEmail) throws BllException;
    
}
