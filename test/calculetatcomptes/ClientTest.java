/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculetatcomptes;

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
public class ClientTest {

    static Client instance;

    public ClientTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        //String codeClient, double etatParClient
        instance = new Client("C123", 10.0);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setCodeClient method, of class Client.
     */
    @Test
    public void testSetCodeClient() {
        System.out.println("setCodeClient");
        String codeClient = "C777";
        instance.setCodeClient(codeClient);
        String expResult = "C777";
        String result = instance.getCodeClient();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEtatParClient method, of class Client.
     */
    @Test
    public void testSetEtatParClient() {
        System.out.println("setEtatParClient");
        double etatParClient = 0.0;
        instance.setEtatParClient(etatParClient);
        double expResult = 0.0;
        double result = instance.getEtatParClient();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCodeClient method, of class Client.
     */
    @Test
    public void testGetCodeClient() {
        System.out.println("getCodeClient");
        String expResult = "C123";
        String result = instance.getCodeClient();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEtatParClient method, of class Client.
     */
    @Test
    public void testGetEtatParClient() {
        System.out.println("getEtatParClient");
        double expResult = 10.0;
        double result = instance.getEtatParClient();
        assertEquals(expResult, result, 0.0);
    }
}

