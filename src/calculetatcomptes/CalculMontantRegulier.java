/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculetatcomptes;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author seif saidi
 */
public class CalculMontantRegulier {
    
    private String codeClient;
    private double montantReguliere;

    public CalculMontantRegulier(String codeClient, double montantReguliere) {
        this.codeClient = codeClient;
        this.montantReguliere = montantReguliere;
    }

    public String getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(String codeClient) {
        this.codeClient = codeClient;
    }

    public double getMontantReguliere() {
        return montantReguliere;
    }

    public void setMontantReguliere(double montantReguliere) {
        this.montantReguliere = montantReguliere;
    }
      
    private static boolean verifCodesClient(String codeClient, String tab[]) {
        boolean verif = false;
        for (String tab1 : tab) {
            if (codeClient.equals(tab1)) {
                verif = true;
                break;
            }
        }
        return verif;
    }

   
    public static ObjetMontantRegulier[] calculMontant() throws IOException {
        Employe employe;
        Intervention intervention;
        employe = GestionEmploye.RecupererJson();
        ArrayList<Intervention> interventions;
        interventions = employe.getInterventions();
        boolean verification;
        String tabCodesClient[] = new String[interventions.size()];
        String tabPourVérifier[] = new String[interventions.size()];
        double tauxHoraireMin = employe.getTauxMin();
        double tauxHoraireMax = employe.getTauxMax();
        double tauxHoraireMoyen = (tauxHoraireMax+tauxHoraireMin)/2;
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
                    verification = verifCodesClient(tabCodesClient[i], tabPourVérifier);
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
                    verification = verifCodesClient(tabCodesClient[i], tabPourVérifier);
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
                    verification = verifCodesClient(tabCodesClient[i], tabPourVérifier);
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





        
   

