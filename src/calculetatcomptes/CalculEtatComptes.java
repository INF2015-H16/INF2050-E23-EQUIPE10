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
        if (args.length > 0) {
            if (args[0].equals("-S")) {
                // Affichez les statistiques à la console
                displayStatistics();
            } else if (args[0].equals("-SR")) {
                // Réinitialisez les statistiques en les remettant à zéro
                
            }
        }  
        GestionErreurs.validerEcartDate( creerEmployeFromJson().getInterventions());
        GestionErreurs.depasserCoutFix(remplirObjetEtatCompte().getCoutFixe());
        GestionErreurs.depasserEtatCompte(remplirObjetEtatCompte().getEtatCompte());
        
        ecrireFichierSortie(args[2],creationJson());
        }catch(Exception e){
        //******Fichier Sortie***********    
        ecrireFichierSortie(args[2],creerJsonErreurMessage(GestionErreurs.messageErreur));
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
   
   private static void displayStatistics() throws Exception {
        Statistiques statistiques=loadStatistics();
        System.out.println("Statistiques :");
        System.out.println("--------------------------");
        System.out.println("Nombre total d'interventions : " + statistiques.getNombreTotalInterventions());
        System.out.println("Occurrences avec un état par client :");
        System.out.println("- Moins de 1000$ : " + statistiques.getNombreOccurrencesMoins1000());
        System.out.println("- Entre 1000 et 10000$ : " + statistiques.getNombreOccurrencesEntre1000Et10000());
        System.out.println("- Plus de 10000$ : " + statistiques.getNombreOccurrencesPlus10000());
        System.out.println("Nombre d'interventions par type d'employé :"+ statistiques.getInterventionsParTypeEmploye());
        System.out.println("Nombre d'heures maximal soumis pour une intervention : " + statistiques.getNombreHeuresMaximal());
        System.out.println("État par client maximal retourné pour un client : " + statistiques.getEtatMaximalPourClient());
    }
   private static Statistiques loadStatistics() throws ClassExceptions, Exception {
        
        EtatEmploye etatEmploye = GestionEtatCompte.remplirObjetEtatCompte();
        Statistiques statistique=new Statistiques();
        
        statistique.setNombreTotalInterventions(etatEmploye.getClients().size());
        statistique.setNombreOccurrencesMoins1000(calculStatOccurrences().getNombreOccurrencesMoins1000());
        statistique.setNombreOccurrencesEntre1000Et10000(calculStatOccurrences().getNombreOccurrencesEntre1000Et10000());
        statistique.setNombreOccurrencesPlus10000(calculStatOccurrences().getNombreOccurrencesPlus10000());
 
       return statistique;
        
    }
   private static ArrayList calculStatsOccurrencesArray(ArrayList<Client> clients) {
       
       ArrayList<Integer> stats =new ArrayList<>();
       
       int moinsMilles=0;
       int plusDixMille=0;
       int entreMilleEtDiXMille=0;
       
       for(int i=0;i<clients.size();i++){
            
            if(clients.get(i).getEtatParClient()<1000){
                
                moinsMilles =moinsMilles + 1;   
            }
            if(clients.get(i).getEtatParClient()>=1000 
                    && clients.get(i).getEtatParClient()<= 10000){
                entreMilleEtDiXMille+=1;
            }if(clients.get(i).getEtatParClient()>10000 ){
                
                plusDixMille+=1;
            }
        }
       stats.add(moinsMilles);
       stats.add(entreMilleEtDiXMille);
       stats.add(plusDixMille);
       return stats; 
   }
   
   private static Statistiques calculStatOccurrences() throws ClassExceptions, Exception {
        
       EtatEmploye etatEmploye = GestionEtatCompte.remplirObjetEtatCompte();
        Statistiques statistique=new Statistiques();
        ArrayList<Client> clients;
        clients=etatEmploye.getClients();
        
        Integer moinsMilles=(Integer)calculStatsOccurrencesArray(clients).get(0);
        Integer plusDixMille=(Integer)calculStatsOccurrencesArray(clients).get(2);
        Integer entreMilleEtDiXMille=(Integer)calculStatsOccurrencesArray(clients).get(1);
        
        statistique.setNombreOccurrencesMoins1000(moinsMilles );
        statistique.setNombreOccurrencesEntre1000Et10000(entreMilleEtDiXMille);
        statistique.setNombreOccurrencesPlus10000(plusDixMille);
        
       return statistique;
        
    }
    
}
