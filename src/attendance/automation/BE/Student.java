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
    
    public Student(int attendance, int id, String name, String email, String course)
    {
        super(id, name, email, course);
        this.attendance = attendance;
        this.date = date;
    }
}
