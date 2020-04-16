/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL;

import attendance.automation.BE.Course;
import attendance.automation.BE.Student;
import attendance.automation.BE.Teacher;
import attendance.automation.DAL.DalException;
import attendance.automation.DAL.DalFacade;
import attendance.automation.DAL.DalManager;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * BllManager class
 * @author The Cowboys
 */
public class BllManager implements BllFacade
{

    private final DalFacade dalFacade;

    public BllManager() throws IOException
    {
        dalFacade = new DalManager();
    }
    
    /**
     * Gets a list of all classes from the dalFacade
     * @return getAllClasses method from the dalFacade
     * @throws BllException 
     */
    @Override
    public List<String> getAllClasses() throws BllException
    {
        try
        {
            return dalFacade.getAllClasses();
        } catch (DalException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
    
    /**
     * Gets a classId int based on the classesName
     * @param classesName
     * @return getClassId from the dalFacade
     * @throws BllException 
     */
    @Override
    public int getClassId(String classesName) throws BllException
    {
        try
        {
            return dalFacade.getClassId(classesName);
        } catch (DalException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
    
    /**
     * Gets the classesName string based on a classId int
     * @param classesId
     * @return getClassName from the dalFacade
     * @throws BllException 
     */
    @Override
    public String getClassName(int classesId) throws BllException
    {
        try
        {
            return dalFacade.getClassName(classesId);
        } catch (DalException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
    
    /**
     * Gets a list of courses
     * @return getAllCourses from the dalFacade
     * @throws BllException 
     */
    @Override
    public List<Course> getAllCourses() throws BllException
    {
        try
        {
            return dalFacade.getAllCourses();
        } catch (DalException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
    
    /**
     * Creates a course based on a list of parameters needed to create said course
     * @param courseName
     * @param weekDay
     * @param startTime
     * @param endTime
     * @param classId
     * @param courseDate
     * @return createCourse from the dalFacade
     * @throws BllException 
     */
    @Override
    public boolean createCourse(String courseName, String weekDay, String startTime, String endTime, int classId, String courseDate) throws BllException
    {
        try
        {
            return dalFacade.createCourse(courseName, weekDay, startTime, endTime, classId, courseDate);
        } catch (DalException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
    
    /**
     * Gets an int of all course dates from the dalFacade. Returns an int because of the
     * method in the dal layer.
     * @param courseDate
     * @param classId
     * @return getAllCourseDates from the dalFacade
     * @throws BllException 
     */
    @Override
    public int getAllCourseDates(String courseDate, int classId) throws BllException
    {
        try
        {
            return dalFacade.getAllCourseDates(courseDate, classId);
        } catch (DalException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
    
    /**
     * Gets a specific course based on a list of parameters for finding that specific course
     * @param courseName
     * @param weekDay
     * @param classId
     * @param startTime
     * @param endTime
     * @param courseDate
     * @return getSpecificCourse from the dalFacade
     * @throws BllException 
     */
    @Override
    public int getSpecificCourse(String courseName, String weekDay, int classId, String startTime, String endTime, String courseDate) throws BllException
    {
        try
        {
            return dalFacade.getSpecificCourse(courseName, weekDay, classId, startTime, endTime, courseDate);
        } catch (DalException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
    
    /**
     * Gets a list start and end times of courses based on the courseDate and classId
     * @param courseDate
     * @param classId
     * @return getStartEndTime from the 
     * @throws BllException 
     */
    @Override
    public List<Course> getStartEndTime(String courseDate, int classId) throws BllException
    {
        try
        {
            return dalFacade.getStartEndTime(courseDate, classId);
        } catch (DalException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
    
    /**
     * Gets a list of all class names
     * @return getAllClassNames from the dalFacade
     * @throws BllException 
     */
    @Override
    public List<String> getAllClassNames() throws BllException
    {
        try
        {
            return dalFacade.getAllClassNames();
        }
        catch (DalException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
    
    /**
     * Gets a list of all course Ids 
     * @return getAllCourseIds from the dalFacade
     * @throws BllException 
     */
    @Override
    public List<Course> getAllCourseIds() throws BllException
    {
        try
        {
            return dalFacade.getAllCourseIds();
        } catch (DalException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
    
    /**
     * Gets attendance from a student based on studentId and courseId
     * @param studentId
     * @param courseId
     * @return getAttendance from the dalFacade
     * @throws BllException 
     */
    @Override
    public int getAttendance(int studentId, int courseId) throws BllException
    {
        try
        {
            return dalFacade.getAttendance(studentId, courseId);
        } catch (DalException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
    
    /**
     * Gets a courseId int based on a few parameters
     * @param courseDate
     * @param classId
     * @param startTime
     * @return getCourseId from the dalFacade
     * @throws BllException 
     */
    @Override
    public int getCourseId(String courseDate, int classId, String startTime) throws BllException
    {
        try
        {
            return dalFacade.getCourseId(courseDate, classId, startTime);
        } catch (DalException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
    
    /**
     * Gets an int of the student Id based on the student name
     * @param studentName
     * @return getStudentId from the dalFacade
     * @throws BllException 
     */
    @Override
    public int getStudentId(String studentName) throws BllException
    {
        try
        {
            return dalFacade.getStudentId(studentName);
        } catch (DalException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
    
    /**
     * Creates attendance based on courseId, studentId and attended parameters
     * @param courseId
     * @param studentId
     * @param attended
     * @return createAttendance from the dalFacade
     * @throws BllException 
     */
    @Override
    public boolean createAttendance(int courseId, int studentId, int attended) throws BllException
    {
        try
        {
            return dalFacade.createAttendance(courseId, studentId, attended);
        } catch (DalException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
    
    /**
     * Gets an int of the course attendance based on courseId and studentId
     * @param courseId
     * @param studentId
     * @return getAttendanceFromCourses from the dalFacade
     * @throws BllException 
     */
    @Override
    public int getAttendanceFromCourses(int courseId, int studentId) throws BllException
    {
        try
        {
            return dalFacade.getAttendanceFromCourses(courseId, studentId);
        } catch (DalException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
    
    /**
     * Gets a list of all students in a class based on class name
     * @param className
     * @return getAllStudentsClass from the dalFacade
     * @throws BllException
     * @throws ParseException 
     */
    @Override
    public List<Student> getAllStudentsClass(String className) throws BllException, ParseException
    {
        try
        {
            return dalFacade.getAllStudentsClass(className);
        } catch (DalException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
    
    /**
     * Gets a list of students in a class based on classId
     * @param classId
     * @return getStudentClass from the dalFacade
     * @throws BllException 
     */
    @Override
    public List<Student> getStudentClass(int classId) throws BllException
    {
        try {
            return dalFacade.getStudentClass(classId);
        } catch (DalException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
    
    /**
     * Checks the student credentials based on studentEmail and studentPassword parameters
     * @param studentEmail
     * @param studentPassword
     * @return checkStudentCredentials from the dalFacade
     * @throws BllException 
     */
    @Override
    public boolean checkStudentCredentials(String studentEmail, String studentPassword) throws BllException
    {
        try
        {
            return dalFacade.checkStudentCredentials(studentEmail, studentPassword);
        } catch (DalException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
    
    /**
     * Gets a list of students based on studentEmail
     * @param studentEmail
     * @return getStudent from the dalFacade
     * @throws BllException 
     */
    @Override
    public List<Student> getStudent(String studentEmail) throws BllException
    {
        try
        {
            return dalFacade.getStudent(studentEmail);
        } catch (DalException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
    
    /**
     * Gets specific student based on studentEmail
     * @param studentEmail
     * @return getSpecificStudent from the dalFacade
     * @throws BllException 
     */
    @Override
    public Student getSpecificStudent(String studentEmail) throws BllException
    {
        try
        {
            return dalFacade.getSpecificStudent(studentEmail);
        } catch (DalException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
    
    /**
     * Updates the attendance percentage based on attendance and studentId
     * @param attendance
     * @param studentId
     * @return updateAttendancePercentage from the dalFacade
     * @throws BllException 
     */
    @Override
    public boolean updateAttendancePercentage(double attendance, int studentId) throws BllException
    {
        try
        {
            return dalFacade.updateAttendancePercentage(attendance, studentId);
        } catch (DalException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
    
    /**
     * Checks teacher login credentials based on teacher email and teacher password
     * @param teacherEmail
     * @param teacherPassword
     * @return checkTeacherCredentials from the dalFacade
     * @throws BllException 
     */
    @Override
    public boolean checkTeacherCredentials(String teacherEmail, String teacherPassword) throws BllException
    {
        try
        {
            return dalFacade.checkTeacherCredentials(teacherEmail, teacherPassword);
        } catch (DalException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
    
    /**
     * Gets a list of teachers based on teacher email
     * @param teacherEmail
     * @return getTeacher from the dalFacade
     * @throws BllException 
     */
    @Override
    public List<Teacher> getTeacher(String teacherEmail) throws BllException
    {
        try
        {
            return dalFacade.getTeacher(teacherEmail);
        } catch (DalException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
    
    /**
     * Get specific teacher based on teacher email
     * @param teacherEmail
     * @return getSpecificTeacher from the dalFacade
     * @throws BllException 
     */
    @Override
    public Teacher getSpecificTeacher(String teacherEmail) throws BllException
    {
        try
        {
            return dalFacade.getSpecificTeacher(teacherEmail);
        } catch (DalException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
    
    /**
     * Updates attendance based on attendance, studentId and courseId
     * @param attendance
     * @param studentId
     * @param courseId
     * @return updateAttendance from the dalFacade
     * @throws BllException 
     */
    @Override
    public boolean updateAttendance(int attendance, int studentId, int courseId) throws BllException
    {
        try 
        {
            return dalFacade.updateAttendance(attendance, studentId, courseId);
        } catch (DalException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
}
