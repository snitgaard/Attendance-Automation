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
    
    /**
     * Gets a list of all classes from the database
     * @return list of all classes
     * @throws DalException 
     */
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

    /**
     * Gets a classId int from the database
     * @param classesName
     * @return classId int
     * @throws DalException 
     */
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

    /**
     * Gets a class name string from the database
     * @param classesId
     * @return getClassName from the database
     * @throws DalException 
     */
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

    /**
     * Gets a list of all courses from the database
     * @return list of all courses
     * @throws DalException 
     */
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

    /**
     * Creates a course in the database and returns true if a row was added
     * @param courseName
     * @param weekDay
     * @param startTime
     * @param endTime
     * @param classId
     * @param courseDate
     * @return createCourse boolean method, true if row was affected, false if not
     * @throws DalException 
     */
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

    /**
     * Gets an int of the getAllCourseDates method from the database that counts 
     * the amount of courses on a day
     * @param courseDate
     * @param classId
     * @return getAllCourseDates from the courseDAO
     * @throws DalException 
     */
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

    /**
     * Gets specific course based on a list of parameters
     * @param courseName
     * @param weekDay
     * @param classId
     * @param startTime
     * @param endTime
     * @param courseDate
     * @return specific course from the courseDAO method
     * @throws DalException 
     */
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

    /**
     * Gets a list of all start and end times of the courses based on courseDate and classId
     * @param courseDate
     * @param classId
     * @return getstartEndTime method from the courseDAO
     * @throws DalException 
     */
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

    /**
     * Gets a list of all course ids
     * @return all course ids
     * @throws DalException 
     */
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

    /**
     * Gets the attendance based on studentId and courseId
     * @param studentId
     * @param courseId
     * @return getAttendance from the studentCourseDAO
     * @throws DalException 
     */
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
    
    /**
     * Gets a boolean on updateAttendance based on attendance, studentId and courseId parameters
     * @param attendance
     * @param studentId
     * @param courseId
     * @return true if attendance was updated, false if not from the studentCourseDAO
     * @throws DalException 
     */
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

    /**
     * Gets a courseId int based on courseDate, classId and startTime
     * @param courseDate
     * @param classId
     * @param startTime
     * @return getCourseId from studentCourseDAO 
     * @throws DalException 
     */
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

    /**
     * Gets studentId based on studentName
     * @param studentName
     * @return getStudentId from the studentCourseDAO
     * @throws DalException 
     */
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

    /**
     * Creates attendance and returns a boolean based on courseId, studentId and attended
     * @param courseId
     * @param studentId
     * @param attended
     * @return createAttendance method that gives true if a row was added or affected, false if not
     * @throws DalException 
     */
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

    /**
     * Gets attendance from courses based on courseId and studentId
     * @param courseId
     * @param studentId
     * @return getAttendanceFromCourses from the studentCourseDAO
     * @throws DalException 
     */
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

    /**
     * Gets a list of students based on a string className
     * @param className
     * @return list of all students
     * @throws DalException
     * @throws ParseException 
     */
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

    /**
     * Checks if the student credentials matches a database entry based on studentEmail and studentPassword
     * @param studentEmail
     * @param studentPassword
     * @return checkStudentCredentials method in the studentDAO
     * @throws DalException 
     */
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

    /**
     * Gets a list of students based on studentEmail
     * @param studentEmail
     * @return getStudent from studentDAO
     * @throws DalException 
     */
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

    /**
     * Gets specific student from studentEmail
     * @param studentEmail
     * @return getSpecificStudent from the studentDAO
     * @throws DalException 
     */
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

    /**
     * Checks the teacher credentials to see if it matches the database entry
     * @param teacherEmail
     * @param teacherPassword
     * @return true if it matches, false if not
     * @throws DalException 
     */
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

    /**
     * Gets a list of teachers based on a teacherEmail string
     * @param teacherEmail
     * @return getTeacher list from the teacherDAO
     * @throws DalException 
     */
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

    /**
     * Gets a specific teacher based on teacherEmail; gets index 0 of the getTeacher method in the database
     * @param teacherEmail
     * @return gets the first teacher entry from the teacherDAO method getTeacher
     * @throws DalException 
     */
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

    /**
     * Get a list of all class names
     * @return list of all class names
     * @throws DalException 
     */
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

    /**
     * Gets a list of all students in a class based on classId
     * @param classId
     * @return getStudentClass list
     * @throws DalException 
     */
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

    /**
     * Updates the attendance percentage based on attendance and studentId
     * @param attendance
     * @param studentId
     * @return true if attendance was updated, false if not
     * @throws DalException 
     */
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
