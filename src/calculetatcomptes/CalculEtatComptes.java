/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculetatcomptes;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author LP
 */
public class CalculEtatComptes {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        Employe employe;
        Intervention intervention;
        employe=GestionEmploye.RecupererJson();
        ArrayList<Intervention> interventions;
        interventions=employe.getInterventions();
        intervention=interventions.get(1);
        EtatEmploye etatEmploye=GestionEtatCompte.RemplirObjetEtatCompte();
        ArrayList<Client> clients;
        clients=etatEmploye.getClients();
        Client client;
        client=clients.get(1);
        
        ObjetMontantRegulier[] montantReg= CalculMontantRegulier.calculMontant();
        CalculeMontantSupplementaires [] montantSupp=CalculeMontantSupplementaires.calculeMontantSupplementaire();
        CalculMontantDeplacement [] montantDep=CalculMontantDeplacement.calculMontanDeplacement();
        
        System.out.println("monatant regulier de "+montantDep[0].getCodeClient()+" est : "+montantDep[0].getMontantDeplacement()+"monat supp de "+montantSupp[2].getCodeClient()+" est : "+montantSupp[0].getMontantHeuresSupp());
        double s=(double)Math.round(4.4567*100.0)/100.0;
        System.out.println(s);
        System.out.print("etat compte de "+ etatEmploye.getMatriculeEmploye()+ " est :  "+etatEmploye.getEtatCompte() +" et l'etat compte du client "+client.getCodeClient()+" est : "+client.getEtatParClient());

        //System.out.print("test projet");
        // TODO code application logic here
    }
    
}
