/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.Model;

import attendance.automation.BE.Course;
import attendance.automation.BE.Student;
import attendance.automation.BE.Teacher;
import attendance.automation.BLL.BllException;
import attendance.automation.BLL.BllManager;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
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

    //This is what the controller calls when creating a Course. This calls a method in the CourseManager
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

    public boolean checkTeacherCredentials(String teacherEmail, String teacherPassword) throws ModelException
    {
        try {
        return bllManager.checkTeacherCredentials(teacherEmail, teacherPassword);
        } catch (BllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    // This method returns a method called in the teacherManager. With a String parameter.
    public Teacher getSpecificTeacher(String teacherEmail) throws ModelException
    {
        try {
        return bllManager.getSpecificTeacher(teacherEmail);
        } catch (BllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

}
