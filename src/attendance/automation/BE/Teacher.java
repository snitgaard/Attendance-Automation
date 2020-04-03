/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BE;

/**
 * @author The Best Group
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

    public int getClassId()
    {
        return classId;
    }

    public void setClassId(int classId)
    {
        this.classId = classId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTeacherPassword()
    {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword)
    {
        this.teacherPassword = teacherPassword;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }


}
