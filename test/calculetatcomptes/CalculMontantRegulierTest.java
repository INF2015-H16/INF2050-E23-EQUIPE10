/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculetatcomptes;

import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author seif saidi
 */
public class CalculMontantRegulierTest {
    
    public CalculMontantRegulierTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCalculMontant() throws Exception {
         System.out.println("calculeMontantRegulier");
        ObjetMontantRegulier [] tableau = new ObjetMontantRegulier[1];
        tableau[0]= new ObjetMontantRegulier("d58",4.20);
        String expResult = "d58";
        double expResult1 = 4.20;
        String result = tableau[0].getCodeClient();
        double result1 = tableau[0].getMontantRegulier();
        assertEquals(expResult, result);
        assertEquals(expResult1, result1, 0.20);
    }
    
    @Test
    public void testVerifierCodesClient() throws IOException{
        System.out.println("VerifierCodesClient");
        String codeClient = "c123";
        String [] tab = {"c123","d455"};
        boolean expResult =true;
         boolean result = CalculMontantRegulier.verifierCodesClient(codeClient, tab) ;
         assertEquals(expResult, result);
        
        
    }
    
}
