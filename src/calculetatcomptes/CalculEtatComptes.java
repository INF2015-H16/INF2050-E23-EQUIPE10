/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculetatcomptes;

import static calculetatcomptes.GestionEmploye.creerEmployeFromJson;
import static calculetatcomptes.GestionEtatCompte.remplirObjetEtatCompte;
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
        GestionErreurs.validerEcartDate( creerEmployeFromJson().getInterventions());
        GestionErreurs.depasserCoutFix(remplirObjetEtatCompte().getCoutFixe());
        
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
        //System.out.println(GestionErreurs.observations.size());
        etatEmployee.put("observations", GestionErreurs.observations); 
        return etatEmployee;
    }
    
    
   
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
