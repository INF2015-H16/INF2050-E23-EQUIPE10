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
 * @author lesuareg
 */
public class CalculMontantDeplacement {

    private String codeClient;
    private double montantDeplacement;

    //Constructeur
    public CalculMontantDeplacement(String codeClient, double montantDeplacement) {
        this.codeClient = codeClient;
        this.montantDeplacement = montantDeplacement;
    }

    //Getters
    public String getCodeClient() {
        return codeClient;
    }
    
    public double getMontantDeplacement() {
        return montantDeplacement;
    }
    
    //Setters
    public void setCodeClient(String codeClient) {
        this.codeClient = codeClient;
    }

    public void setMontantDeplacement(double montantDeplacement) {
        this.montantDeplacement = montantDeplacement;
    }

    // Methode listeClientsUnique:
    // Description: cree un map avec la liste de clients et la distance de deplacement,
    //              si le code de client se repete, il ajoute cette distance de 
    //              deplacement à la distance de deplacement qui est déjà dans le map. 
    //              Le map est converti dans un array list qui contien le code du client 
    //              et la distance de deplacement total pour ce client
    // RETOUR: ArrayList<Entry<String, Integer>>, liste des codes du client et leur distance 
    //     total de deplacement
    // IN: 
    //     ArrayList<Intervention> interventions : liste des interventions 
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

    // Methode calculMontantTotal:
    // Description: calcule le montant pour le déplacement du client selon le type d'émploye
    // RETOUR: double, montant total pour le déplacement de chaque client 
    // IN: 
    //     double montantRegulier : montant régulier du client
    //     double distanceDeplacement : distance de déplacement du client
    //     int type : type d'employé
    private static double calculMontantTotal(double montantRegulier, double distanceDeplacement, int type) {
        double montantTotal = 0;
        switch (type) {
            case 0:
                montantTotal = 200.0 - (distanceDeplacement * (5 / 100.0) * montantRegulier);
                break;
            case 1:
                montantTotal = 200.0 - (distanceDeplacement * (10 / 100.0) * montantRegulier);
                break;
            case 2:
                montantTotal = 200.0 - (distanceDeplacement * (15 / 100.0) * montantRegulier);
                break;
            default:
                break;
        }
        return montantTotal;
    }
     
    // Methode calculMontantType:
    // Description: crée un tableau avec le code du client et leur distance total de 
    //              déplacement à partir de la liste unique
    //              il parcourt les deux tableaux: montantReguliere (avec le montant régulière)  
    //              et deplacementClient (avec la distance de déplacement), s'ils ont le
    //              même code du client il calcule le montant pour déplacement et l'ajoute dans 
    //              le tableaux le montant avec le respective code du client
    // RETOUR: CalculMontantDeplacement[], tableau avec les codes du client et leur montant pour
    //         le déplacement
    // IN: 
    //     ArrayList<Entry<String, Integer>> listeUnique : liste des clients et leur distance 
    //                                                     total de deplacement
    //     int type : type d'employé
    private static CalculMontantDeplacement[] calculMontantType(ArrayList<Entry<String, Integer>> listeUnique, int type) throws IOException {
        double montantTotal, montantA, montantB;
        String codeA, codeB;
        
        CalculMontantDeplacement[] deplacementClient = new CalculMontantDeplacement[listeUnique.size()];
        CalculMontantDeplacement[] montant = new CalculMontantDeplacement[listeUnique.size()];

        ObjetMontantRegulier[] montantReguliere;
        montantReguliere = CalculMontantRegulier.calculMontant();
        /*
        ObjetMontantRegulier[] montantReguliere = {new ObjetMontantRegulier("C123", 1000), //montant reguliere pour test
            new ObjetMontantRegulier("C456", 1000), new ObjetMontantRegulier("C789", 1000)};
        */
        int compteur = 0;

        for (Entry<String, Integer> b : listeUnique) {
            deplacementClient[compteur] = new CalculMontantDeplacement(b.getKey(), b.getValue());
            compteur++;
        }

        for (int i = 0; i < montantReguliere.length; i++) {
            for (CalculMontantDeplacement montantDepl : deplacementClient) {
                codeA = montantReguliere[i].getCodeClient();
                codeB = montantDepl.getCodeClient();
                montantA = montantReguliere[i].getMontantRegulier();
                montantB = montantDepl.getMontantDeplacement();
                if (codeA.equalsIgnoreCase(codeB)) {
                    montantTotal = calculMontantTotal(montantA, montantB, type);
                    montant[i] = new CalculMontantDeplacement(codeA, montantTotal);
                }
            }
        }
        return montant;
    }
 
    // Methode calculMontanDeplacement:
    // Description: recupere un employé et la liste des interventions du fichier JSON
    //              recupere la liste unique des codes du client et leur distance total de deplacement     
    //              calcule le montant pour déplacement pour chaque code du client selon le 
    //              type d'émploye
    // RETOUR: CalculMontantDeplacement[], tableau avec les codes du client et leur montant pour
    //         le déplacement
     /**
     * @return 
     * @throws java.io.IOException
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