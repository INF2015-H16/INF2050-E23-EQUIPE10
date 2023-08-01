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
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Luz Suarez
 */
public class EtatEmployeTest {

    static EtatEmploye instance;

    public EtatEmployeTest() {
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
        Client[] tabClient = {new Client("C123", 858.0), new Client("C456", 817.0), new Client("C789", 1219.5)};
        ArrayList<Client> clients = new ArrayList<>();
        Collections.addAll(clients, tabClient);

        //int matriculeEmploye, double etatCompte, double coutFixe, double coutVariable, ArrayList<Client> clients = new ArrayList<>();      
        instance = new EtatEmploye(123456789, 3628.25, 43.55, 90.70, clients);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setMatriculeEmploye method, of class EtatEmploye.
     */
    @Test
    public void testSetMatriculeEmploye() {
        System.out.println("setMatriculeEmploye");
        int matriculeEmploye = 1111;
        instance.setMatriculeEmploye(matriculeEmploye);
        int expResult = 1111;
        int result = instance.getMatriculeEmploye();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEtatCompte method, of class EtatEmploye.
     */
    @Test
    public void testSetEtatCompte() {
        System.out.println("setEtatCompte");
        double etatCompte = 0.0;
        instance.setEtatCompte(etatCompte);
        double expResult = 0.0;
        double result = instance.getEtatCompte();
        Assert.assertEquals(expResult, result, 0);
    }

    /**
     * Test of setCoutFixe method, of class EtatEmploye.
     */
    @Test
    public void testSetCoutFixe() {
        System.out.println("setCoutFixe");
        double coutFixe = 0.0;
        instance.setCoutFixe(coutFixe);
        double expResult = 0.0;
        double result = instance.getCoutFixe();
        Assert.assertEquals(expResult, result, 0);
    }

    /**
     * Test of setCoutVariable method, of class EtatEmploye.
     */
    @Test
    public void testSetCoutVariable() {
        System.out.println("setCoutVariable");
        double coutVariable = 0.0;
        instance.setCoutVariable(coutVariable);
        double expResult = 0.0;
        double result = instance.getCoutVariable();
        Assert.assertEquals(expResult, result, 0);
    }

    /**
     * Test of getMatriculeEmploye method, of class EtatEmploye.
     */
    @Test
    public void testGetMatriculeEmploye() {
        System.out.println("getMatriculeEmploye");
        int expResult = 123456789;
        int result = instance.getMatriculeEmploye();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEtatCompte method, of class EtatEmploye.
     */
    @Test
    public void testGetEtatCompte() {
        System.out.println("getEtatCompte");
        double expResult = 3628.25;
        double result = instance.getEtatCompte();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getCoutFixe method, of class EtatEmploye.
     */
    @Test
    public void testGetCoutFixe() {
        System.out.println("getCoutFixe");
        double expResult = 43.55;
        double result = instance.getCoutFixe();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getCoutVariable method, of class EtatEmploye.
     */
    @Test
    public void testGetCoutVariable() {
        System.out.println("getCoutVariable");
        double expResult = 90.70;
        double result = instance.getCoutVariable();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setClients method, of class EtatEmploye.
     */
    @Test
    public void testSetClients() {
        System.out.println("setClients");

        //String codeClient, double etatParClient
        Client[] tabClient_1 = {new Client("C555", 444.0), new Client("C666", 111.0), new Client("C222", 333.0)};
        ArrayList<Client> clients_1 = new ArrayList<>();
        Collections.addAll(clients_1, tabClient_1);

        instance.setClients(clients_1);

        Client[] tabExpResult = {new Client("C555", 444.0), new Client("C666", 111.0), new Client("C222", 333.0)};
        ArrayList<Client> expResult = new ArrayList<>();
        Collections.addAll(expResult, tabExpResult);

        ArrayList<Client> result = instance.getClients();

        for (int i = 0; i < expResult.size(); i++) {
            assertEquals(expResult.get(i).getCodeClient(), result.get(i).getCodeClient());
            assertEquals(expResult.get(i).getEtatParClient(), result.get(i).getEtatParClient());
        }
    }

    /**
     * Test of getClients method, of class EtatEmploye.
     */
    @Test
    public void testGetClients() {
        System.out.println("getClients");
        
        Client[] tabExpResult = {new Client("C123", 858.0), new Client("C456", 817.0), new Client("C789", 1219.5)};
        ArrayList<Client> expResult = new ArrayList<>();
        Collections.addAll(expResult, tabExpResult);
       
        ArrayList<Client> result = instance.getClients();
        
        for (int i = 0; i < expResult.size(); i++) {
            assertEquals(expResult.get(i).getCodeClient(), result.get(i).getCodeClient());
            assertEquals(expResult.get(i).getEtatParClient(), result.get(i).getEtatParClient());
        }
    }
}
