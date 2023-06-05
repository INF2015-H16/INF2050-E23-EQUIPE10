/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculetatcomptes;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author seif saidi
 */
public class ObjetMontantRegulierTest {
    
    public ObjetMontantRegulierTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetCodeClient() {
        System.out.println("getCodeClient");
        ObjetMontantRegulier instance = new ObjetMontantRegulier("c123",52.22);
        String expResult = "c123";
        String result = instance.getCodeClient();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetMontantRegulier() {
        System.out.println("getMontantRegulier");
        ObjetMontantRegulier instance = new ObjetMontantRegulier("c123",52.22);
        double expResult = 52.22;
        double result = instance.getMontantRegulier();
        assertEquals(expResult, result,0.22);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    @Test
    public void testSetCodeClient() {
         System.out.println("setCodeClient");
         String codeClient = "c322";
        ObjetMontantRegulier instance = new ObjetMontantRegulier("c123",52.22);
        instance.setCodeClient(codeClient);
        String expResult = "c322";
        String result = instance.getCodeClient();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    @Test
    public void testSetMontantRegulier() {
         System.out.println("setMontantRegulier");
         double montantReg = 322.25;
        ObjetMontantRegulier instance = new ObjetMontantRegulier("c123",52.22);
        instance.setMontantRegulier(montantReg);
        double expResult = 322.25;
        double result = instance.getMontantRegulier();
        assertEquals(expResult, result,0.25);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    
    }
    
}
