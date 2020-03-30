/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.Model;

import attendance.automation.BE.Classes;
import attendance.automation.BLL.ClassesManager;
import attendance.automation.DAL.DalException;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jigzi
 */
public class ClassesModel {
        private ObservableList<Classes> allClasses;
    private ClassesManager classesManager;

    
    public ClassesModel() throws IOException
    {
        classesManager = new ClassesManager();
    }


    public ObservableList<Classes> getAllClasses()
    {
        allClasses = FXCollections.observableArrayList();
        allClasses.addAll(classesManager.getAllClasses());
        return allClasses;
    }
    


    public void createClasses(int classesId, String classesName, int studentId, int teacherId) throws DalException
    {
        boolean classesIsCreated = classesManager.createClasses(classesId, classesName, studentId, teacherId);
        if (classesIsCreated)
        {
            System.out.println("Class Created");
        }
    }


    public void deleteClasses(Classes selectedClasses) throws DalException
    {
        classesManager.deleteClasses(selectedClasses);
        if (allClasses.remove(selectedClasses))
        {
            allClasses.remove(selectedClasses);
        }
    }

   
    public void updateClasses(String classesName, int classesId)
    {
        boolean classesIsUpdated = classesManager.updateClasses(classesName, classesId);
        if (classesIsUpdated)
        {
            System.out.println("classes Is Updated");
        }
    }
}