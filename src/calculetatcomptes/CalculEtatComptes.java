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
import java.text.NumberFormat;
import java.util.Locale;


public class CalculEtatComptes {

    
    public static void main(String[] args) throws Exception,ClassExceptions {
        
        //********Fichier Entree*********    
        try{
            
        GestionEmploye.lireFichierEntree(args);
        //******Calculs******************    
        creationJson();
        ecrireFichierSortie(args[1],creationJson());
        }catch(Exception e){
        //******Fichier Sortie***********    
        ecrireFichierSortie(args[1],creerJsonErreurMessage(GestionErreurs.messageErreur));
        throw e;
        }
        
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
    public static JSONObject creationJson()  throws Exception, ClassExceptions{
        
        EtatEmploye etatEmploye=GestionEtatCompte.remplirObjetEtatCompte();
        ArrayList<Client> clients;
        clients=etatEmploye.getClients();
        
        JSONObject etatEmployee= new JSONObject();
        etatEmployee.accumulate("matricule_employe", etatEmploye.getMatriculeEmploye());
        etatEmployee.accumulate("etat_compte", formatDecimal(etatEmploye.getEtatCompte()).replace(",", ".")+" $");
        etatEmployee.accumulate("cout_fixe", formatDecimal(etatEmploye.getCoutFixe()).replace(",", ".")+" $");
        etatEmployee.accumulate("cout_ variable",formatDecimal(etatEmploye.getCoutVariable()).replace(",", ".")+" $");
          
        JSONArray etatClients = new JSONArray();
        JSONObject etatClient = new JSONObject();
         
        for(int i=0;i<clients.size();i++){
             
            etatClient.accumulate("code_client",clients.get(i).getCodeClient());
            etatClient.accumulate("etat_par_client",formatDecimal(clients.get(i).getEtatParClient()).replace(",", ".")+" $");
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
     * @param json
     * @throws java.io.IOException
     */
    public static void ecrireFichierSortie(String args,JSONObject json) throws IOException, ClassExceptions {
       try  {
          FileWriter.saveStringIntoFile(args, json.toString());
        
       } catch (IOException e) {
           throw new IOException("Erreur dans l'ecriture du fichier de sortie.");
       }
    }
   public static JSONObject creerJsonErreurMessage(String message){
    
     JSONObject messageErr= new JSONObject();
     
    messageErr.accumulate("message", message);
     return messageErr;
    
    }
    
}
