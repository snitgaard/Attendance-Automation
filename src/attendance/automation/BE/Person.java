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
<<<<<<< HEAD
<<<<<<< HEAD
public abstract class Person
{

    protected int id;
    protected String name;
    protected String email;
    protected String course;

    public Person(int id, String name, String email, String course)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
=======
=======
>>>>>>> 1ce33cf96a8c32fe1c9ffc90b96c28c82caac68d
public class Person {
    private int id;
    private String name;
    private String email;
    private String course;
>>>>>>> 3714392a1ab0f5798ca6c9cd7ceae86eaaa5b539

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

    public String getCourse()
    {
        return course;
    }

    public void setCourse(String course)
    {
        this.course = course;
    }
}
