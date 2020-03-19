/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BE;

/**
 *
 * @author jigzi
 */
public class Course {
    
    private int courseId;
    private String courseName;
    private int weekNumber;
    private int dayNumber;
    private String courseTime;

    public Course(int courseId, String courseName, int weekNumber, int dayNumber, String courseTime)
    {
        this.courseId = courseId;
        this.courseName = courseName;
        this.weekNumber = weekNumber;
        this.dayNumber = dayNumber;
        this.courseTime = courseTime;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getWeekNumber()
    {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber)
    {
        this.weekNumber = weekNumber;
    }

    public int getDayNumber()
    {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber)
    {
        this.dayNumber = dayNumber;
    }

    public String getCourseTime()
    {
        return courseTime;
    }

    public void setCourseTime(String courseTime)
    {
        this.courseTime = courseTime;
    }
}
