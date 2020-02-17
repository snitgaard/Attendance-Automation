/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.Model;

import attendance.automation.BE.Student;
import attendance.automation.BLL.MockDataBuisness;
import java.util.List;

/**
 *
 * @author jigzi
 */
public class StudentAttendanceModel {
    
    private MockDataBuisness mockDataBuisness;
    
    public List<Student> getAllData()
    {
        try {
            return mockDataBuisness.getAllData();
        } catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
        
    }
}
