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
     * Méthode pour la vérification si un code client existe dans un tableau
     * rempli avec des codes clients aprés le calcul de son montant reguliér
     * Elle prend comme parmétre un code client pour le véridier et le tableau
     * qui contient les codes clients
     *
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
     * La méthode calculMontant permet de faire le calcul du montant régulier
     * selon le type d'employé et retourne un tableau d'objet qui contient le
     * code du client et son montatnt régulier
     *
     * @return
     * @throws IOException
     * @throws calculetatcomptes.ClassExceptions
     */
    public static ObjetMontantRegulier[] calculMontant() throws Exception, ClassExceptions {
        Employe employe;
        Intervention intervention;
        employe = GestionEmploye.creerEmployeFromJson();
        ArrayList<Intervention> interventions;
        interventions = employe.getInterventions();
        boolean verification;
        String tabCodesClient[] = new String[interventions.size()];
        String tabPourVérifier[] = new String[interventions.size()];
        double tauxHoraireMin = employe.getTauxMin();
        double tauxHoraireMax = employe.getTauxMax();
        double tauxHoraireMoyen = (tauxHoraireMax + tauxHoraireMin) / 2;
        int nbHeure;
        int typeEmploye = employe.getTypeEmploye();
        double montantRegulier;
        ObjetMontantRegulier tabObjet[] = new ObjetMontantRegulier[interventions.size()];
        ObjetMontantRegulier tabObjetFinal[] = new ObjetMontantRegulier[0];
        int compteur = 0;

        for (int i = 0; i < interventions.size(); i++) {
            intervention = interventions.get(i);
            tabCodesClient[i] = intervention.getCodeClient();
            // System.out.println(tabCodesClient[i]);
        }

        switch (typeEmploye) {

            case 0:
                for (int i = 0; i < tabCodesClient.length; i++) {
                    nbHeure = 0;
                    verification = verifierCodesClient(tabCodesClient[i], tabPourVérifier);
                    if (verification == false) {
                        compteur++;
                        for (int j = i + 1; j < tabCodesClient.length; j++) {
                            if (tabCodesClient[i].equals(tabCodesClient[j])) {
                                nbHeure += interventions.get(j).getNombresHeures();
                            }
                        }

                        nbHeure += interventions.get(i).getNombresHeures();
                        montantRegulier = nbHeure * tauxHoraireMin;

                        tabObjet[i] = new ObjetMontantRegulier(tabCodesClient[i], montantRegulier);
                    }
                    tabPourVérifier[i] = tabCodesClient[i];

                }
                tabObjetFinal = new ObjetMontantRegulier[compteur];

                for (int i = 0; i < tabObjet.length; i++) {
                    if (tabObjet[i] != null) {
                        tabObjetFinal[i] = tabObjet[i];
                    }
                }
                tabObjet = null;

                break;

            case 1:
                for (int i = 0; i < tabCodesClient.length; i++) {
                    nbHeure = 0;
                    verification = verifierCodesClient(tabCodesClient[i], tabPourVérifier);
                    if (verification == false) {
                        compteur++;
                        for (int j = i + 1; j < tabCodesClient.length; j++) {
                            if (tabCodesClient[i].equals(tabCodesClient[j])) {
                                nbHeure += interventions.get(j).getNombresHeures();
                            }
                        }

                        nbHeure += interventions.get(i).getNombresHeures();
                        montantRegulier = nbHeure * tauxHoraireMoyen;

                        tabObjet[i] = new ObjetMontantRegulier(tabCodesClient[i], montantRegulier);
                    }
                    tabPourVérifier[i] = tabCodesClient[i];

                }
                tabObjetFinal = new ObjetMontantRegulier[compteur];

                for (int i = 0; i < tabObjet.length; i++) {
                    if (tabObjet[i] != null) {
                        tabObjetFinal[i] = tabObjet[i];
                    }
                }
                tabObjet = null;
                break;

            case 2:
                for (int i = 0; i < tabCodesClient.length; i++) {
                    nbHeure = 0;
                    verification = verifierCodesClient(tabCodesClient[i], tabPourVérifier);
                    if (verification == false) {
                        compteur++;
                        for (int j = i + 1; j < tabCodesClient.length; j++) {
                            if (tabCodesClient[i].equals(tabCodesClient[j])) {
                                nbHeure += interventions.get(j).getNombresHeures();
                            }
                        }

                        nbHeure += interventions.get(i).getNombresHeures();
                        montantRegulier = nbHeure * tauxHoraireMax;

                        tabObjet[i] = new ObjetMontantRegulier(tabCodesClient[i], montantRegulier);
                    }
                    tabPourVérifier[i] = tabCodesClient[i];

                }
                tabObjetFinal = new ObjetMontantRegulier[compteur];

                for (int i = 0; i < tabObjet.length; i++) {
                    if (tabObjet[i] != null) {
                        tabObjetFinal[i] = tabObjet[i];
                    }
                }
                tabObjet = null;
                break;

        }

        return tabObjetFinal;
    }
}
