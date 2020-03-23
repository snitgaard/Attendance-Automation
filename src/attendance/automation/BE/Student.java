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
    private int semester;
    private String studentPassword;
    private String studentEducation;
    
    public Student(int id, String name, String email, String course, double attendance, int semester, String studentPassword, String studentEducation)
    {
        super(id, name, email, course);
        this.attendance = attendance;
        this.semester = semester;
        this.studentPassword = studentPassword;
        this.studentEducation = studentEducation;
    }

    public String getStudentEducation() {
        return studentEducation;
    }

    public void setStudentEducation(String studentEducation) {
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

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }


    
    
}
