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
    
    public Student(int id, String name, String email, String course, double attendance, String date)
    {
        super(id, name, email, course);
        this.attendance = attendance;
        this.date = date;
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

    
    
    
}
