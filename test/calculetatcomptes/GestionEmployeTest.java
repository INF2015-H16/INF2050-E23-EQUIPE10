/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculetatcomptes;

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
public class GestionEmployeTest {
    
    public GestionEmployeTest() {
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

    /**
     * Test of creerEmployeFromJson method, of class GestionEmploye.
     * @throws java.lang.Exception
     */
    /*
    @Test
    public void testCreerEmployeFromJson() throws Exception {
        System.out.println("creerEmployeFromJson");
        Employe expResult = null;
        Employe result = GestionEmploye.creerEmployeFromJson();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    
    /**
     * Test of convertirStringEnDouble method, of class GestionEmploye.
     */
    @Test
    public void testConvertirStringEnDouble() {
        System.out.println("convertirStringEnDouble");
        String chaine = "0.0";
        double expResult = 0.0;
        double result = GestionEmploye.convertirStringEnDouble(chaine);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of lireFichierEntree method, of class GestionEmploye.
     * @throws java.lang.Exception
     */
    @Test
    public void testLireFichierEntree() throws Exception {
        System.out.println("lireFichierEntree");       
        GestionEmploye test = new GestionEmploye();
        try {
            String[] args = {"test1"};
            test.lireFichierEntree(args); //GestionEmploye.lireFichierEntree(args);
        } catch (Exception e) {
            assertEquals("Fichier d'entree manquant.", e.getMessage());
        }             
    }   
}
