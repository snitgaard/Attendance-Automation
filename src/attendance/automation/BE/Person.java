/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BE;

/**
 * Person BE Class
 *
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

    /**
     * Gets the Id
     *
     * @return id
     */
    public int getId()
    {
        return id;
    }

    /**
     * Sets the id
     *
     * @param id
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Gets the name
     *
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name
     *
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Gets the email
     *
     * @return
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Sets the email
     *
     * @param email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * toString method for the person class
     *
     * @return
     */
    @Override
    public String toString()
    {
        return name;
    }
}
