/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculetatcomptes;

import java.io.IOException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author lesuareg
 */
public class CalculMontantDeplacementTest {

    public CalculMontantDeplacementTest() {
    }

    /**
     * Test of calcul method, of class CalculMontantDeplacement.
     */
    @Test
    public void testCalcul() throws IOException {
         
        String json = FileReader.loadFileIntoString("data/employesTest.json", "UTF-8");
        JSONArray employes = JSONArray.fromObject(json);
        
        System.out.println("calcul"); 
        
        JSONArray expResult = new JSONArray();
        JSONArray testClient = new JSONArray();
        
        JSONObject client = new JSONObject();
        JSONObject employe = new JSONObject();
               
        employe.put("matricule_employe", "123456789");
        client.put("code_client", "C123");
        client.put("etat_par_client", 196);
        testClient.add(client);
        client.put("code_client", "C456");
        client.put("etat_par_client", 200);
        testClient.add(client);
        client.put("code_client", "C789");
        client.put("etat_par_client", 200);
        testClient.add(client);
        employe.put("clients", testClient);
        expResult.add(employe);
        testClient.clear();
        
        employe.put("matricule_employe", "123456790");
        client.put("code_client", "C123");
        client.put("etat_par_client", 195);
        testClient.add(client);
        client.put("code_client", "C456");
        client.put("etat_par_client", 200);
        testClient.add(client);
        client.put("code_client", "C789");
        client.put("etat_par_client", 200);
        testClient.add(client);
        employe.put("clients", testClient);
        expResult.add(employe);
              
        JSONArray result = CalculMontantDeplacement.calcul(employes);
        
        System.out.println("Resultat experé" + expResult);
        System.out.println("Resultat obtenu" + result);

        assertEquals(expResult, result);       
    }

}
