/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BE;

import java.util.Date;

/**
 *
 * @author jigzi
 */
public class Course {
    
    private int courseId;
    private String courseName;
    private String weekDay;
    private String selectClass;
    private int courseLength;
    private Date courseDate;

    public Course(int courseId, String courseName, String weekDay, String selectClass, int courseLength, Date courseDate)
    {
        this.courseId = courseId;
        this.courseName = courseName;
        this.weekDay = weekDay;
        this.selectClass = selectClass;
        this.courseLength = courseLength;
    }

    public Date getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(Date courseDate) {
        this.courseDate = courseDate;
    }
    
    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getSelectClass() {
        return selectClass;
    }

    public void setSelectClass(String selectClass) {
        this.selectClass = selectClass;
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

    public int getCourseLength()
    {
        return courseLength;
    }

    public void setCourseLength(int courseLength)
    {
        this.courseLength = courseLength;
    }

    @Override
    public String toString()
    {
        return getSelectClass();
    }

}
