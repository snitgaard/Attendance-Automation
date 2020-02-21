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
 * @author The Best Group
 */
public class MockData
{

    ArrayList<Student> l1 = new ArrayList<>();

    public MockData()
    {
        Student s1 = new Student("Kasper");
        Student s2 = new Student("Christian");
        Student s3 = new Student("Mads");
        Student s4 = new Student("Troels");

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
