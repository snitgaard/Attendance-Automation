/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL;

import attendance.automation.BE.Student;
import attendance.automation.DAL.MockData;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jigzi
 */
public class MockDataBuisness {
    
    private MockData mockData = new MockData();

    public List<Student> getAllData()
    {
        try {
            return mockData.getAllData();
        } catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
        
    }
   
   
}
