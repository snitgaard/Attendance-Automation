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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CSnit
 */
public class BllManager implements BllFacade
{

    private final DalFacade dalFacade;

    public BllManager() throws IOException
    {
        dalFacade = new DalManager();
    }

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
