/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculetatcomptes;

import java.io.IOException;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.text.DecimalFormat;

/**
 *Class principal du projet
 * lit le fichier d entree passee en argument
 * appel la methode pour faire les calculs 
 * envoie de ficier de sortie en argument
 * 
 * @author lemaicirabah
 */
public class CalculEtatComptes {

    /**
     * Methode main
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        //********Fichier Entree*********    
        GestionEmploye.lireFichierEntree(args);
        //******Calculs******************    
        creationJson();
        //******Fichier Sortie***********    
        ecrireFichierSortie(args[1]);
        
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
    
    //Methode pour donner le format 0.00$ a une valeur double
    static String formatDecimal(double valeur) {
        String pattern = "#.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(valeur);
    }
    
    /**
     * Methode fait la creation de 
     * l'objet Json et le retourner
     * 
     * @throws java.io.IOException
     * @return etatEmployee objet de type Json pour creer notre fichier de sortie
     */
    public static JSONObject creationJson()  throws IOException{
        
        EtatEmploye etatEmploye=GestionEtatCompte.RemplirObjetEtatCompte();
        ArrayList<Client> clients;
        clients=etatEmploye.getClients();
        
        JSONObject etatEmployee= new JSONObject();
        etatEmployee.accumulate("matricule_employe", etatEmploye.getMatriculeEmploye());
        etatEmployee.accumulate("etat_compte", formatDecimal(etatEmploye.getEtatCompte()) + " $");
        etatEmployee.accumulate("cout_fixe", formatDecimal(etatEmploye.getCoutFixe()) + " $");
        etatEmployee.accumulate("cout_ variable",formatDecimal(etatEmploye.getCoutVariable()) + " $");
          
        JSONArray etatClients = new JSONArray();
        JSONObject etatClient = new JSONObject();
         
        for(int i=0;i<clients.size();i++){
             
            etatClient.accumulate("code_client",clients.get(i).getCodeClient());
            etatClient.accumulate("etat_par_client",formatDecimal(clients.get(i).getEtatParClient())+ " $");
            etatClients.add(etatClient);
            etatClient.clear();      
             
        }  
        etatEmployee.accumulate("clients", etatClients);
         
        return etatEmployee;
    }
    /**
     * methode qui prends le chemin envoyer en argument 
     * pour ecrire le fichier Json en sortie 
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void ecrireFichierSortie(String args) throws IOException {
       try  {
          FileWriter.saveStringIntoFile(args, creationJson().toString());
        
       } catch (IOException e) {
           throw new IOException("Erreur dans l'ecriture du fichier de sortie.");
       }
    }
    
}
