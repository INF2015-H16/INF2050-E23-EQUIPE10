/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculetatcomptes;

import java.io.IOException;
import java.util.ArrayList;


public class CalculeMontantSupplementaires {

    private String codeClient;
    private double MontantHeuresSupp = 0;

    public CalculeMontantSupplementaires(String codeCl, double montantHeureSupp) {

        this.MontantHeuresSupp = montantHeureSupp;
        this.codeClient = codeCl;

    }

    public void setCodeClient(String codeClient) {

        this.codeClient = codeClient;
    }

    public void setMontantHeuresSupp(double MontantHeuresSupp) {

        this.MontantHeuresSupp = MontantHeuresSupp;
    }

    public String getCodeClient() {

        return codeClient;
    }

    public double getMontantHeuresSupp() {

        return MontantHeuresSupp;

    }

    
    public static CalculeMontantSupplementaires[] calculeMontantSupplementaire() throws Exception, ClassExceptions {

        Employe employe = GestionEmploye.creerEmployeFromJson();
        int typeEmploye = employe.getTypeEmploye();

        ArrayList<Intervention> interventions = employe.getInterventions();
        String tabCodesClient[] = new String[interventions.size()];
        int tabOvertime[] = new int[interventions.size()];

        for (int i = 0; i < interventions.size(); i++) {
            tabCodesClient[i] = interventions.get(i).getCodeClient();
            tabOvertime[i] = interventions.get(i).getOvertime() + 2;// 2heures suplementaires de base
        }

        CalculeMontantSupplementaires tabFinalMontantSuppl[] = creerTableauMontantSuppl(tabCodesClient, tabOvertime, interventions, typeEmploye);

        return tabFinalMontantSuppl;
    }

    
    private static CalculeMontantSupplementaires[] creerTableauMontantSuppl(String tabCodesClient[], int tabOvertime[],
            ArrayList<Intervention> interventions, int typeEmploye) throws IOException {

        String tabPourVérifier[] = new String[interventions.size()];
        CalculeMontantSupplementaires[] tabObjet = new CalculeMontantSupplementaires[tabCodesClient.length];

        int compteur = 0;

        for (int i = 0; i < tabCodesClient.length; i++) {
            if (isNouveauCodeClient(tabCodesClient, tabPourVérifier, i)) {
                compteur++;
                tabObjet[i] = creerObjetMontantSuplementaire(tabCodesClient, tabOvertime, interventions, typeEmploye, i);
            }
            tabPourVérifier[i] = tabCodesClient[i];
        }

        CalculeMontantSupplementaires tabObjetFinal[] = creerTableauObjet(tabObjet, compteur);

        return tabObjetFinal;
    }

    
    private static CalculeMontantSupplementaires creerObjetMontantSuplementaire(String tabCodesClient[], int tabOvertime[],
            ArrayList<Intervention> interventions, int typeEmploye, int i) throws IOException {

        CalculeMontantSupplementaires[] tabObjet = new CalculeMontantSupplementaires[tabCodesClient.length];
        int nbHeure = 0;
        int overtime = 0;

        int nombreHeure = nombreHeures(interventions, tabCodesClient, i, nbHeure);
        int nbOvertime = nombreHeuresOvertime(tabCodesClient, tabOvertime, i, overtime);
        double montantHeureSuplementaire = montantHeuresSuppType(typeEmploye, nombreHeure, nbOvertime);

        tabObjet[i] = new CalculeMontantSupplementaires(tabCodesClient[i], montantHeureSuplementaire);

        return tabObjet[i];
    }

    
    private static CalculeMontantSupplementaires[] creerTableauObjet(CalculeMontantSupplementaires[] tabMontantSuppl, int tailleTableau) {

        CalculeMontantSupplementaires[] tableauFinal = new CalculeMontantSupplementaires[tailleTableau];

        int indice = 0;
        for (CalculeMontantSupplementaires objet : tabMontantSuppl) {
            if (objet != null) {
                tableauFinal[indice] = objet;
                indice++;
            }
        }
        return tableauFinal;
    }

    
    private static int nombreHeures(ArrayList<Intervention> interventions,
            String[] tabCodesClient, int i, int nbHeure) {

        for (int j = i + 1; j < tabCodesClient.length; j++) {
            if (tabCodesClient[i].equals(tabCodesClient[j])) {
                nbHeure += interventions.get(j).getNombresHeures();
            }
        }
        nbHeure += interventions.get(i).getNombresHeures();

        return nbHeure;
    }

    
    private static int nombreHeuresOvertime(String[] tabCodesClient, int tabOvertime[], int i, int overtime) {

        for (int j = i + 1; j < tabCodesClient.length; j++) {
            if (tabCodesClient[i].equals(tabCodesClient[j])) {
                overtime += tabOvertime[j];
            }
        }
        overtime += tabOvertime[i];

        return overtime;
    }

    
    private static double montantHeuresSuppType(int typeEmploye, int nombreDheureCl, int overtime) {

        double MontantHeuresSupp = 0;

        switch (typeEmploye) {
            case 0:
                MontantHeuresSupp = 0;//0.00$
                break;
            case 1:
                MontantHeuresSupp = montanHeuresType1(nombreDheureCl, overtime);
                break;
            case 2:
                MontantHeuresSupp = montanHeuresType2(overtime);
                break;
        }
        return MontantHeuresSupp;
    }
 
    private static double montanHeuresType1(int nombreDheureCl, int overtime) {
        double MontantHeuresSupp = 0;
        if (nombreDheureCl <= 4) {
            MontantHeuresSupp = 0;//0.00$
        } else if (nombreDheureCl > 4 && nombreDheureCl <= 8) {
            MontantHeuresSupp = overtime * 50;//50$par heure d'overtime
        } else if (nombreDheureCl > 8) {
            MontantHeuresSupp = overtime * 100;//100$par heure d'overtime
        }
        return MontantHeuresSupp;
    }

    private static double montanHeuresType2(int overtime) {
        double MontantHeuresSupp = 0;
        if (overtime <= 4) {
            MontantHeuresSupp = overtime * 75;//75$par heure d'overtime
        } else if (overtime > 4) {
            MontantHeuresSupp = overtime * 150;//150$par heure d'overtime
            if (MontantHeuresSupp >= 1500) {
                MontantHeuresSupp = 1500;//1500$
            }
        }
        return MontantHeuresSupp;
    }

    
    private static boolean isNouveauCodeClient(String[] tabCodesClient, String[] tabPourVérifier, int i) throws IOException {

        boolean nouveauCodeClient = false;
        boolean verification = verifierLeCodeClient(tabCodesClient[i], tabPourVérifier);

        if (verification == false) {
            nouveauCodeClient = true;
        }

        return nouveauCodeClient;
    }

    
    public static boolean verifierLeCodeClient(String codeClient, String[] tabCodesClient) {

        boolean verifier = false;

        for (String numCodeClient : tabCodesClient) {
            if (codeClient.equals(numCodeClient)) {
                verifier = true;
                break;
            }
        }

        return verifier;
    }
}
