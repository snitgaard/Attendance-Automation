/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL;

import attendance.automation.BE.Classes;
import attendance.automation.DAL.ClassesDAO;
import attendance.automation.DAL.DalException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author The Cowboys
 */
public class ClassesManager
{
    private ClassesDAO classesDao;

    // Constructor retrieving the ClassesDAO Class.    
    public ClassesManager() throws IOException
    {
        classesDao = new ClassesDAO();
    }

    public boolean createClasses(int classesId, String classesName, int studentId, int teacherId)
    {
        return classesDao.createClasses(classesId, classesName, studentId, teacherId);
    }

    public List<String> getAllClasses()
    {
        try
        {
            return classesDao.getAllClasses();
        } catch (SQLException ex)
        {
            Logger.getLogger(CourseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void deleteClasses(Classes classes) throws DalException
    {
        classesDao.deleteClasses(classes);
    }


    public boolean updateClasses(String classesName, int classesId)
    {
        return classesDao.updateClasses(classesName, classesId);
    }

    public int getClassId(String classesName) throws SQLException
    {
        return classesDao.getClassId(classesName);
    }

    public String getClassName(int classesId) throws SQLException
    {
        return classesDao.getClassName(classesId);
    }
}
