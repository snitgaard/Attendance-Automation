/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitTest;

import attendance.automation.gui.Model.Model;
import attendance.automation.gui.Model.ModelException;
import static attendance.automation.gui.controller.LoginController.encryptThisString;
import attendance.automation.gui.utilities.Checker;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
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
    private Model model;
    
    public unitTest() throws IOException {
        checker = new Checker();
        model = new Model();
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
    public void testLoginCredentials() throws UnknownHostException, ModelException {
        System.out.println("attendanceAutomationTest:TestLoginCredentials");

        String username = "mads@easv.dk";
        String password = encryptThisString("Mads");

        try {
            if (model.checkStudentCredentials(username, password)) {
                assertFalse(throwException());
            }
            if (model.checkTeacherCredentials(username, password)) {
                assertFalse(throwException());
            }

        } catch (ModelException ex) {
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
