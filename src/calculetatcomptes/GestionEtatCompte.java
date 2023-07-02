/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculetatcomptes;

import java.io.IOException;
import java.util.ArrayList;

/**
 * class de méthodes qui permet de gérer tous les montant calculées dans les
 * autres class et de les regrouper dans un seul objet etatEmploye
 *
 * @author rabahlemici
 */
public class GestionEtatCompte {

    private static final double MONTANT_AJOUTE = 733.77;

    /**
     * @return @throws IOException
     * @throws calculetatcomptes.ClassExceptions
     */
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

    /**
     * @param montantReg
     * @param montantDep
     * @param montantSupp
     * @return
     */
    private static double calculEtatcompte(ObjetMontantRegulier[] montantReg, CalculerMontantDeplacement[] montantDep, CalculeMontantSupplementaires[] montantSupp) {//

        double etatCompte;
        double etatClient = 0;

        for (int i = 0; i < montantReg.length; i++) {
            etatClient += montantReg[i].getMontantRegulier()
                    + montantDep[i].getMontantDeplacement()
                    + montantSupp[i].getMontantHeuresSupp();
        }
        etatCompte = etatClient + MONTANT_AJOUTE;

        return (arrondiSuperieur(etatCompte));
    }

    /**
     * @param montantReg
     * @param montantDep
     * @param montantSupp
     * @return
     */
    private static ArrayList<Client> remplirListeClients(ObjetMontantRegulier[] montantReg, CalculerMontantDeplacement[] montantDep, CalculeMontantSupplementaires[] montantSupp) {

        Client client;
        ArrayList<Client> clients = new ArrayList<>();

        for (int i = 0; i < montantReg.length; i++) {
            client = calculEtatClient(montantReg[i], montantDep[i], montantSupp[i]);
            clients.add(client);
        }
        return clients;
    }

    /**
     * @param montantReg
     * @param montantDep
     * @param montantSupp
     * @return
     */
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

    /**
     * @param employe
     * @param etatCompte
     * @param clients
     * @return
     */
    private static EtatEmploye creerObjetEtatCompte(Employe employe, double etatCompte, ArrayList<Client> clients) {

        EtatEmploye objetEtatCompte = new EtatEmploye();

        objetEtatCompte.setMatriculeEmploye(employe.getMatricule());
        objetEtatCompte.setEtatCompte(etatCompte);
        objetEtatCompte.setCoutFixe(calculMontantFix(etatCompte));
        objetEtatCompte.setCoutVariable(calculCoutVariable(etatCompte));
        objetEtatCompte.setClients(clients);

        return objetEtatCompte;
    }

    /**
     * @param etatCompte
     * @return
     */
    private static double calculMontantFix(double etatCompte) {
        final double POURCENTAGE_FIX = 0.012; //1.2%
        double montantFix;

        montantFix = etatCompte * POURCENTAGE_FIX;

        return arrondiSuperieur(montantFix);
    }

    /**
     * @param etatCompte
     * @return
     */
    private static double calculCoutVariable(double etatCompte) {
        final double POURCENTAGE_VARIABLE = 0.025; //2.5%
        double coutVariable;

        coutVariable = etatCompte * POURCENTAGE_VARIABLE;

        return arrondiSuperieur(coutVariable);
    }

    /**
     * @param montant
     * @return
     */
    private static double arrondiSuperieur(double montant) {

        return (double) Math.ceil(montant * 20) / 20d;
    }
}
