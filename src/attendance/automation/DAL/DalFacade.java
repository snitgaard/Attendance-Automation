/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.DAL;

import attendance.automation.BE.Course;
import attendance.automation.BE.Student;
import attendance.automation.BE.Teacher;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author CSnit
 */
public interface DalFacade
{
    // ClassesDAO
    public List<String> getAllClasses() throws DalException;
    public int getClassId(String classesName) throws DalException;
    public String getClassName(int classesId) throws DalException;
    
    // CourseDAO
    public List<Course> getAllCourses() throws DalException;
    public boolean createCourse(String courseName, String weekDay, String startTime, String endTime, int classId, String courseDate) throws DalException;
    public int getAllCourseDates(String courseDate, int classId) throws DalException;
    public int getSpecificCourse(String courseName, String weekDay, int classId, String startTime, String endTime, String courseDate) throws DalException;
    public List<Course> getStartEndTime(String courseDate, int classId) throws DalException;
    public List<Course> getAllCourseIds() throws DalException;
    public List<String> getAllClassNames() throws DalException;
    
    // StudentCourseDAO
    public int getAttendance(int studentId, int courseId) throws DalException;
    public boolean updateAttendance(int attendance, int studentId, int courseId) throws DalException;
    public int getCourseId(String courseDate, int classId, String startTime) throws DalException;
    public int getStudentId(String studentName) throws DalException;
    public boolean createAttendance(int courseId, int studentId, int attended) throws DalException;
    public int getAttendanceFromCourses(int courseId, int studentId) throws DalException;

    // StudentDAO
    public List<Student> getAllStudentsClass(String className) throws DalException, ParseException;
    public boolean checkStudentCredentials(String studentEmail, String studentPassword) throws DalException;
    public List<Student> getStudent(String studentEmail) throws DalException;
    public Student getSpecificStudent(String studentEmail) throws DalException;
    public boolean updateAttendancePercentage(double attendance, int studentId) throws DalException;
    public List<Student> getStudentClass(int classId) throws DalException;
    
    // TeacherDAO
    public boolean checkTeacherCredentials(String teacherEmail, String teacherPassword) throws DalException;
    public List<Teacher> getTeacher(String teacherEmail) throws DalException;
    public Teacher getSpecificTeacher(String teacherEmail) throws DalException;
    
    
}
