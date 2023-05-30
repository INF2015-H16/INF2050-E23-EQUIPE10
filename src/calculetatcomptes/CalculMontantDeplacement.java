/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculetatcomptes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

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

    private static ArrayList<Entry<String, Integer>> listeClientsUnique(ArrayList<Intervention> interventions) {
        String codeClient;
        Integer distance;
        HashMap<String, Integer> mapUnique = new HashMap<>();

        for (Intervention i : interventions) {
            codeClient = i.getCodeClient();
            distance = i.getDistanceDeplacement();
            if (mapUnique.containsKey(codeClient)) {
                distance += mapUnique.get(codeClient);
                mapUnique.put(codeClient, distance);
            } else {
                mapUnique.put(codeClient, distance);
            }
        }

        List<String> uniqueList = mapUnique.keySet().stream().collect(Collectors.toList());
        Set<Map.Entry<String, Integer>> entrySet
                = mapUnique.entrySet();
        ArrayList<Map.Entry<String, Integer>> listeUnique;
        listeUnique = new ArrayList<>(entrySet);

        return listeUnique;
    }

    private static double calculMontantTotal(double montantRegulier, double montantDeplacement1, int par) {
        double montantTotal = 0;
        switch (par) {
            case 0:
                montantTotal = 200.0 - (montantDeplacement1 * (5 / 100.0) * montantRegulier);
                break;
            case 1:
                montantTotal = 200.0 - (montantDeplacement1 * (10 / 100.0) * montantRegulier);
                break;
            case 2:
                montantTotal = 200.0 - (montantDeplacement1 * (15 / 100.0) * montantRegulier);
                break;
            default:
                break;
        }
        return montantTotal;
    }

    private static CalculMontantDeplacement[] calculMontantType(ArrayList<Entry<String, Integer>> listeUnique, int par) throws IOException {
        double montantTotal, montantA, montantB;
        String codeA, codeB;
        
        CalculMontantDeplacement[] deplacement = new CalculMontantDeplacement[listeUnique.size()];
        CalculMontantDeplacement[] montant = new CalculMontantDeplacement[listeUnique.size()];

        ObjetMontantRegulier[] montantReguliere;
        montantReguliere = CalculMontantRegulier.calculMontant();
        /*
        ObjetMontantRegulier[] montantReguliere = {new ObjetMontantRegulier("C123", 1000), //montant reguliere pour test
            new ObjetMontantRegulier("C456", 1000), new ObjetMontantRegulier("C789", 1000)};
        */
        int compteur = 0;

        for (Entry<String, Integer> b : listeUnique) {
            deplacement[compteur] = new CalculMontantDeplacement(b.getKey(), b.getValue());
            compteur++;
        }

        for (int i = 0; i < montantReguliere.length; i++) {
            for (CalculMontantDeplacement montantDepl1 : deplacement) {
                codeA = montantReguliere[i].getCodeClient();
                codeB = montantDepl1.getCodeClient();
                montantA = montantReguliere[i].getMontantRegulier();
                montantB = montantDepl1.getMontantDeplacement();
                if (codeA.equalsIgnoreCase(codeB)) {
                    montantTotal = calculMontantTotal(montantA, montantB, par);
                    montant[i] = new CalculMontantDeplacement(codeA, montantTotal);
                }
            }
        }

        return montant;
    }

    /**
     *
     * @return @throws java.io.IOException
     */
    public static CalculMontantDeplacement[] calculMontanDeplacement() throws IOException {

        Employe employe;
        employe = GestionEmploye.RecupererJson();

        ArrayList<Intervention> interventions;
        interventions = employe.getInterventions();

        ArrayList<Entry<String, Integer>> listeUnique;
        listeUnique = listeClientsUnique(interventions);

        CalculMontantDeplacement[] calculMontant;
        calculMontant = calculMontantType(listeUnique, employe.getTypeEmploye());

        return calculMontant;
    }
}