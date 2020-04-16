/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BE;

/**
 * Course BE class
 *
 * @author The Cowboys
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
        this.courseDate = courseDate;
    }

    public Course(String startTime, String endTime, String courseName)
    {
        this.startTime = startTime;
        this.endTime = endTime;
        this.courseName = courseName;
    }

    public Course(int courseId, String courseDate)
    {
        this.courseId = courseId;
        this.courseDate = courseDate;
    }

    /**
     * Gets the course date
     *
     * @return courseDate
     */
    public String getCourseDate()
    {
        return courseDate;
    }

    /**
     * Sets the course date
     *
     * @param courseDate
     */
    public void setCourseDate(String courseDate)
    {
        this.courseDate = courseDate;
    }

    /**
     * Gets the weekday
     *
     * @return weekDay
     */
    public String getWeekDay()
    {
        return weekDay;
    }

    /**
     * Sets the weekday
     *
     * @param weekDay
     */
    public void setWeekDay(String weekDay)
    {
        this.weekDay = weekDay;
    }

    /**
     * Gets class Id
     *
     * @return classId
     */
    public int getClassId()
    {
        return classId;
    }

    /**
     * Sets the class Id
     *
     * @param classId
     */
    public void setClassId(int classId)
    {
        this.classId = classId;
    }

    /**
     * Gets the course Id
     *
     * @return courseId
     */
    public int getCourseId()
    {
        return courseId;
    }

    /**
     * Sets the course Id
     *
     * @param courseId
     */
    public void setCourseId(int courseId)
    {
        this.courseId = courseId;
    }

    /**
     * Gets the course name
     *
     * @return courseName
     */
    public String getCourseName()
    {
        return courseName;
    }

    /**
     * Sets the course name
     *
     * @param courseName
     */
    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    /**
     * Gets the start time of a course
     *
     * @return startTime
     */
    public String getStartTime()
    {
        return startTime;
    }

    /**
     * Sets the start time
     *
     * @param startTime
     */
    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    /**
     * Gets the end time of a course
     *
     * @return endTime
     */
    public String getEndTime()
    {
        return endTime;
    }

    /**
     * Sets the end time
     *
     * @param endTime
     */
    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }

    /**
     * toString method for the GUI
     *
     * @return
     */
    @Override
    public String toString()
    {
        return startTime + " - " + endTime + ": " + courseName;
    }
}
