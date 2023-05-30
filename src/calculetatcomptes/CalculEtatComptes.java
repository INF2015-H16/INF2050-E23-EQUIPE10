/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculetatcomptes;

import java.io.IOException;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
        
        
             creationJson();
        
        /*client=clients.get(1);
        
        ObjetMontantRegulier[] montantReg= CalculMontantRegulier.calculMontant();
        CalculeMontantSupplementaires [] montantSupp=CalculeMontantSupplementaires.calculeMontantSupplementaire();
        CalculMontantDeplacement [] montantDep=CalculMontantDeplacement.calculMontanDeplacement();
        
        System.out.println("monatant déplacement de "+montantDep[0].getCodeClient()+" est : "+montantDep[0].getMontantDeplacement()+"monat supp de "+montantSupp[1].getCodeClient()+" est : "+montantSupp[1].getMontantHeuresSupp());
        double s=(double)Math.round(4.4567*100.0)/100.0;
        System.out.print("etat compte de "+ etatEmploye.getMatriculeEmploye()+ " est :  "+etatEmploye.getEtatCompte() +" et l'etat compte du client "+client.getCodeClient()+" est : "+client.getEtatParClient());

        //System.out.print("test projet");
        // TODO code application logic here
     */
    }
    
    
    public static void creationJson()  throws IOException{
        
        EtatEmploye etatEmploye=GestionEtatCompte.RemplirObjetEtatCompte();
        ArrayList<Client> clients;
        clients=etatEmploye.getClients();
        
        
        JSONObject etatEmployee= new JSONObject();
         etatEmployee.accumulate("matricule_employe", etatEmploye.getMatriculeEmploye());
         etatEmployee.accumulate("etat_compte", etatEmploye.getEtatCompte());
         etatEmployee.accumulate("cout_fixe", etatEmploye.getCoutFixe());
         etatEmployee.accumulate("cout_ variable",etatEmploye.getCoutVariable() );
         
         
         JSONArray etatClients = new JSONArray();
         JSONObject etatClient = new JSONObject();
         
         for(int i=0;i<clients.size();i++){
             
            etatClient.accumulate("code_client",clients.get(i).getCodeClient());
            etatClient.accumulate("etat_par_client",clients.get(i).getEtatParClient());
            etatClients.add(etatClient);
            etatClient.clear();      
             
         }
         
         
         etatEmployee.accumulate("clients", etatClients);
         
         FileWriter.saveStringIntoFile("etatEmploye."
         + "json", etatEmployee.toString());

    }
    
}
