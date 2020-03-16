/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BE;

import attendance.automation.BE.Person;

/**
 *
 * @author jigzi
 */
public class Student extends Person {
    
    public Student(int id, String name) {
        this.setName(name);
        this.setId(id);
    }
}
