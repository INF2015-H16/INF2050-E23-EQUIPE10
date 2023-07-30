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
public class EmployeTest {
    
    static Employe instance;
    
    public EmployeTest() {
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
        Intervention[] tabIntervention = {new Intervention ("C123", 4, 0, 4,"2023-04-14"), new Intervention ("C123", 4, 0, 4,"2023-04-14")};
        
        ArrayList<Intervention> interventions = new ArrayList<>();
        Collections.addAll(interventions, tabIntervention);
        
        //int matricule, int typeEmploye, double tauxMin, double tauxMax, ArrayList<Intervention> interventions        
        instance = new Employe (1234, 1, 10.2, 20.0, interventions);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setMatricule method, of class Employe.
     */
    @Test
    public void testSetMatricule() {
        System.out.println("setMatricule");        
        int matricule = 7777;        
        instance.setMatricule(matricule);
        int expResult = 7777;        
        int result = instance.getMatricule();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTypeEmploye method, of class Employe.
     */
    @Test
    public void testSetTypeEmploye() {
        System.out.println("setTypeEmploye");
        int typeEmploye = 2;        
        instance.setTypeEmploye(typeEmploye);
        int expResult = 2;        
        int result = instance.getTypeEmploye();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTauxMin method, of class Employe.
     */
    @Test
    public void testSetTauxMin() {
        System.out.println("setTauxMin");
        double tauxMin = 0.0;
        instance.setTauxMin(tauxMin);
        double expResult = 0.0;        
        double result = instance.getTauxMin();
        Assert.assertEquals(expResult, result, 0);
    }

    /**
     * Test of setTauxMax method, of class Employe.
     */
    @Test
    public void testSetTauxMax() {
        System.out.println("setTauxMax");
        double tauxMax = 1000000000.0;
        instance.setTauxMax(tauxMax);
        double expResult = 1000000000.0;        
        double result = instance.getTauxMax();
        Assert.assertEquals(expResult, result, 0);
    }
    
        /**
     * Test of setInterventions method, of class Employe.
     */
    @Test
    public void testSetInterventions() {
        System.out.println("setInterventions");
        
        //String codeClient, int distanceDeplacement, int overtime, int nombresHeures, String dateIntervention
        Intervention[] tabintervent_1 = {new Intervention ("C456", 10, 5, 15,"2023-06-20"), new Intervention ("C456", 10, 5, 15,"2023-06-20")};
        ArrayList<Intervention> intervent_1 = new ArrayList<>();
        Collections.addAll(intervent_1, tabintervent_1);
      
        instance.setInterventions(intervent_1);
        
        Intervention[] tabExpResult = {new Intervention ("C456", 10, 5, 15,"2023-06-20"), new Intervention ("C456", 10, 5, 15,"2023-06-20")};
        ArrayList<Intervention> expResult = new ArrayList<>();
        Collections.addAll(expResult, tabExpResult);
        
        ArrayList<Intervention> result = instance.getInterventions();
               
        for (int i = 0; i < expResult.size(); i++) {
           assertEquals(expResult.get(i).getCodeClient(), result.get(i).getCodeClient());
           assertEquals(expResult.get(i).getDistanceDeplacement(), result.get(i).getDistanceDeplacement());
           assertEquals(expResult.get(i).getOvertime(), result.get(i).getOvertime());
           assertEquals(expResult.get(i).getNombresHeures(), result.get(i).getNombresHeures());
           assertEquals(expResult.get(i).getDateIntervention(), result.get(i).getDateIntervention());
        }         
    }

    /**
     * Test of getMatricule method, of class Employe.
     */
    @Test
    public void testGetMatricule() {
        System.out.println("getMatricule");        
        int expResult = 1234;
        int result = instance.getMatricule();
        assertEquals(expResult, result);       
    }

    /**
     * Test of getTypeEmploye method, of class Employe.
     */
    @Test
    public void testGetTypeEmploye() {
        System.out.println("getTypeEmploye");
        int expResult = 1;
        int result = instance.getTypeEmploye();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTauxMin method, of class Employe.
     */
    @Test
    public void testGetTauxMin() {
        System.out.println("getTauxMin");
        double expResult = 10.2;
        double result = instance.getTauxMin();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getTauxMax method, of class Employe.
     */
    @Test
    public void testGetTauxMax() {
        System.out.println("getTauxMax");
        double expResult = 20.0;
        double result = instance.getTauxMax();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getInterventions method, of class Employe.
     */
    @Test
    public void testGetInterventions() {
        System.out.println("getInterventions");
        
        Intervention[] tabExpResult = {new Intervention ("C123", 4, 0, 4,"2023-04-14"), new Intervention ("C123", 4, 0, 4,"2023-04-14")};
        ArrayList<Intervention> expResult = new ArrayList<>();
        Collections.addAll(expResult, tabExpResult);
                     
        ArrayList<Intervention> result = instance.getInterventions();

        for (int i = 0; i < expResult.size(); i++) {
           assertEquals(expResult.get(i).getCodeClient(), result.get(i).getCodeClient());
           assertEquals(expResult.get(i).getDistanceDeplacement(), result.get(i).getDistanceDeplacement());
           assertEquals(expResult.get(i).getOvertime(), result.get(i).getOvertime());
           assertEquals(expResult.get(i).getNombresHeures(), result.get(i).getNombresHeures());
           assertEquals(expResult.get(i).getDateIntervention(), result.get(i).getDateIntervention());
        }              
    }   
}
