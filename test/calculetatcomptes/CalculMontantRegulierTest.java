/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculetatcomptes;

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
    }
    
    @Test
    public void testVerifierCodesClient(){
        System.out.println("VerifierCodesClient");
        String codeClient = "c123";
        String [] tab = {"c123","d455"};
        boolean expResult =true;
         boolean result = CalculMontantRegulier.verifierCodesClient(codeClient, tab) ;
         assertEquals(expResult, result);
        
        
    }
    
}
