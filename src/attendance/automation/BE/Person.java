/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BE;

/**
 * @author The Cowboys
 */
public abstract class Person
{

    protected int id;
    protected String name;
    protected String email;

    public Person(int id, String name, String email)
    {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Person(int id)
    {
        this.id = id;
    }

    public Person(String name)
    {
        this.name = name;
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

    @Override
    public String toString()
    {
        return name;
    }


}
