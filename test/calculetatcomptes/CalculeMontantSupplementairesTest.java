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
 * @author 256SSD
 */
public class CalculeMontantSupplementairesTest {
    
    public CalculeMontantSupplementairesTest() {
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
     * Test of verifierLeCodeClient method, of class CalculeMontantSupplementaires.
     */
    @Test
    public void testVerifierLeCodeClient() {
        System.out.println("verifierLeCodeClient");
        String code = "";
        String[] tab = null;
        boolean expResult = false;
        boolean result = CalculeMontantSupplementaires.verifierLeCodeClient(code, tab);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculeMontantSupplementaire method, of class CalculeMontantSupplementaires.
     */
    @Test
    public void testCalculeMontantSupplementaire() throws Exception {
        System.out.println("calculeMontantSupplementaire");
        CalculeMontantSupplementaires[] tableau = null;
        CalculeMontantSupplementaires[] expResult = null;
        CalculeMontantSupplementaires[] result = CalculeMontantSupplementaires.calculeMontantSupplementaire(tableau);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCodeClient method, of class CalculeMontantSupplementaires.
     */
    @Test
    public void testSetCodeClient() {
        System.out.println("setCodeClient");
        String codeClient = "";
        CalculeMontantSupplementaires instance = new CalculeMontantSupplementaires();
        instance.setCodeClient(codeClient);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMontantHeuresSupp method, of class CalculeMontantSupplementaires.
     */
    @Test
    public void testSetMontantHeuresSupp() {
        System.out.println("setMontantHeuresSupp");
        double MontantHeuresSupp = 0.0;
        CalculeMontantSupplementaires instance = new CalculeMontantSupplementaires();
        instance.setMontantHeuresSupp(MontantHeuresSupp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCodeClient method, of class CalculeMontantSupplementaires.
     */
    @Test
    public void testGetCodeClient() {
        System.out.println("getCodeClient");
        CalculeMontantSupplementaires instance = new CalculeMontantSupplementaires();
        String expResult = "";
        String result = instance.getCodeClient();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMontantHeuresSupp method, of class CalculeMontantSupplementaires.
     */
    @Test
    public void testGetMontantHeuresSupp() {
        System.out.println("getMontantHeuresSupp");
        CalculeMontantSupplementaires instance = new CalculeMontantSupplementaires();
        double expResult = 0.0;
        double result = instance.getMontantHeuresSupp();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
