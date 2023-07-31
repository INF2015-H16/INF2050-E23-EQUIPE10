/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculetatcomptes;

//import static calculetatcomptes.CalculerMontantDeplacement.listeClientsUnique;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Luz Enith Suarez Gonzalez
 */
public class CalculMontantDeplacementTest {

    /**
     * Test de la méthode calculerMontanDeplacement, 
 de la classe CalculerMontantDeplacement.
     * @throws java.lang.Exception
     */
    
    @Test
    public void testCalculMontanDeplacement() throws Exception {
    System.out.println("calculMontanDeplacement");

        CalculerMontantDeplacement[] expResult = {new CalculerMontantDeplacement("C123", -707.1999999999999),
            new CalculerMontantDeplacement("C456", 27.200000000000017), new CalculerMontantDeplacement("C789", -26.799999999999983)};

        CalculerMontantDeplacement[] result = {new CalculerMontantDeplacement("C123", -707.1999999999999),
            new CalculerMontantDeplacement("C456", 27.200000000000017), new CalculerMontantDeplacement("C789", -26.799999999999983)};

        System.out.println("Tableau de résultats attendus:");
        for (CalculerMontantDeplacement expResult1 : expResult) {
            System.out.println("Code du client: " + expResult1.getCodeClient() + " Montant pour le déplacement: " + expResult1.getMontantDeplacement());
        }
        
        System.out.println("Tableau de résultats:");
        for (CalculerMontantDeplacement result1 : result) {
            System.out.println("Code du client: " + result1.getCodeClient() + " Montant pour le déplacement: " + result1.getMontantDeplacement());
        }
        
        for (int i = 0; i < result.length; i++) {
            assertEquals(expResult[i].getCodeClient(), result[i].getCodeClient());
            assertEquals(expResult[i].getMontantDeplacement(), result[i].getMontantDeplacement());
        }       
    }
}
     

