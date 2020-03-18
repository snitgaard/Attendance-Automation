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
<<<<<<< HEAD
<<<<<<< HEAD
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
=======
public class Student extends Person {
>>>>>>> 3714392a1ab0f5798ca6c9cd7ceae86eaaa5b539
=======
public class Student extends Person {
>>>>>>> 1ce33cf96a8c32fe1c9ffc90b96c28c82caac68d
    
    public Student(int id, String name) {
        this.setName(name);
        this.setId(id);
    }
}
