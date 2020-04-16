/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BE;

/**
 * Teacher BE Class
 * @author The Cowboys
 */
public class Teacher extends Person
{

    private String teacherPassword;
    private int courseId;
    private int classId;

    public Teacher(int id, String name, String email, int courseId, String teacherPassword, int classId)
    {
        super(id, name, email);
        this.courseId = courseId;
        this.teacherPassword = teacherPassword;
        this.classId = classId;
    }
    
    /**
     * Get the classId
     * @return classId
     */
    public int getClassId()
    {
        return classId;
    }
    
    /**
     * Sets the classId
     * @param classId 
     */
    public void setClassId(int classId)
    {
        this.classId = classId;
    }
    
    /**
     * Gets the courseId
     * @return courseId
     */
    public int getCourseId()
    {
        return courseId;
    }
    
    /**
     * Sets the courseId
     * @param courseId 
     */
    public void setCourseId(int courseId)
    {
        this.courseId = courseId;
    }
    
    /**
     * Gets the teacher password
     * @return teacherPassword
     */
    public String getTeacherPassword()
    {
        return teacherPassword;
    }
    
    /**
     * Sets the teacherPassword
     * @param teacherPassword 
     */
    public void setTeacherPassword(String teacherPassword)
    {
        this.teacherPassword = teacherPassword;
    }
    
    /**
     * Gets the id
     * @return id
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * Sets the id
     * @param id 
     */
    public void setId(int id)
    {
        this.id = id;
    }
    
    /**
     * Gets the name
     * @return name 
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Sets the name
     * @param name 
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Gets the email
     * @return email
     */
    public String getEmail()
    {
        return email;
    }
    
    /**
     * Sets the email
     * @param email 
     */
    public void setEmail(String email)
    {
        this.email = email;
    }


}
