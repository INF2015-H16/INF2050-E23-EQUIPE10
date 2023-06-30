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
     * @throws calculetatcomptes.ClassExceptions
     */
    public static void main(String[] args) throws IOException, ClassExceptions {
        
        //********Fichier Entree*********    
        GestionEmploye.lireFichierEntree(args);
        //******Calculs******************    
        creerJson();
        //******Fichier Sortie***********    
        ecrireFichierSortie(args[1]);
        
    }
    
    static String formatDecimal(double valeur) {
        String pattern = "#.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(valeur);
    }
    
   
    public static JSONObject creerJson()  throws IOException, ClassExceptions{
        
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
     * @throws calculetatcomptes.ClassExceptions
     */
    public static void ecrireFichierSortie(String args) throws IOException, ClassExceptions {
       try  {
          FileWriter.saveStringIntoFile(args, creerJson().toString());
        
       } catch (IOException e) {
           throw new IOException("Erreur dans l'ecriture du fichier de sortie.");
       }
    }
    
}
