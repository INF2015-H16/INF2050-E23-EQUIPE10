/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculetatcomptes;

/**
 *
 * @author seif saidi
 */
public class ObjetMontantRegulier {
   
    private String codeClient;
    private double montantRegulier;
       

    
    public ObjetMontantRegulier( String codeClient,double montantRegulier){
        this.codeClient=codeClient;
        this.montantRegulier=montantRegulier;
    
    }
    
    public String getCodeClient() {
        return codeClient;
    }
    
    public double getMontantRegulier() {
        return montantRegulier;
    }

    public void setCodeClient(String codeClient) {
        this.codeClient = codeClient;
    }
    
    public void setMontantRegulier(double montantRegulier) {
        this.montantRegulier = montantRegulier;
    }

   
    
}
