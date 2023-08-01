/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculetatcomptes;

import java.util.ArrayList;
import java.util.Collections;
import static junit.framework.Assert.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Luz Suarez
 */
public class CalculerMontantDeplacementTest {

    static CalculerMontantDeplacement instance;

    public CalculerMontantDeplacementTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        instance = new CalculerMontantDeplacement("C123", 10.0);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getCodeClient method, of class CalculerMontantDeplacement.
     *
     */
    @Test
    public void testGetCodeClient() {
        System.out.println("getCodeClient");
        String result = instance.getCodeClient();
        String expResult = "C123";
        assertEquals(expResult, result);
    }

    /**
     * Test of getMontantDeplacement method, of class
     * CalculerMontantDeplacement.
     */
    @Test
    public void testGetMontantDeplacement() {
        System.out.println("getMontantDeplacement");
        double expResult = 10.0;
        double result = instance.getMontantDeplacement();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setCodeClient method, of class CalculerMontantDeplacement.
     */
    @Test
    public void testSetCodeClient() {
        System.out.println("setCodeClient");
        String codeClient = "C222";
        instance.setCodeClient(codeClient);
        String expResult = "C222";
        String result = instance.getCodeClient();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMontantDeplacement method, of class
     * CalculerMontantDeplacement.
     */
    @Test
    public void testSetMontantDeplacement() {
        System.out.println("setMontantDeplacement");
        double montantDeplacement = 0.0;
        instance.setMontantDeplacement(montantDeplacement);
        double expResult = 0.0;
        double result = instance.getMontantDeplacement();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of calculerMontanDeplacement method, of class
     * CalculerMontantDeplacement.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculerMontanDeplacement() throws Exception {
        System.out.println("calculerMontanDeplacement");

        //String codeClient, int distanceDeplacement, int overtime, int nombresHeures, String dateIntervention
        Intervention[] tabIntervention = {new Intervention("C123", 4, 0, 4, "2023-04-14"), new Intervention("C456", 2, 1, 8, "2023-03-15"),
            new Intervention("C789", 3, 3, 7, "2023-03-20"), new Intervention("C123", 8, 3, 3, "2023-04-20")};

        ArrayList<Intervention> interventions = new ArrayList<>();
        Collections.addAll(interventions, tabIntervention);

        //int matricule, int typeEmploye, double tauxMin, double tauxMax, ArrayList<Intervention> interventions        
        Employe employe = new Employe(123456789, 2, 35.45, 72.0, interventions);

        CalculerMontantDeplacement[] result = CalculerMontantDeplacement.calculerMontanDeplacement(employe);

        CalculerMontantDeplacement[] expResult = {new CalculerMontantDeplacement("C123", -707.1999999999999),
            new CalculerMontantDeplacement("C456", 27.200000000000017), new CalculerMontantDeplacement("C789", -26.799999999999983)};

        for (int i = 0; i < expResult.length; i++) {
            assertEquals(expResult[i].getCodeClient(), result[i].getCodeClient());
            assertEquals(expResult[i].getMontantDeplacement(), result[i].getMontantDeplacement());
        }
    }
}
