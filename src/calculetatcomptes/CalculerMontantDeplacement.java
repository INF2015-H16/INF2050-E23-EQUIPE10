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
 * Class CalculerMontantDeplacement:
 Description: classe qui sert à calculer le montant pour le déplacement 
              des interventions pour les clients selon le type d'employé
 * @author Luz Enith Suarez Gonzalez
 */
public class CalculerMontantDeplacement {

    private String codeClient;
    private double montantDeplacement;

  
    public CalculerMontantDeplacement(String codeClient, double montantDeplacement) {
        this.codeClient = codeClient;
        this.montantDeplacement = montantDeplacement;
    }


    public String getCodeClient() {
        return codeClient;
    }
    
    public double getMontantDeplacement() {
        return montantDeplacement;
    }
    
  
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

    // Methode calculerMontantTotal:
    // Description: calcule le montant pour le déplacement du client selon le type d'émploye
    // RETOUR: double, montant total pour le déplacement de chaque client 
    // IN: 
    //     double montantRegulier : montant régulier du client
    //     double distanceDeplacement : distance de déplacement du client
    //     int type : type d'employé
    private static double calculerMontantTotal(double montantRegulier, double distanceDeplacement, int type) {
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
     
    // Methode calculerMontantType:
    // Description: crée un tableau avec le code du client et leur distance total de 
    //              déplacement à partir de la liste unique
    //              il parcourt les deux tableaux: montantReguliere (avec le montant régulière)  
    //              et deplacementClient (avec la distance de déplacement), s'ils ont le
    //              même code du client il calcule le montant pour déplacement et l'ajoute dans 
    //              le tableaux le montant avec le respective code du client
    // RETOUR: CalculerMontantDeplacement[], tableau avec les codes du client et leur montant pour
    //         le déplacement
    // IN: 
    //     ArrayList<Entry<String, Integer>> listeUnique : liste des clients et leur distance 
    //                                                     total de deplacement
    //     int type : type d'employé
    private static CalculerMontantDeplacement[] calculerMontantType(ArrayList<Entry<String, Integer>> listeUnique, int type) throws IOException, ClassExceptions  {
        double montantTotal, montantReg, montantDep;
        String codeClMonReg, codeClMonDep;
        
        CalculerMontantDeplacement[] deplacementClient = new CalculerMontantDeplacement[listeUnique.size()];
        CalculerMontantDeplacement[] montant = new CalculerMontantDeplacement[listeUnique.size()];

        ObjetMontantRegulier[] montantReguliere;
        montantReguliere = CalculMontantRegulier.calculMontant();
      
        int compteur = 0;

        for (Entry<String, Integer> b : listeUnique) {
            deplacementClient[compteur] = new CalculerMontantDeplacement(b.getKey(), b.getValue());
            compteur++;
        }

        for (int i = 0; i < montantReguliere.length; i++) {
            for (CalculerMontantDeplacement montantDepl : deplacementClient) {
                codeClMonReg = montantReguliere[i].getCodeClient();
                codeClMonDep = montantDepl.getCodeClient();
                montantReg = montantReguliere[i].getMontantRegulier();
                montantDep = montantDepl.getMontantDeplacement();
                if (codeClMonReg.equalsIgnoreCase(codeClMonDep)) {
                    montantTotal = calculerMontantTotal(montantReg, montantDep, type);
                    montant[i] = new CalculerMontantDeplacement(codeClMonReg, montantTotal);
                }
            }
        }
        return montant;
    }
 
    // Methode calculerMontanDeplacement:
    // Description: recupere un employé et la liste des interventions du fichier JSON
    //              recupere la liste unique des codes du client et leur distance total de deplacement     
    //              calcule le montant pour déplacement pour chaque code du client selon le 
    //              type d'émploye
    // RETOUR: CalculerMontantDeplacement[], tableau avec les codes du client et leur montant pour
    //         le déplacement
     /**
     * @return 
     * @throws java.io.IOException
     * @throws calculetatcomptes.ClassExceptions
     */
    public static CalculerMontantDeplacement[] calculerMontanDeplacement() throws IOException, ClassExceptions  {

        Employe employe;
        employe = GestionEmploye.creerEmployeFromJson();

        ArrayList<Intervention> interventions;
        interventions = employe.getInterventions();

        ArrayList<Entry<String, Integer>> listeUnique;
        listeUnique = listeClientsUnique(interventions);

        CalculerMontantDeplacement[] calculMontant;
        calculMontant = calculerMontantType(listeUnique, employe.getTypeEmploye());

        return calculMontant;
    }
}
