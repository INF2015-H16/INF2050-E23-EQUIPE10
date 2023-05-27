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
    private static double convertireStringEnDouble(String chaine) throws IOException {
        double chaineConvertit;
        chaine = chaine.replace("$", "");
        chaineConvertit = Double.parseDouble(chaine);
        return chaineConvertit;
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
    public ObjetMontantRegulier[] claculMontant() throws IOException {
        Employe employe;
        Intervention intervention;
        employe = GestionEmploye.RecupererJson();
        ArrayList<Intervention> interventions;
        interventions = employe.getInterventions();
        boolean verification;
        String tabCodesClient[] = new String[interventions.size()];
        String tabPourVérifier[] = new String[interventions.size()];
        double tauxHoraireMin = convertireStringEnDouble(employe.getTauxMin());
        double tauxHoraireMax = convertireStringEnDouble(employe.getTauxMax());
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
    return tabObjetFinal;
    }
}
