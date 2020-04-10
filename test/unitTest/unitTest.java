/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitTest;

import attendance.automation.gui.Model.StudentModel;
import attendance.automation.gui.Model.TeacherModel;
import static attendance.automation.gui.controller.LoginController.encryptThisString;
import attendance.automation.gui.utilities.Checker;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author The Cowboys
 */
public class unitTest {

    private Checker checker;
    private StudentModel studentModel;
    private TeacherModel teacherModel;

    public unitTest() {
        checker = new Checker();
        teacherModel = new TeacherModel();
        studentModel = new StudentModel();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    private boolean throwException() {
        throw new IllegalArgumentException();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckIfIPIsCorrect() throws UnknownHostException {
        System.out.println("attendanceAutomationTest:TestIfCorrectIP");

        if (checker.checker() == true) {
            assertFalse(throwException());
        } else {
            assertTrue(false);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLoginCredentials() throws UnknownHostException {
        System.out.println("attendanceAutomationTest:TestLoginCredentials");

        String username = "mads@easv.dk";
        String password = encryptThisString("Mads");

        try {
            if (studentModel.checkStudentCredentials(username, password)) {
                assertFalse(throwException());
            }
            if (teacherModel.checkTeacherCredentials(username, password)) {
                assertFalse(throwException());
            }

        } catch (SQLException ex) {
            assertTrue(false);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfEncryptWorks() throws UnknownHostException {
        System.out.println("attendanceAutomationTest:testIfEncryptWorks");
        String password = encryptThisString("Mads");

        if (password == "Mads") {
            assertTrue(false);
        } else {
            assertFalse(throwException());
        }
    }
}
