/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package calculetatcomptes;

import java.util.ArrayList;
import net.sf.json.JSONArray;
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
public class GestionEmployeTest {
    
    public GestionEmployeTest() {
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
     * Test of RecupererJson method, of class GestionEmploye.
     */
    @Test
    public void testRecupererJson() throws Exception {
        System.out.println("RecupererJson");
        Employe expResult = null;
        Employe result = GestionEmploye.RecupererJson();
        assertEquals(expResult, result);
        fail("Test RecupererJson");
    }

    /**
     * Test of remplireInetrventions method, of class GestionEmploye.
     */
    @Test
    public void testRemplireInetrventions() {
        System.out.println("remplireInetrventions");
        JSONArray listInterventions = null;
        ArrayList<Intervention> expResult = null;
        ArrayList<Intervention> result = GestionEmploye.remplireInetrventions(listInterventions);
        assertEquals(expResult, result);
        fail("Test RemplireInetrventions");
    }

    /**
     * Test of convertirString method, of class GestionEmploye.
     */
    @Test
    public void testConvertirString() {
        System.out.println("convertirString");
        String chaine = "";
        double expResult = 0.0;
        double result = GestionEmploye.convertirString(chaine);
        assertEquals(expResult, result, 0);
        fail("Test ConvertirString");
    }
    
}
