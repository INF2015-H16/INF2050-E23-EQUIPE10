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
     * @return
     * @throws calculetatcomptes.ClassExceptions
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
        tabObjetFinal = creertableauObjetFinalType(tabCodesClient, interventions, 
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
    private static ObjetMontantRegulier[] creertableauObjetFinalType(String tabCodesClient[],
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

        int compteur = 0;

        String tabPourVérifier[] = new String[interventions.size()];
        ObjetMontantRegulier tabObjet[] = new ObjetMontantRegulier[interventions.size()];

        for (int i = 0; i < tabCodesClient.length; i++) {
            int nbHeure = 0;
            boolean verification = verifierCodesClient(tabCodesClient[i], tabPourVérifier);
            if (verification == false) {
                compteur++;
                for (int j = i + 1; j < tabCodesClient.length; j++) {
                    nbHeure = calculNbHeures(interventions, tabCodesClient, i, j);
                }
                nbHeure += interventions.get(i).getNombresHeures();
                double montantRegulier = nbHeure * tauxHoraire;

                tabObjet[i] = new ObjetMontantRegulier(tabCodesClient[i], montantRegulier);
            }
            tabPourVérifier[i] = tabCodesClient[i];
        }
        ObjetMontantRegulier tabObjetFinal[] = creerTableauObjet(tabObjet, compteur);

        return tabObjetFinal;
    }

    /**
     * @param codeClient
     * @param tab
     * @return
     * @throws java.io.IOException
     */
    public static boolean verifierCodesClient(String codeClient, String tab[]) throws IOException {
        boolean verif = false;
        for (String tab1 : tab) {
            if (codeClient.equals(tab1)) {
                verif = true;
                break;
            }
        }
        return verif;
    }

    /**
     * @param interventions
     * @param tabCodesClient
     * @return
     */
    private static int calculNbHeures(ArrayList<Intervention> interventions, String tabCodesClient[], int i, int j) {

        int nbHeure = 0;

        if (tabCodesClient[i].equals(tabCodesClient[j])) {
            nbHeure += interventions.get(j).getNombresHeures();
        }

        return nbHeure;
    }

    /**
     * @param tabObjet
     * @param compteur
     * @return
     */
    private static ObjetMontantRegulier[] creerTableauObjet(ObjetMontantRegulier tabObjet[], int compteur) {

        ObjetMontantRegulier tabObjetFinal[] = new ObjetMontantRegulier[compteur];

        for (int i = 0; i < tabObjet.length; i++) {
            if (tabObjet[i] != null) {
                tabObjetFinal[i] = tabObjet[i];
            }
        }
        return tabObjetFinal;
    }
}
