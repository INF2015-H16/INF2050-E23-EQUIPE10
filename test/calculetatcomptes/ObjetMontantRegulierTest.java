/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculetatcomptes;

import calculetatcomptes.ObjetMontantRegulier;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luz Suarez
 */
public class ObjetMontantRegulierTest {
    
    static ObjetMontantRegulier instance;
    
    public ObjetMontantRegulierTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new ObjetMontantRegulier("C456", 10.0);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCodeClient method, of class ObjetMontantRegulier.
     */
    @Test
    public void testGetCodeClient() {
        System.out.println("getCodeClient");
        String expResult = "C456";
        String result = instance.getCodeClient();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMontantRegulier method, of class ObjetMontantRegulier.
     */
    @Test
    public void testGetMontantRegulier() {
        System.out.println("getMontantRegulier");
        double expResult = 10.0;
        double result = instance.getMontantRegulier();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setCodeClient method, of class ObjetMontantRegulier.
     */
    @Test
    public void testSetCodeClient() {
        System.out.println("setCodeClient");
        String codeClient = "C111";
        instance.setCodeClient(codeClient);
        String expResult = "C111";
        String result = instance.getCodeClient();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMontantRegulier method, of class ObjetMontantRegulier.
     */
    @Test
    public void testSetMontantRegulier() {
        System.out.println("setMontantRegulier");
        double montantRegulier = 0.0;       
        instance.setMontantRegulier(montantRegulier);
        double expResult = 0.0;
        double result = instance.getMontantRegulier();
        assertEquals(expResult, result, 0.0);
    }   
}
