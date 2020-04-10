/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitTest;

import attendance.automation.gui.utilities.Checker;
import java.net.UnknownHostException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jigzi
 */
public class unitTest {
    
    private Checker checker;
    
    public unitTest() {
        checker = new Checker();
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
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetInterestRateOutsideValidRange() throws UnknownHostException {
        System.out.println("BankAccountTest:testSetInterestRateOutsideValidRange");
        
//        BankAccount acc = new BankAccount(1111,1000);
        
        try {
            if (checker.checker() == true)
            acc.setInterestRate(0.11);            
        }
        finally {
            assertEquals(0.01, acc.getInterestRate(), 0);
        }
    }
}

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
