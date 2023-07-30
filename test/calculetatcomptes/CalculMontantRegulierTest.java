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
public class CalculMontantRegulierTest {

    public CalculMontantRegulierTest() {
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
     * Test of calculMontant method, of class CalculMontantRegulier.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculMontant() throws Exception {
        System.out.println("calculMontant");

        //String codeClient, int distanceDeplacement, int overtime, int nombresHeures, String dateIntervention
        Intervention[] tabIntervention = {new Intervention("C123", 4, 0, 4, "2023-04-14"), new Intervention("C456", 2, 1, 8, "2023-03-15"),
            new Intervention("C789", 3, 3, 7, "2023-03-20"), new Intervention("C123", 8, 3, 3, "2023-04-20")};

        ArrayList<Intervention> interventions = new ArrayList<>();
        Collections.addAll(interventions, tabIntervention);

        //int matricule, int typeEmploye, double tauxMin, double tauxMax, ArrayList<Intervention> interventions        
        Employe instance = new Employe(123456789, 2, 35.45, 72.0, interventions);

        ObjetMontantRegulier[] result = CalculMontantRegulier.calculMontant(instance);

        ObjetMontantRegulier[] expResult = {new ObjetMontantRegulier("C123", 504.0),
            new ObjetMontantRegulier("C456", 576.0), new ObjetMontantRegulier("C789", 504.0)};

        for (int i = 0; i < expResult.length; i++) {
            assertEquals(expResult[i].getCodeClient(), result[i].getCodeClient());
            assertEquals(expResult[i].getMontantRegulier(), result[i].getMontantRegulier());
        }
    }
}
