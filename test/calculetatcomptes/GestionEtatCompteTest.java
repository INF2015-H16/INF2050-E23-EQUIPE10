/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package calculetatcomptes;

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
public class GestionEtatCompteTest {
    
    public GestionEtatCompteTest() {
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
     * Test of RemplirObjetEtatCompte method, of class GestionEtatCompte.
     */
    @Test
    public void testRemplirObjetEtatCompte() throws Exception {
        System.out.println("RemplirObjetEtatCompte");
        EtatEmploye expResult = null;
        EtatEmploye result = GestionEtatCompte.RemplirObjetEtatCompte();
        assertEquals(expResult, result);
        fail("Test RemplirObjetEtatCompte");
    }
    
}
