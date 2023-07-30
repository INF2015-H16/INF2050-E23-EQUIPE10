
package calculetatcomptes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
   
    public static CalculerMontantDeplacement[] calculerMontanDeplacement(Employe employe) throws Exception, ClassExceptions {

        ArrayList<Intervention> interventions;
        interventions = employe.getInterventions();

        ArrayList<Entry<String, Integer>> listeUnique;
        listeUnique = listeClientsUnique(interventions);

        CalculerMontantDeplacement[] calculMontant;
        calculMontant = calculerMontantType(employe, listeUnique, employe.getTypeEmploye());

        return calculMontant;
    }
  
    private static ArrayList<Entry<String, Integer>> listeClientsUnique(ArrayList<Intervention> interventions) {

        HashMap<String, Integer> mapUnique = creerMapClientsUnique(interventions);

        Set<Map.Entry<String, Integer>> entrySet
                = mapUnique.entrySet();

        ArrayList<Map.Entry<String, Integer>> listeUnique;
        listeUnique = new ArrayList<>(entrySet);

        return listeUnique;
    }

    private static HashMap<String, Integer> creerMapClientsUnique(ArrayList<Intervention> interventions) {

        HashMap<String, Integer> mapUnique = new HashMap<>();

        interventions.forEach(intervention -> {
            Integer distance = intervention.getDistanceDeplacement();
            if (mapUnique.containsKey(intervention.getCodeClient())) {
                distance += mapUnique.get(intervention.getCodeClient());
                mapUnique.put(intervention.getCodeClient(), distance);
            } else {
                mapUnique.put(intervention.getCodeClient(), distance);
            }
        });

        return mapUnique;
    }

    private static CalculerMontantDeplacement[] calculerMontantType(Employe employe, ArrayList<Entry<String, Integer>> listeUnique, int type) throws Exception, ClassExceptions {

        CalculerMontantDeplacement[] deplacementClient = new CalculerMontantDeplacement[listeUnique.size()];
        ObjetMontantRegulier[] montantReguliere = CalculMontantRegulier.calculMontant(employe);

        int compteur = 0;

        for (Entry<String, Integer> client : listeUnique) {
            deplacementClient[compteur] = new CalculerMontantDeplacement(client.getKey(), client.getValue());
            compteur++;
        }

        CalculerMontantDeplacement[] montant = calculerMontantListeUnique(listeUnique, montantReguliere, deplacementClient, type);

        return montant;
    }

    private static CalculerMontantDeplacement[] calculerMontantListeUnique(ArrayList<Entry<String, Integer>> listeUnique, ObjetMontantRegulier[] montantReguliere, CalculerMontantDeplacement[] deplacementClient, int type) {

        CalculerMontantDeplacement[] montant = new CalculerMontantDeplacement[listeUnique.size()];

        for (int i = 0; i < montantReguliere.length; i++) {

            for (CalculerMontantDeplacement montantDepl : deplacementClient) {

                if (montantReguliere[i].getCodeClient().equalsIgnoreCase(montantDepl.getCodeClient())) {
                    double montantTotal = calculerMontantTotal(montantReguliere[i].getMontantRegulier(),
                            montantDepl.getMontantDeplacement(), type);
                    montant[i] = new CalculerMontantDeplacement(montantReguliere[i].getCodeClient(), montantTotal);
                }
            }
        }
        return montant;
    }

    private static double calculerMontantTotal(double montantRegulier, double distanceDeplacement, int type) {
        double montantTotal = 0;
        switch (type) {
            case 0:
                montantTotal = 200.0 - (distanceDeplacement * (5 / 100.0) * montantRegulier); //5%
                break;
            case 1:
                montantTotal = 200.0 - (distanceDeplacement * (10 / 100.0) * montantRegulier); //10%
                break;
            case 2:
                montantTotal = 200.0 - (distanceDeplacement * (15 / 100.0) * montantRegulier); //15%
                break;
            default:
                break;
        }
        return montantTotal;
    }
}
