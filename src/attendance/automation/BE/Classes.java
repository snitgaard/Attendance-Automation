/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BE;

/**
 * Class BE class
 *
 * @author The Cowboys
 */
public class Classes
{

    private int classesId;
    private String classesName;

    public Classes(int classesId, String classesName)
    {
        this.classesId = classesId;
        this.classesName = classesName;
    }

    /**
     * Gets the class Id
     *
     * @return classesId
     */
    public int getClassesId()
    {
        return classesId;
    }

    /**
     * Sets the class Id
     *
     * @param classesId
     */
    public void setClassesId(int classesId)
    {
        this.classesId = classesId;
    }

    /**
     * Gets the class name
     *
     * @return classesName
     */
    public String getClassesName()
    {
        return classesName;
    }

    /**
     * Sets the class name
     *
     * @param classesName
     */
    public void setClassesName(String classesName)
    {
        this.classesName = classesName;
    }

    /**
     * toString method for setting the className
     *
     * @return classesName as toString.
     */
    @Override
    public String toString()
    {
        return classesName;
    }
}
