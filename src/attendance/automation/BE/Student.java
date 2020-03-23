/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BE;

import attendance.automation.BE.Person;

/**
 *
 * @author CSnit
 */
public class Student extends Person
{
    private double attendance;
    private String date;
    private int courseAttended;
    private String studentPassword;
    private String studentEducation
    
    public Student(int id, String name, String email, String course, double attendance, String date, int courseAttended, String studentPassword, String studentEducation)
    {
        super(id, name, email, course);
        this.attendance = attendance;
        this.date = date;
        this.courseAttended = courseAttended;
        this.studentPassword = studentPassword;
        this.studentEducation = studentEducation;
    }

    
    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public double getAttendance()
    {
        return attendance;
    }

    public void setAttendance(double attendance)
    {
        this.attendance = attendance;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public int getCourseAttended()
    {
        return courseAttended;
    }

    public void setCourseAttended(int courseAttended)
    {
        this.courseAttended = courseAttended;
    }
    
    

    
    
    
}
