/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculetatcomptes;

import static calculetatcomptes.ClientTest.instance;
import static junit.framework.Assert.assertEquals;
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
public class InterventionTest {
    
    static Intervention instance;
    
    public InterventionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        //String codeClient, int distanceDeplacement, int overtime, int nombresHeures, String dateIntervention
        instance = new Intervention("C123", 4, 0, 4,"2023-04-14");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setCodeClient method, of class Intervention.
     */
    @Test
    public void testSetCodeClient() {
        System.out.println("setCodeClient");
        String codeClient = "C444";
        instance.setCodeClient(codeClient);
        String expResult = "C444";
        String result = instance.getCodeClient();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDistanceDeplacement method, of class Intervention.
     */
    @Test
    public void testSetDistanceDeplacement() {
        System.out.println("setDistanceDeplacement");
        int distanceDeplacement = 0;
        instance.setDistanceDeplacement(distanceDeplacement);
        int expResult = 0;
        int result = instance.getDistanceDeplacement();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOvertime method, of class Intervention.
     */
    @Test
    public void testSetOvertime() {
        System.out.println("setOvertime");
        int overtime = 0;
        instance.setOvertime(overtime);
        int expResult = 0;
        int result = instance.getOvertime();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNombresHeures method, of class Intervention.
     */
    @Test
    public void testSetNombresHeures() {
        System.out.println("setNombresHeures");
        int nombresHeures = 0;
        instance.setNombresHeures(nombresHeures);
        int expResult = 0;
        int result = instance.getNombresHeures();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDateIntervention method, of class Intervention.
     */
    @Test
    public void testSetDateIntervention() {
        System.out.println("setDateIntervention");
        String dateIntervention = "2023-05-05";
        instance.setDateIntervention(dateIntervention);
        String expResult = "2023-05-05";
        String result = instance.getDateIntervention();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDistanceDeplacement method, of class Intervention.
     */
    @Test
    public void testGetDistanceDeplacement() {
        System.out.println("getDistanceDeplacement");
        int expResult = 4;
        int result = instance.getDistanceDeplacement();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCodeClient method, of class Intervention.
     */
    @Test
    public void testGetCodeClient() {
        System.out.println("getCodeClient");
        String expResult = "C123";
        String result = instance.getCodeClient();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOvertime method, of class Intervention.
     */
    @Test
    public void testGetOvertime() {
        System.out.println("getOvertime");
        int expResult = 0;
        int result = instance.getOvertime();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNombresHeures method, of class Intervention.
     */
    @Test
    public void testGetNombresHeures() {
        System.out.println("getNombresHeures");
        int expResult = 4;
        int result = instance.getNombresHeures();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDateIntervention method, of class Intervention.
     */
    @Test
    public void testGetDateIntervention() {
        System.out.println("getDateIntervention");
        String expResult = "2023-04-14";
        String result = instance.getDateIntervention();
        assertEquals(expResult, result);
    }    
}
