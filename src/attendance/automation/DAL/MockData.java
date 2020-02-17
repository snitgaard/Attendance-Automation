/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.DAL;

import attendance.automation.BE.Student;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jigzi
 */
public class MockData {

    private List<Student> l1 = new ArrayList<>();
    
    public MockData() {
        Student s1 = new Student(1, "Kasper", "sick@Email.com", "SCO");
        Student s2 = new Student(2, "Christian", "sickest@emaillest.com", "SCO");
        Student s3 = new Student(3, "Mads", "Bestest@email.com", "SCO");
        Student s4 = new Student(4, "Troels", "betterest@email.com", "SCO");
        
        l1.add(s1);
        l1.add(s2);
        l1.add(s3);
        l1.add(s4);
    }
    
    public List<Student> getAllData()
    {
        return l1;
    }
}
