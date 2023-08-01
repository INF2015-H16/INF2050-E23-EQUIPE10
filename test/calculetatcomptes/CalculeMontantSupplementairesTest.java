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

public class CalculeMontantSupplementairesTest {
    
    static CalculeMontantSupplementaires instance;
    
    public CalculeMontantSupplementairesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new CalculeMontantSupplementaires("C345", 10.0);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setCodeClient method, of class CalculeMontantSupplementaires.
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
     * Test of setMontantHeuresSupp method, of class CalculeMontantSupplementaires.
     */
    @Test
    public void testSetMontantHeuresSupp() {
        System.out.println("setMontantHeuresSupp");
        double MontantHeuresSupp = 0.0;      
        instance.setMontantHeuresSupp(MontantHeuresSupp);
        double expResult = 0.0;
        double result = instance.getMontantHeuresSupp();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getCodeClient method, of class CalculeMontantSupplementaires.
     */
    @Test
    public void testGetCodeClient() {
        System.out.println("getCodeClient");       
        String expResult = "C345";
        String result = instance.getCodeClient();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMontantHeuresSupp method, of class CalculeMontantSupplementaires.
     */
    @Test
    public void testGetMontantHeuresSupp() {
        System.out.println("getMontantHeuresSupp");        
        double expResult = 10.0;
        double result = instance.getMontantHeuresSupp();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of calculeMontantSupplementaire method, of class CalculeMontantSupplementaires.
     */
    @Test
    public void testCalculeMontantSupplementaire() throws Exception {
        System.out.println("calculeMontantSupplementaire");
        
        //String codeClient, int distanceDeplacement, int overtime, int nombresHeures, String dateIntervention
        Intervention[] tabIntervention = {new Intervention("C123", 4, 0, 4, "2023-04-14"), new Intervention("C456", 2, 1, 8, "2023-03-15"),
            new Intervention("C789", 3, 3, 7, "2023-03-20"), new Intervention("C123", 8, 3, 3, "2023-04-20")};

        ArrayList<Intervention> interventions = new ArrayList<>();
        Collections.addAll(interventions, tabIntervention);

        //int matricule, int typeEmploye, double tauxMin, double tauxMax, ArrayList<Intervention> interventions        
        Employe employe = new Employe(123456789, 2, 35.45, 72.0, interventions);

        CalculeMontantSupplementaires[] result = CalculeMontantSupplementaires.calculeMontantSupplementaire(employe);

        CalculeMontantSupplementaires[] expResult = {new CalculeMontantSupplementaires("C123", 1050.0),
            new CalculeMontantSupplementaires("C456", 225.0), new CalculeMontantSupplementaires("C789", 750.0)};

        for (int i = 0; i < expResult.length; i++) {
            assertEquals(expResult[i].getCodeClient(), result[i].getCodeClient());
            assertEquals(expResult[i].getMontantHeuresSupp(), result[i].getMontantHeuresSupp());
        }
    }   
}
