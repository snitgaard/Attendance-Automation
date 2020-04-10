/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.DAL;

import attendance.automation.BE.Course;
import attendance.automation.BE.Student;
import attendance.automation.BE.Teacher;
import attendance.automation.DAL.database.ClassesDAO;
import attendance.automation.DAL.database.CourseDAO;
import attendance.automation.DAL.database.StudentCourseDAO;
import attendance.automation.DAL.database.StudentDAO;
import attendance.automation.DAL.database.TeacherDAO;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author CSnit
 */
public class DalManager implements DalFacade
{

    private final ClassesDAO classesDAO;
    private final CourseDAO courseDAO;
    private final StudentDAO studentDAO;
    private final TeacherDAO teacherDAO;
    private final StudentCourseDAO studentCourseDAO;

    public DalManager() throws IOException
    {
        classesDAO = new ClassesDAO();
        courseDAO = new CourseDAO();
        studentDAO = new StudentDAO();
        teacherDAO = new TeacherDAO();
        studentCourseDAO = new StudentCourseDAO();
    }

    @Override
    public List<String> getAllClasses() throws DalException
    {
        try
        {
            return classesDAO.getAllClasses();
        } catch (DalException ex)
        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public int getClassId(String classesName) throws DalException
    {
        try
        {
            return classesDAO.getClassId(classesName);
        } catch (DalException ex)

        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public String getClassName(int classesId) throws DalException
    {
        try
        {
            return classesDAO.getClassName(classesId);
        } catch (DalException ex)

        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public List<Course> getAllCourses() throws DalException
    {
        try
        {
            return courseDAO.getAllCourses();
        } catch (DalException ex)

        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public boolean createCourse(String courseName, String weekDay, String startTime, String endTime, int classId, String courseDate) throws DalException
    {
        try
        {
            return courseDAO.createCourse(courseName, weekDay, startTime, endTime, classId, courseDate);
        } catch (DalException ex)

        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public int getAllCourseDates(String courseDate, int classId) throws DalException
    {
        try
        {
            return courseDAO.getAllCourseDates(courseDate, classId);
        } catch (DalException ex)

        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public int getSpecificCourse(String courseName, String weekDay, int classId, String startTime, String endTime, String courseDate) throws DalException
    {
        try
        {
            return courseDAO.getSpecificCourse(courseName, weekDay, classId, startTime, endTime, courseDate);
        } catch (DalException ex)

        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public List<Course> getStartEndTime(String courseDate, int classId) throws DalException
    {
        try
        {
            return courseDAO.getStartEndTime(courseDate, classId);
        } catch (DalException ex)

        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public List<Course> getAllCourseIds() throws DalException
    {
        try
        {
            return courseDAO.getAllCourseIds();
        } catch (DalException ex)

        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public int getAttendance(int studentId, int courseId) throws DalException
    {
        try
        {
            return studentCourseDAO.getAttendance(studentId, courseId);
        } catch (DalException ex)

        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public boolean updateAttendance(int attendance, int studentId, int courseId) throws DalException
    {
        try
        {
            return studentCourseDAO.updateAttendance(attendance, studentId, courseId);
        } catch (DalException ex)

        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public int getCourseId(String courseDate, int classId, String startTime) throws DalException
    {
        try
        {
            return studentCourseDAO.getCourseId(courseDate, classId, startTime);
        } catch (DalException ex)

        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public int getStudentId(String studentName) throws DalException
    {
        try
        {
            return studentCourseDAO.getStudentId(studentName);
        } catch (DalException ex)

        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public boolean createAttendance(int courseId, int studentId, int attended) throws DalException
    {
        try
        {
            return studentCourseDAO.createAttendance(courseId, studentId, attended);
        } catch (DalException ex)

        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public int getAttendanceFromCourses(int courseId, int studentId) throws DalException
    {
        try
        {
            return studentCourseDAO.getAttendanceFromCourses(courseId, studentId);
        } catch (DalException ex)

        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public List<Student> getAllStudentsClass(String className) throws DalException, ParseException
    {
        try
        {
            return studentDAO.getAllStudentsClass(className);
        } catch (DalException ex)
        {
            throw new DalException(ex.getMessage());
        } catch (ParseException exx)
        {
            throw new DalException(exx.getMessage());
        }
    }

    @Override
    public boolean checkStudentCredentials(String studentEmail, String studentPassword) throws DalException
    {
        try
        {
            return studentDAO.checkStudentCredentials(studentEmail, studentPassword);
        } catch (DalException ex)

        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public List<Student> getStudent(String studentEmail) throws DalException
    {
        try
        {
            return studentDAO.getStudent(studentEmail);
        } catch (DalException ex)

        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public Student getSpecificStudent(String studentEmail) throws DalException
    {
        try
        {
            return studentDAO.getSpecificStudent(studentEmail);
        } catch (DalException ex)

        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public boolean checkTeacherCredentials(String teacherEmail, String teacherPassword) throws DalException
    {
        try
        {
            return teacherDAO.checkTeacherCredentials(teacherEmail, teacherPassword);
        } catch (DalException ex)

        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public List<Teacher> getTeacher(String teacherEmail) throws DalException
    {
        try
        {
            return teacherDAO.getTeacher(teacherEmail);
        } catch (DalException ex)

        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public Teacher getSpecificTeacher(String teacherEmail) throws DalException
    {
        try
        {
            return teacherDAO.getSpecificTeacher(teacherEmail);
        } catch (DalException ex)

        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public List<String> getAllClassNames() throws DalException
    {
        try
        {
            return courseDAO.getAllClassNames();
        } catch (DalException ex)
        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public List<Student> getStudentClass(int classId) throws DalException
    {
        try
        {
            return studentDAO.getStudentClass(classId);
        } catch (DalException ex)
        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public boolean updateAttendancePercentage(double attendance, int studentId) throws DalException
    {
        try
        {
            return studentDAO.updateAttendancePercentage(attendance, studentId);
        } catch (DalException ex)
        {
            throw new DalException(ex.getMessage());
        }
    }
}
