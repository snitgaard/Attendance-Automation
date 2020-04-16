/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BE;

/**
 * Student BE Class
 * @author The Cowboys
 */
public class Student extends Person
{

    private double attendance;
    private int semester;
    private String studentPassword;
    private String studentEducation;
    private int classId;

    public Student(int id, String name, String email, int classId, double attendance, int semester, String studentPassword, String studentEducation)
    {
        super(id, name, email);
        this.attendance = attendance;
        this.semester = semester;
        this.studentPassword = studentPassword;
        this.studentEducation = studentEducation;
        this.classId = classId;
    }

    public Student(int id)
    {
        super(id);
    }
    
    /**
     * Gets the classId
     * @return classId
     */
    public int getClassId()
    {
        return classId;
    }
    
    /**
     * Sets classId
     * @param classId 
     */
    public void setClassId(int classId)
    {
        this.classId = classId;
    }
    
    /**
     * Gets the student education
     * @return studentEducation
     */
    public String getStudentEducation()
    {
        return studentEducation;
    }
    
    /**
     * Sets the student education
     * @param studentEducation 
     */
    public void setStudentEducation(String studentEducation)
    {
        this.studentEducation = studentEducation;
    }
    
    /**
     * Gets the student password
     * @return 
     */
    public String getStudentPassword()
    {
        return studentPassword;
    }
    
    /**
     * Sets the student password
     * @param studentPassword 
     */
    public void setStudentPassword(String studentPassword)
    {
        this.studentPassword = studentPassword;
    }
    
    /**
     * Gets the attendance
     * @return attendance
     */
    public double getAttendance()
    {
        return attendance;
    }
    
    /**
     * Sets the attendance
     * @param attendance 
     */
    public void setAttendance(double attendance)
    {
        this.attendance = attendance;
    }
    
    /**
     * Gets the semester
     * @return semester
     */
    public int getSemester()
    {
        return semester;
    }
    
    /**
     * Sets the semester
     * @param semester 
     */
    public void setSemester(int semester)
    {
        this.semester = semester;
    }
}
