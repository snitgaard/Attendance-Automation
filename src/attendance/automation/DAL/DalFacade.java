/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.DAL;

import attendance.automation.BE.*;

import java.text.ParseException;
import java.util.List;

/**
 * @author CSnit
 */
public interface DalFacade
{
    // ClassesDAO
    List<String> getAllClasses() throws DalException;

    int getClassId(String classesName) throws DalException;

    String getClassName(int classesId) throws DalException;

    // CourseDAO
    List<Course> getAllCourses() throws DalException;

    boolean createCourse(String courseName, String weekDay, String startTime, String endTime, int classId, String courseDate) throws DalException;

    int getAllCourseDates(String courseDate, int classId) throws DalException;

    int getSpecificCourse(String courseName, String weekDay, int classId, String startTime, String endTime, String courseDate) throws DalException;

    List<Course> getStartEndTime(String courseDate, int classId) throws DalException;

    List<Course> getAllCourseIds() throws DalException;

    List<String> getAllClassNames() throws DalException;

    // StudentCourseDAO
    int getAttendance(int studentId, int courseId) throws DalException;

    boolean updateAttendance(int attendance, int studentId, int courseId) throws DalException;

    int getCourseId(String courseDate, int classId, String startTime) throws DalException;

    int getStudentId(String studentName) throws DalException;

    boolean createAttendance(int courseId, int studentId, int attended) throws DalException;

    int getAttendanceFromCourses(int courseId, int studentId) throws DalException;

    // StudentDAO
    List<Student> getAllStudentsClass(String className) throws DalException, ParseException;

    boolean checkStudentCredentials(String studentEmail, String studentPassword) throws DalException;

    List<Student> getStudent(String studentEmail) throws DalException;

    Student getSpecificStudent(String studentEmail) throws DalException;

    boolean updateAttendancePercentage(double attendance, int studentId) throws DalException;

    List<Student> getStudentClass(int classId) throws DalException;

    // TeacherDAO
    boolean checkTeacherCredentials(String teacherEmail, String teacherPassword) throws DalException;

    List<Teacher> getTeacher(String teacherEmail) throws DalException;

    Teacher getSpecificTeacher(String teacherEmail) throws DalException;


}
