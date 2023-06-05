/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package calculetatcomptes;

import net.sf.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author lemaicirabah
 */
public class CalculEtatComptesTest {
    
    public CalculEtatComptesTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of main method, of class CalculEtatComptes.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        CalculEtatComptes.main(args);
        fail("Test main");
    }

    /**
     * Test of creationJson method, of class CalculEtatComptes.
     */
    @Test
    public void testCreationJson() throws Exception {
        System.out.println("creationJson");
        JSONObject expResult = null;
        JSONObject result = CalculEtatComptes.creationJson();
        assertEquals(expResult, result);
        fail("Test CreationJson");
    }

    /**
     * Test of ecrireFichierSortie method, of class CalculEtatComptes.
     */
    @Test
    public void testEcrireFichierSortie() throws Exception {
        System.out.println("ecrireFichierSortie");
        String args = "";
        CalculEtatComptes.ecrireFichierSortie(args);
        fail("Test EcrireFichierSortie");
    }
    
}
