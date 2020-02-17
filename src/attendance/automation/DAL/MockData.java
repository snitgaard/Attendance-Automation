/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.DAL;

import attendance.automation.BE.Student;

/**
 *
 * @author jigzi
 */
public class MockData {

    public MockData() {
        Student s1 = new Student(1, "Kasper", "sick@Email.com", "SCO");
        Student s2 = new Student(2, "Christian", "sickest@emaillest.com", "SCO");
        Student s3 = new Student(3, "Mads", "Bestest@email.com", "SCO");
        Student s4 = new Student(4, "Troels", "betterest@email.com", "SCO");
    }


    
}
