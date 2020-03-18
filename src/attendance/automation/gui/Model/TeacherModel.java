/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.Model;

import attendance.automation.BE.Teacher;
import attendance.automation.BLL.TeacherManager;
import java.util.List;

/**
 *
 * @author jigzi
 */
public class TeacherModel {

    private TeacherManager TeacherManager = new TeacherManager();

    public List<Teacher> getAllData() {
        try {
            return TeacherManager.getAllData();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
