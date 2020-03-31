/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BE;

/**
 * @author jigzi
 */
public class Course
{

    private int courseId;
    private String courseName;
    private String weekDay;
    private int classId;
    private String startTime;
    private String endTime;
    private String courseDate;

    public Course(int courseId, String courseName, String weekDay, int classId, String startTime, String endTime, String courseDate)
    {
        this.courseId = courseId;
        this.courseName = courseName;
        this.weekDay = weekDay;
        this.classId = classId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Course(String startTime, String endTime, String courseName)
    {
        this.startTime = startTime;
        this.endTime = endTime;
        this.courseName = courseName;
    }


    public String getCourseDate()
    {
        return courseDate;
    }

    public void setCourseDate(String courseDate)
    {
        this.courseDate = courseDate;
    }


    public String getWeekDay()
    {
        return weekDay;
    }

    public void setWeekDay(String weekDay)
    {
        this.weekDay = weekDay;
    }

    public int getClassId()
    {
        return classId;
    }

    public void setClassId(int classId)
    {
        this.classId = classId;
    }


    public int getCourseId()
    {
        return courseId;
    }

    public void setCourseId(int courseId)
    {
        this.courseId = courseId;
    }

    public String getCourseName()
    {
        return courseName;
    }

    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public String getEndTime()
    {
        return endTime;
    }

    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }

    @Override
    public String toString()
    {
        return startTime + " - " + endTime + ": " + courseName;
    }

}
