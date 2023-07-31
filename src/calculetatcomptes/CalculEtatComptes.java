/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculetatcomptes;

import java.io.FileReader;
import java.io.FileWriter;
import static calculetatcomptes.GestionEmploye.creerEmployeFromJson;
import static calculetatcomptes.GestionEtatCompte.remplirObjetEtatCompte;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CalculEtatComptes {
    
    private static final File file = new File("statistiques.json");

    public static void main(String[] args) throws Exception, ClassExceptions {

        //********Fichier Entree*********    
        if(!args[0].equals("-S")&& !args[0].equals("-SR")){
        try{
              
                GestionEmploye.lireFichierEntree(args);
                //******Calculs****************** 
                creationJson();
                GestionErreurs.validerEcartDate( creerEmployeFromJson().getInterventions());
                GestionErreurs.depasserCoutFix(remplirObjetEtatCompte().getCoutFixe());
                GestionErreurs.depasserEtatCompte(remplirObjetEtatCompte().getEtatCompte());
                GestionErreurs.depasserTauxHoraire(creerEmployeFromJson().getTauxMin(), creerEmployeFromJson().getTauxMax());
                GestionErreurs.depasserDistanceDeplacement(creerEmployeFromJson());
                GestionErreurs.depasserEtatCompteCoutVariable(remplirObjetEtatCompte());
                ecrireFichierSortie(args[1],creationJson());
                storeStatisticsFromFile(loadStatistics());
            }catch(Exception e){
        //******Fichier Sortie***********    
        ecrireFichierSortie(args[1],creerJsonErreurMessage(GestionErreurs.messageErreur));
        throw e;
        }
    }
        if (args.length > 0) {
            
        if (args[0].equals("-S")) {
            // Affichez les statistiques à la console
            displayStatistics(loadStatisticsFromFile());
            
            } else if (args[0].equals("-SR")) {
                // Réinitialisez les statistiques en les remettant à zéro
                resetStatistics(loadStatisticsFromFile());
                System.out.println("Statistiques réinitialisées avec succès.");
            }
            
        }  
        
        

    }

    //Methode pour donner le format 0.00$ a une valeur double
    static String formatDecimal(double valeur) {
        String pattern = "#.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        return decimalFormat.format(valeur);
    }

    private static JSONObject creationJson() throws Exception, ClassExceptions {

        EtatEmploye etatEmploye = GestionEtatCompte.remplirObjetEtatCompte();
        ArrayList<Client> clients;
        clients = etatEmploye.getClients();

        JSONObject etatEmployee = new JSONObject();
        etatEmployee.put("matricule_employe", etatEmploye.getMatriculeEmploye());
        etatEmployee.put("etat_compte", formatDecimal(etatEmploye.getEtatCompte()).replace(",", ".") + " $");
        etatEmployee.put("cout_fixe", formatDecimal(etatEmploye.getCoutFixe()).replace(",", ".") + " $");
        etatEmployee.put("cout_ variable", formatDecimal(etatEmploye.getCoutVariable()).replace(",", ".") + " $");

        JSONArray etatClients = new JSONArray();
        JSONObject etatClient = new JSONObject();

        for (int i = 0; i < clients.size(); i++) {

            etatClient.put("code_client", clients.get(i).getCodeClient());
            etatClient.put("etat_par_client", formatDecimal(clients.get(i).getEtatParClient()).replace(",", ".") + " $");
            etatClients.add(etatClient);
            etatClient.clear();

        }
        etatEmployee.put("clients", etatClients);

        etatEmployee.put("observations", GestionErreurs.observations); 

        //System.out.println(GestionErreurs.observations.size());
        etatEmployee.put("observations", GestionErreurs.observations);

        return etatEmployee;
    }

    private static void ecrireFichierSortie(String args, JSONObject json) throws IOException, ClassExceptions {
        
        File file = new File(args);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(json.toString());
           
        }catch (IOException e) {
            throw new IOException("Erreur dans l'ecriture du fichier de sortie.");
        }
    }

    private static JSONObject creerJsonErreurMessage(String message) {

        JSONObject messageErr = new JSONObject();

        messageErr.put("message", message);
        return messageErr;

    }

   
   private static void displayStatistics(Statistiques statistiques) throws Exception {
        
        System.out.println("Statistiques : ");
        System.out.println("--------------------------");
        System.out.println("Nombre total d'interventions : " + statistiques.getNombreTotalInterventions());
        System.out.println("Occurrences avec un état par client : ");
        System.out.println("- Moins de 1000$ : " + statistiques.getNombreOccurrencesMoins1000());
        System.out.println("- Entre 1000 et 10000$ : " + statistiques.getNombreOccurrencesEntre1000Et10000());
        System.out.println("- Plus de 10000$ : " + statistiques.getNombreOccurrencesPlus10000());
        System.out.println("Nombre d'interventions par type d'employé 1 : "+ statistiques.getNombreInterventionsParTypeEmploye1());
        System.out.println("Nombre d'interventions par type d'employé 2 : "+ statistiques.getNombreInterventionsParTypeEmploye2());
        System.out.println("Nombre d'interventions par type d'employé 3 : "+ statistiques.getNombreInterventionsParTypeEmploye3());        
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
        statistique.setNombreHeuresMaximal(calculHeureMaxInter(creerEmployeFromJson()));
        statistique.setEtatMaximalPourClient((Double)calculMaxEtatCompte(etatEmploye.getClients()));    
        statistique.setNombreInterventionsParTypeEmploye1(calculeInterventionsTypeEmp(1,creerEmployeFromJson()));
        statistique.setNombreInterventionsParTypeEmploye2(calculeInterventionsTypeEmp(2,creerEmployeFromJson()));
        statistique.setNombreInterventionsParTypeEmploye3(calculeInterventionsTypeEmp(3,creerEmployeFromJson()));
        return statistique;      
    }
   private static int calculHeureMaxInter(Employe employe) {
       int max=employe.getInterventions().get(0).getNombresHeures();
       for(int i=0;i<employe.getInterventions().size();i++){          
           if(max<employe.getInterventions().get(i).getNombresHeures()){
               max=employe.getInterventions().get(i).getNombresHeures();
           }
       }
       return max;
   }
   
   private static int calculeInterventionsTypeEmp(int a,Employe employe) {
        int compteur=0;
        
        if(employe.getTypeEmploye()==a){
            
           compteur= employe.getInterventions().size();
            
        }
        return compteur;
    }
   private static double calculMaxEtatCompte(ArrayList<Client> clients) {      
       double max=clients.get(0).getEtatParClient();       
       for(int i=0;i<clients.size();i++){           
           if(max<clients.get(i).getEtatParClient()){
               max=clients.get(i).getEtatParClient();
           }
       }
       return max;
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
   private static Statistiques storeStatisticsFromFile(Statistiques statistique) throws Exception {
    JSONParser parser = new JSONParser();
    boolean exist = false;
    Statistiques existingStatistics = new Statistiques();

    if (file.exists()) {
        try (FileReader fileReader = new FileReader(file)) {
            // Parse le fichier JSON et convertit en objet JSONObject
            JSONObject jsonObject = (JSONObject) parser.parse(fileReader);

            // Construit l'objet Statistiques à partir du JSONObject
            existingStatistics.setNombreTotalInterventions(((Long) jsonObject.get("nombreTotalInterventions")).intValue());
            existingStatistics.setNombreOccurrencesMoins1000(((Long) jsonObject.get("nombreOccurrencesMoins1000")).intValue());
            existingStatistics.setNombreOccurrencesEntre1000Et10000(((Long) jsonObject.get("nombreOccurrencesEntre1000Et10000")).intValue());
            existingStatistics.setNombreOccurrencesPlus10000(((Long) jsonObject.get("nombreOccurrencesPlus10000")).intValue());
            existingStatistics.setNombreHeuresMaximal(((Long) jsonObject.get("nombreHeuresMaximal")).intValue());
            existingStatistics.setEtatMaximalPourClient((Double) jsonObject.get("etatMaximalPourClient"));
            existingStatistics.setNombreInterventionsParTypeEmploye1(((Long) jsonObject.get("nombreInterventionsParTypeEmploye1")).intValue());
            existingStatistics.setNombreInterventionsParTypeEmploye2(((Long) jsonObject.get("nombreInterventionsParTypeEmploye2")).intValue());
            existingStatistics.setNombreInterventionsParTypeEmploye3(((Long) jsonObject.get("nombreInterventionsParTypeEmploye3")).intValue());

            exist = true;
        } catch (IOException | ParseException e) {
            System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

    // Mettre à jour les statistiques existantes avec les nouvelles statistiques
    existingStatistics.setNombreTotalInterventions(existingStatistics.getNombreTotalInterventions() + statistique.getNombreTotalInterventions());
    existingStatistics.setNombreOccurrencesMoins1000(existingStatistics.getNombreOccurrencesMoins1000() + statistique.getNombreOccurrencesMoins1000());
    existingStatistics.setNombreOccurrencesEntre1000Et10000(existingStatistics.getNombreOccurrencesEntre1000Et10000() + statistique.getNombreOccurrencesEntre1000Et10000());
    existingStatistics.setNombreOccurrencesPlus10000(existingStatistics.getNombreOccurrencesPlus10000() + statistique.getNombreOccurrencesPlus10000());
    existingStatistics.setNombreHeuresMaximal(Math.max(existingStatistics.getNombreHeuresMaximal(), statistique.getNombreHeuresMaximal()));
    existingStatistics.setEtatMaximalPourClient(Math.max(existingStatistics.getEtatMaximalPourClient(), statistique.getEtatMaximalPourClient()));
    existingStatistics.setNombreInterventionsParTypeEmploye1(existingStatistics.getNombreInterventionsParTypeEmploye1() + statistique.getNombreInterventionsParTypeEmploye1());
    existingStatistics.setNombreInterventionsParTypeEmploye2(existingStatistics.getNombreInterventionsParTypeEmploye2() + statistique.getNombreInterventionsParTypeEmploye2());
    existingStatistics.setNombreInterventionsParTypeEmploye3(existingStatistics.getNombreInterventionsParTypeEmploye3() + statistique.getNombreInterventionsParTypeEmploye3());

    // Sauvegarder les statistiques mises à jour dans le fichier JSON
    try (FileWriter fileWriter = new FileWriter(file)) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nombreTotalInterventions", existingStatistics.getNombreTotalInterventions());
        jsonObject.put("nombreOccurrencesMoins1000", existingStatistics.getNombreOccurrencesMoins1000());
        jsonObject.put("nombreOccurrencesEntre1000Et10000", existingStatistics.getNombreOccurrencesEntre1000Et10000());
        jsonObject.put("nombreOccurrencesPlus10000", existingStatistics.getNombreOccurrencesPlus10000());
        jsonObject.put("nombreHeuresMaximal", existingStatistics.getNombreHeuresMaximal());
        jsonObject.put("etatMaximalPourClient", existingStatistics.getEtatMaximalPourClient());
        jsonObject.put("nombreInterventionsParTypeEmploye1", existingStatistics.getNombreInterventionsParTypeEmploye1());
        jsonObject.put("nombreInterventionsParTypeEmploye2", existingStatistics.getNombreInterventionsParTypeEmploye2());
        jsonObject.put("nombreInterventionsParTypeEmploye3", existingStatistics.getNombreInterventionsParTypeEmploye3());

        fileWriter.write(jsonObject.toJSONString());
    } catch (IOException e) {
        e.printStackTrace();
    }

    if (!exist) {
        existingStatistics = statistique;
    }

    return existingStatistics;
}

   private static void resetStatistics(Statistiques statistiques) throws IOException {
    statistiques.setNombreTotalInterventions(0);
    statistiques.setNombreOccurrencesMoins1000(0);
    statistiques.setNombreOccurrencesEntre1000Et10000(0);
    statistiques.setNombreOccurrencesPlus10000(0);
    statistiques.setNombreHeuresMaximal(0);
    statistiques.setEtatMaximalPourClient(0);
    statistiques.setNombreTotalInterventions(0);
    statistiques.setEtatMaximalPourClient(0);
    statistiques.setNombreInterventionsParTypeEmploye1(0);
    statistiques.setNombreInterventionsParTypeEmploye2(0);
    statistiques.setNombreInterventionsParTypeEmploye3(0);

    loadForReset();
   }
   
     private static void loadForReset() throws FileNotFoundException, IOException {
        
            if (file.exists()) {
            try (FileWriter fileWriter = new FileWriter(file)) {
                // Écrire les statistiques réinitialisées dans le fichier JSON
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("nombreTotalInterventions", 0);
                jsonObject.put("nombreOccurrencesMoins1000", 0);
                jsonObject.put("nombreOccurrencesEntre1000Et10000", 0);
                jsonObject.put("nombreOccurrencesPlus10000", 0);
                jsonObject.put("nombreHeuresMaximal", 0);
                jsonObject.put("etatMaximalPourClient", 0.0);
                jsonObject.put("nombreInterventionsParTypeEmploye1", 0);
                jsonObject.put("nombreInterventionsParTypeEmploye2", 0);
                jsonObject.put("nombreInterventionsParTypeEmploye3", 0);

                fileWriter.write(jsonObject.toJSONString());
            } catch (IOException e) {
                System.out.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
            }
     }
    }
        
       private static Statistiques loadStatisticsFromFile() throws FileNotFoundException, IOException {
        JSONParser parser = new JSONParser();
        
        try (FileReader fileReader = new FileReader(file)) {
            // Parse le fichier JSON et convertit en objet JSONObject
            JSONObject jsonObject = (JSONObject) parser.parse(fileReader);

            // Construit l'objet Statistiques à partir du JSONObject
            Statistiques statistiques = new Statistiques();
            statistiques.setNombreTotalInterventions(((Long) jsonObject.get("nombreTotalInterventions")).intValue());
            statistiques.setNombreOccurrencesMoins1000(((Long) jsonObject.get("nombreOccurrencesMoins1000")).intValue());
            statistiques.setNombreOccurrencesEntre1000Et10000(((Long) jsonObject.get("nombreOccurrencesEntre1000Et10000")).intValue());
            statistiques.setNombreOccurrencesPlus10000(((Long) jsonObject.get("nombreOccurrencesPlus10000")).intValue());
            statistiques.setNombreHeuresMaximal(((Long) jsonObject.get("nombreHeuresMaximal")).intValue());
            statistiques.setEtatMaximalPourClient((Double) jsonObject.get("etatMaximalPourClient"));
            statistiques.setNombreInterventionsParTypeEmploye1(((Long) jsonObject.get("nombreInterventionsParTypeEmploye1")).intValue());
            statistiques.setNombreInterventionsParTypeEmploye2(((Long) jsonObject.get("nombreInterventionsParTypeEmploye2")).intValue());
            statistiques.setNombreInterventionsParTypeEmploye3(((Long) jsonObject.get("nombreInterventionsParTypeEmploye3")).intValue());

            return statistiques;
        } catch (ParseException| FileNotFoundException e) {
            System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }

        // Si le fichier n'existe pas ou s'il y a une erreur de lecture, retournez une nouvelle instance de Statistiques
        return new Statistiques();
    }
}