/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculetatcomptes;

import java.io.IOException;
import java.util.ArrayList;

public class GestionEtatCompte {

    private static final double MONTANT_AJOUTE = 733.77;

    public static EtatEmploye remplirObjetEtatCompte() throws IOException, ClassExceptions, Exception {
   
        Employe employe = GestionEmploye.creerEmployeFromJson();

        ObjetMontantRegulier[] montantReg = CalculMontantRegulier.calculMontant();
        CalculeMontantSupplementaires[] montantSupp = CalculeMontantSupplementaires.calculeMontantSupplementaire();
        CalculerMontantDeplacement[] montantDep = CalculerMontantDeplacement.calculerMontanDeplacement();
               
        double etatCompte = calculEtatcompte(montantReg, montantDep, montantSupp);

        ArrayList<Client> clients;
        clients = remplirListeClients(montantReg, montantDep, montantSupp);

        EtatEmploye objetEtatCompte = creerObjetEtatCompte(employe, etatCompte, clients);

        return objetEtatCompte;
    }

    private static double calculEtatcompte(ObjetMontantRegulier[] montantReg, CalculerMontantDeplacement[] montantDep, CalculeMontantSupplementaires[] montantSupp) {//

        double etatCompte;
        double etatClient = 0;

        for (int i = 0; i < montantReg.length; i++) {
            etatClient += montantReg[i].getMontantRegulier()
                    + montantDep[i].getMontantDeplacement()
                    + montantSupp[i].getMontantHeuresSupp();
        }
        etatCompte = etatClient + MONTANT_AJOUTE;

        return (arrondiAuPlusProche(etatCompte));
    }

    private static ArrayList<Client> remplirListeClients(ObjetMontantRegulier[] montantReg, CalculerMontantDeplacement[] montantDep, CalculeMontantSupplementaires[] montantSupp) {

        Client client;
        ArrayList<Client> clients = new ArrayList<>();

        for (int i = 0; i < montantReg.length; i++) {
            client = calculEtatClient(montantReg[i], montantDep[i], montantSupp[i]);
            clients.add(client);
        }
        return clients;
    }

    private static Client calculEtatClient(ObjetMontantRegulier montantReg, CalculerMontantDeplacement montantDep, CalculeMontantSupplementaires montantSupp) {

        double montantRegulier = montantReg.getMontantRegulier();
        double montantDeplacement = montantDep.getMontantDeplacement();
        double montantHeuresSupp = montantSupp.getMontantHeuresSupp();
        double resultat = montantRegulier + montantDeplacement + montantHeuresSupp;
        
        Client etatClient = new Client();
        etatClient.setEtatParClient(resultat);
        etatClient.setCodeClient(montantReg.getCodeClient());

        return etatClient;
    }

    private static EtatEmploye creerObjetEtatCompte(Employe employe, double etatCompte, ArrayList<Client> clients) {

        EtatEmploye objetEtatCompte = new EtatEmploye();

        objetEtatCompte.setMatriculeEmploye(employe.getMatricule());
        objetEtatCompte.setEtatCompte(etatCompte);
        objetEtatCompte.setCoutFixe(calculMontantFix(etatCompte));
        objetEtatCompte.setCoutVariable(calculCoutVariable(etatCompte));
        objetEtatCompte.setClients(clients);

        return objetEtatCompte;
    }

    private static double calculMontantFix(double etatCompte) {
        final double POURCENTAGE_FIX = 0.012; //1.2%
        double montantFix;

        montantFix = etatCompte * POURCENTAGE_FIX;

        return arrondiAuPlusProche(montantFix);
    }
    private static double calculCoutVariable(double etatCompte) {
        final double POURCENTAGE_VARIABLE = 0.025; //2.5%
        double coutVariable;

        coutVariable = etatCompte * POURCENTAGE_VARIABLE;

        return arrondiAuPlusProche(coutVariable);
    }

    private static double arrondiAuPlusProche(double montant) {

        return (double) Math.round(montant * 20) / 20;
    }
}
