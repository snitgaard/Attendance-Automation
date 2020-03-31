/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BE;

/**
 * @author jigzi
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

    public int getClassesId()
    {
        return classesId;
    }

    public void setClassesId(int classesId)
    {
        this.classesId = classesId;
    }

    public String getClassesName()
    {
        return classesName;
    }

    public void setClassesName(String classesName)
    {
        this.classesName = classesName;
    }

    @Override
    public String toString()
    {
        return classesName;
    }
    
    
}
