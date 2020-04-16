/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL;

import attendance.automation.BE.*;

import java.text.ParseException;
import java.util.List;

/**
 * BllFacade class
 *
 * @author The Cowboys
 */
public interface BllFacade
{
    // ClassDAO
    List<String> getAllClasses() throws BllException;

    int getClassId(String classesName) throws BllException;

    String getClassName(int classesId) throws BllException;

    // CourseDAO
    List<Course> getAllCourses() throws BllException;

    boolean createCourse(String courseName, String weekDay, String startTime, String endTime, int classId, String courseDate) throws BllException;

    int getAllCourseDates(String courseDate, int classId) throws BllException;

    int getSpecificCourse(String courseName, String weekDay, int classId, String startTime, String endTime, String courseDate) throws BllException;

    List<Course> getStartEndTime(String courseDate, int classId) throws BllException;

    List<Course> getAllCourseIds() throws BllException;

    List<String> getAllClassNames() throws BllException;

    // StudentCourseDAO 
    int getAttendance(int studentId, int courseId) throws BllException;

    int getCourseId(String courseDate, int classId, String startTime) throws BllException;

    boolean updateAttendance(int attendance, int studentId, int courseId) throws BllException;

    int getStudentId(String studentName) throws BllException;

    boolean createAttendance(int courseId, int studentId, int attended) throws BllException;

    int getAttendanceFromCourses(int courseId, int studentId) throws BllException;

    // StudentDAO
    List<Student> getAllStudentsClass(String className) throws BllException, ParseException;

    boolean checkStudentCredentials(String studentEmail, String studentPassword) throws BllException;

    List<Student> getStudent(String studentEmail) throws BllException;

    Student getSpecificStudent(String studentEmail) throws BllException;

    boolean updateAttendancePercentage(double attendance, int studentId) throws BllException;

    List<Student> getStudentClass(int classId) throws BllException;

    // TeacherDAO
    boolean checkTeacherCredentials(String teacherEmail, String teacherPassword) throws BllException;

    List<Teacher> getTeacher(String teacherEmail) throws BllException;

    Teacher getSpecificTeacher(String teacherEmail) throws BllException;
}
