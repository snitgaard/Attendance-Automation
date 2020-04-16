/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.Model;

import attendance.automation.BE.*;
import attendance.automation.BLL.BllException;
import attendance.automation.BLL.BllManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @author CSnit
 */
public class Model
{

    private ObservableList<String> allClasses;
    private ObservableList<Course> allCourses;
    private ObservableList<String> allClassNames;
    private ObservableList<Course> allStartEndTimes;
    private ObservableList<Student> allStudentsClass;
    private BllManager bllManager;

    public Model() throws IOException
    {
        bllManager = new BllManager();
    }

    /**
     * ObservableList that gets a list of all classes from the database using
     * FXCollections
     *
     * @return a list of all classes
     * @throws ModelException
     */
    public ObservableList<String> getAllClasses() throws ModelException
    {
        allClasses = FXCollections.observableArrayList();
        try
        {
            allClasses.addAll(bllManager.getAllClasses());
        } catch (BllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
        return allClasses;

    }

    /**
     * Gets class Id based on className from the bllManager
     *
     * @param classesName
     * @return classId from the bllManager
     * @throws ModelException
     */
    public int getClassId(String classesName) throws ModelException
    {
        try
        {
            return bllManager.getClassId(classesName);
        } catch (BllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Gets a className based on classId
     *
     * @param classesId
     * @return className from the bllManager
     * @throws ModelException
     */
    public String getClassName(int classesId) throws ModelException
    {
        try
        {
            return bllManager.getClassName(classesId);
        } catch (BllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Gets an observableList of all courses from the bllManager through
     * FXCollections
     *
     * @return a list of all courses from the bllManager
     * @throws ModelException
     */
    public ObservableList<Course> getAllCourses() throws ModelException
    {
        allCourses = FXCollections.observableArrayList();
        try
        {
            allCourses.addAll(bllManager.getAllCourses());
        } catch (BllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
        return allCourses;
    }

    /**
     * Gets an int of all course dates based on courseDate and classId
     *
     * @param courseDate
     * @param classId
     * @return getAllCourseDates from the bllManager
     * @throws ModelException
     */
    public int getAllCourseDates(String courseDate, int classId) throws ModelException
    {
        try
        {
            return bllManager.getAllCourseDates(courseDate, classId);
        } catch (BllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Creates a course entry in the database that goes through the bllManager
     * based on a list of parameters
     *
     * @param courseName
     * @param weekDay
     * @param startTime
     * @param endTime
     * @param classId
     * @param courseDate
     * @throws ModelException
     */
    public void createCourses(String courseName, String weekDay, String startTime, String endTime, int classId, String courseDate) throws ModelException
    {
        try
        {
            boolean courseIsCreated = bllManager.createCourse(courseName, weekDay, startTime, endTime, classId, courseDate);
            if (courseIsCreated)
            {
            }
        } catch (BllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Gets an ObservableList of className strings from the bllManager using
     * FXCollections
     *
     * @return the observableList of class names
     * @throws ModelException
     */
    public ObservableList<String> getAllClassNames() throws ModelException
    {
        allClassNames = FXCollections.observableArrayList();
        try
        {
            allClassNames.addAll(bllManager.getAllClassNames());
        } catch (BllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
        return allClassNames;
    }

    /**
     * Gets an ObservableList of start and end time based on courseDate and
     * classId
     *
     * @param courseDate
     * @param classId
     * @return observableList of startEndTimes
     * @throws ModelException
     */
    public ObservableList<Course> getStartEndTime(String courseDate, int classId) throws ModelException
    {
        allStartEndTimes = FXCollections.observableArrayList();
        try
        {
            allStartEndTimes.addAll(bllManager.getStartEndTime(courseDate, classId));
        } catch (BllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
        return allStartEndTimes;
    }

    /**
     * Gets an int of specificCourse based on a list of parameters
     *
     * @param courseName
     * @param weekDay
     * @param classId
     * @param startTime
     * @param endTime
     * @param courseDate
     * @return getSpecificCourse from the bllManager
     * @throws ModelException
     */
    public int getSpecificCourse(String courseName, String weekDay, int classId, String startTime, String endTime, String courseDate) throws ModelException
    {
        try
        {
            return bllManager.getSpecificCourse(courseName, weekDay, classId, startTime, endTime, courseDate);
        } catch (BllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Gets attendance from studentId and courseId
     *
     * @param studentId
     * @param courseId
     * @return getAttendance from the bllManager
     * @throws ModelException
     */
    public int getAttendance(int studentId, int courseId) throws ModelException
    {
        try
        {
            return bllManager.getAttendance(studentId, courseId);
        } catch (BllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Gets courseId from courseDate, classId and startTime
     *
     * @param courseDate
     * @param classId
     * @param startTime
     * @return getCourseId from bllManager
     * @throws ModelException
     */
    public int getCourseId(String courseDate, int classId, String startTime) throws ModelException
    {
        try
        {
            return bllManager.getCourseId(courseDate, classId, startTime);
        } catch (BllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Gets studentId from studentName
     *
     * @param studentName
     * @return getStudentId from bllManager
     * @throws ModelException
     */
    public int getStudentId(String studentName) throws ModelException
    {
        try
        {
            return bllManager.getStudentId(studentName);
        } catch (BllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Creates attendance from courseId, studentId and attended parameters
     *
     * @param courseId
     * @param studentId
     * @param attended
     * @return createAttendance method in the bllManager that returns true if a row was added, false if not
     * @throws ModelException
     */
    public boolean createAttendance(int courseId, int studentId, int attended) throws ModelException
    {
        try
        {
            return bllManager.createAttendance(courseId, studentId, attended);
        } catch (BllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Updates attendance based on attendance, studentId and courseId
     *
     * @param attendance
     * @param studentId
     * @param courseId
     * @return updateAttendance from bllManager, returns true if a row was added, false if not
     * @throws ModelException
     */
    public boolean updateAttendance(int attendance, int studentId, int courseId) throws ModelException
    {
        try
        {
            return bllManager.updateAttendance(attendance, studentId, courseId);
        } catch (BllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Gets an int of all courseIds based on courseId and studentId
     *
     * @param courseId
     * @param studentId
     * @return getAttendanceFromCourses method from bllManager
     * @throws ModelException
     */
    public int getAllCourseIds(int courseId, int studentId) throws ModelException
    {
        try
        {
            return bllManager.getAttendanceFromCourses(courseId, studentId);
        } catch (BllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Checks if student credentials match the database entry of credentials basd on studentEmail and studentPassword
     *
     * @param studentEmail
     * @param studentPassword
     * @return true if the credentials match, false if not from the bllManager
     * @throws ModelException
     */
    public boolean checkStudentCredentials(String studentEmail, String studentPassword) throws ModelException
    {
        try
        {
            return bllManager.checkStudentCredentials(studentEmail, studentPassword);
        } catch (BllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Gets a list of all students based on studentEmail
     *
     * @param studentEmail
     * @return getStudent list from the bllManager
     * @throws ModelException
     */
    public List<Student> getStudent(String studentEmail) throws ModelException
    {
        try
        {
            return bllManager.getStudent(studentEmail);
        } catch (BllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Gets a specific student based on studentEmail from the bllManager
     *
     * @param studentEmail
     * @return getSpecificStudent from the bllManager
     * @throws ModelException
     */
    public Student getSpecificStudent(String studentEmail) throws ModelException
    {
        try
        {
            return bllManager.getSpecificStudent(studentEmail);
        } catch (BllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Gets a list of students in a class based on classId
     *
     * @param classId
     * @return getStudentClass method from bllManager
     * @throws ModelException
     */
    public List<Student> getStudentClass(int classId) throws ModelException
    {
        try
        {
            return bllManager.getStudentClass(classId);
        } catch (BllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Updates the attendancePercentage based on attendance and studentId
     *
     * @param attendance
     * @param studentId
     * @return true if row was affected, false if not
     * @throws ModelException
     */
    public boolean updateAttendancePercentage(double attendance, int studentId) throws ModelException
    {
        try
        {
            return bllManager.updateAttendancePercentage(attendance, studentId);
        } catch (BllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Gets an ObservableList of all students based on className through FXCollections
     *
     * @param className
     * @return an observableList of all students
     * @throws ModelException
     * @throws ParseException
     */
    public ObservableList<Student> getAllStudentsClass(String className) throws ModelException, ParseException
    {
        allStudentsClass = FXCollections.observableArrayList();
        try
        {
            allStudentsClass.addAll(bllManager.getAllStudentsClass(className));
        } catch (BllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
        return allStudentsClass;
    }

    /**
     * Checks if the teacher credentials match if it matches with an entry in the
     * database based on teacherEmail and teacherPassword
     *
     * @param teacherEmail
     * @param teacherPassword
     * @return true if credentials match a database entry, false if not
     * @throws ModelException
     */
    public boolean checkTeacherCredentials(String teacherEmail, String teacherPassword) throws ModelException
    {
        try
        {
            return bllManager.checkTeacherCredentials(teacherEmail, teacherPassword);
        } catch (BllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Gets a specific teacher based on teacherEmail
     *
     * @param teacherEmail
     * @return specific teacher from bllManager
     * @throws ModelException
     */
    public Teacher getSpecificTeacher(String teacherEmail) throws ModelException
    {
        try
        {
            return bllManager.getSpecificTeacher(teacherEmail);
        } catch (BllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

}
