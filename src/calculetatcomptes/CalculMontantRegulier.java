/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculetatcomptes;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Cette class permet de calculer le montant régulier de chaque client
 *
 * @author seif saidi
 */
public class CalculMontantRegulier {

    /**
     * @return @throws calculetatcomptes.ClassExceptions
     */
    public static ObjetMontantRegulier[] calculMontant() throws Exception, ClassExceptions {

        Employe employe = GestionEmploye.creerEmployeFromJson();

        ArrayList<Intervention> interventions = employe.getInterventions();
        String tabCodesClient[] = new String[interventions.size()];

        for (int i = 0; i < interventions.size(); i++) {
            tabCodesClient[i] = interventions.get(i).getCodeClient();
        }

        ObjetMontantRegulier tabObjetFinal[];
        tabObjetFinal = creerTableauObjetFinal(employe, tabCodesClient, interventions);

        return tabObjetFinal;
    }

    /**
     * @param employe
     * @param tabCodesClient
     * @param interventions
     * @return
     * @throws java.io.IOException
     */
    private static ObjetMontantRegulier[] creerTableauObjetFinal(Employe employe,
            String tabCodesClient[], ArrayList<Intervention> interventions) throws Exception {

        int typeEmploye = employe.getTypeEmploye();
        double tauxHoraireMin = employe.getTauxMin();
        double tauxHoraireMax = employe.getTauxMax();
        double tauxHoraireMoyen = (tauxHoraireMax + tauxHoraireMin) / 2;

        ObjetMontantRegulier tabObjetFinal[];
        tabObjetFinal = creerTableauObjetFinalType(tabCodesClient, interventions,
                typeEmploye, tauxHoraireMin, tauxHoraireMax, tauxHoraireMoyen);

        return tabObjetFinal;
    }

    /**
     * @param tabCodesClient
     * @param interventions
     * @param typeEmploye
     * @param tauxHoraireMin
     * @param tauxHoraireMax
     * @param tauxHoraireMoyen
     * @return
     * @throws java.io.IOException
     */
    private static ObjetMontantRegulier[] creerTableauObjetFinalType(String tabCodesClient[],
            ArrayList<Intervention> interventions, int typeEmploye, double tauxHoraireMin,
            double tauxHoraireMax, double tauxHoraireMoyen) throws IOException {

        ObjetMontantRegulier tabObjetFinal[] = new ObjetMontantRegulier[0];

        switch (typeEmploye) {
            case 0:
                tabObjetFinal = calculMontantReguliere(tabCodesClient, interventions, tauxHoraireMin);
                break;
            case 1:
                tabObjetFinal = calculMontantReguliere(tabCodesClient, interventions, tauxHoraireMoyen);
                break;
            case 2:
                tabObjetFinal = calculMontantReguliere(tabCodesClient, interventions, tauxHoraireMax);
                break;
        }
        return tabObjetFinal;
    }

    /**
     * @param tabCodesClient
     * @param interventions
     * @param tauxHoraire
     * @return
     * @throws java.io.IOException
     */
    private static ObjetMontantRegulier[] calculMontantReguliere(String tabCodesClient[],
            ArrayList<Intervention> interventions, double tauxHoraire) throws IOException {

        String tabPourVérifier[] = new String[interventions.size()];
        ObjetMontantRegulier[] tabObjet = new ObjetMontantRegulier[tabCodesClient.length];

        int compteur = 0;

        for (int i = 0; i < tabCodesClient.length; i++) {
            int nbHeure = 0;
            if (isNouveauCodeClient(tabCodesClient, tabPourVérifier, i)) {
                compteur++;
                double montantRegulier = montantReguliere(interventions, tabCodesClient, i, nbHeure, tauxHoraire);
                tabObjet[i] = new ObjetMontantRegulier(tabCodesClient[i], montantRegulier);
            }
            tabPourVérifier[i] = tabCodesClient[i];
        }
        ObjetMontantRegulier tabObjetFinal[] = creerTableauObjet(tabObjet, compteur);

        return tabObjetFinal;
    }

    /**
     * @param interventions
     * @param tabCodesClient
     * @param i
     * @param nbHeure
     * @param tauxHoraire
     * @return
     * @throws java.io.IOException
     */
    private static double montantReguliere(ArrayList<Intervention> interventions,
            String[] tabCodesClient, int i, int nbHeure, double tauxHoraire) {

        for (int j = i + 1; j < tabCodesClient.length; j++) {
            if (tabCodesClient[i].equals(tabCodesClient[j])) {
                nbHeure += interventions.get(j).getNombresHeures();
            }
        }
        nbHeure += interventions.get(i).getNombresHeures();
        double montantRegulier = nbHeure * tauxHoraire;

        return montantRegulier;
    }

    /**
     * @param tabPourVérifier
     * @param i
     * @param tabCodesClient
     * @return
     * @throws java.io.IOException
     */
    private static boolean isNouveauCodeClient(String[] tabCodesClient, String[] tabPourVérifier, int i) throws IOException {

        boolean nouveauCodeClient = false;
        boolean verification = verifierCodesClient(tabCodesClient[i], tabPourVérifier);

        if (verification == false) {
            nouveauCodeClient = true;
        }

        return nouveauCodeClient;
    }

    /**
     * @param codeClient
     * @param tabCodesClient
     * @return
     * @throws java.io.IOException
     */
    private static boolean verifierCodesClient(String codeClient, String[] tabCodesClient) throws IOException {

        boolean verifier = false;

        for (String numCodeClient : tabCodesClient) {
            if (codeClient.equals(numCodeClient)) {
                verifier = true;
                break;
            }
        }

        return verifier;
    }

    /**
     * @param tabObjet
     * @param compteur
     * @return
     */
    private static ObjetMontantRegulier[] creerTableauObjet(ObjetMontantRegulier tabObjet[], int compteur) {

        ObjetMontantRegulier tabObjetFinal[] = new ObjetMontantRegulier[compteur];
        int indice = 0;
        for (ObjetMontantRegulier objet : tabObjet) {
            if (objet != null) {

                tabObjetFinal[indice] = objet;
                indice++;
            }
        }

        return tabObjetFinal;
    }
}
