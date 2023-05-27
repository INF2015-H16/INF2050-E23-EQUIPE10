/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculetatcomptes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author lesuareg
 */
public class CalculMontantDeplacement {

    private String codeClient;
    private double montantDeplacement;

    public CalculMontantDeplacement(String codeClient, double montantDeplacement) {
        this.codeClient = codeClient;
        this.montantDeplacement = montantDeplacement;
    }

    public String getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(String codeClient) {
        this.codeClient = codeClient;
    }

    public double getMontantDeplacement() {
        return montantDeplacement;
    }

    public void setMontantDeplacement(double montantDeplacement) {
        this.montantDeplacement = montantDeplacement;
    }

    private static HashMap<String, Integer> listeClientsUnique(ArrayList<Intervention> interventions) {

        String codeClient;
        Integer distance;
        HashMap<String, Integer> listeUnique = new HashMap<String, Integer>();

        for (Intervention i : interventions) {
            codeClient = i.getCodeClient();
            distance = i.getDistanceDeplacement();
            if (listeUnique.containsKey(codeClient)) {
                distance += listeUnique.get(i);
                listeUnique.put(codeClient, distance);
            } else {
                listeUnique.put(codeClient, distance);
            }
        }
        return listeUnique;
    }

    private static CalculMontantDeplacement[] calculMontantType0(HashMap<String, Integer> listeUnique) throws IOException {

        
        CalculMontantDeplacement[] montant = null;
        

        return montant;
    }

    private static void calculMontantType1(ArrayList<Intervention> interventions) {
        
    }

    private static void calculMontantType2(ArrayList<Intervention> interventions) {
       
    }

    /**
     *
     * @throws java.io.IOException
     */
    public static void calculMontanDeplacement() throws IOException {

        Employe employe;
        employe = GestionEmploye.RecupererJson();

        ArrayList<Intervention> interventions;
        interventions = employe.getInterventions();

        HashMap<String, Integer> listeUnique;
        listeUnique = listeClientsUnique(interventions);

        switch (employe.getTypeEmploye()) {
            case 0:
                CalculMontantDeplacement[] calculMontantType0 = calculMontantType0(listeUnique); //
                break;

            case 1:
                // calculMontantType1(interventions);
                break;
            case 2:
                //calculMontantType2(interventions);
                break;
            default:
                break;
        }
    }
}
