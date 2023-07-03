/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculetatcomptes;

import static calculetatcomptes.GestionErreurs.CODE_CLIENT;
import static calculetatcomptes.GestionErreurs.DATE_INTERVENTION;
import static calculetatcomptes.GestionErreurs.DISTANCE_DEPLACEMENT;
import static calculetatcomptes.GestionErreurs.INTERVENTION;
import static calculetatcomptes.GestionErreurs.MATRICULE_EMPLOYE;
import static calculetatcomptes.GestionErreurs.NOMBRES_HEURES;
import static calculetatcomptes.GestionErreurs.OVERTIME;
import static calculetatcomptes.GestionErreurs.TAUX_HORAIRES_MAX;
import static calculetatcomptes.GestionErreurs.TAUX_HORAIRES_MIN;
import static calculetatcomptes.GestionErreurs.TYPE_EMPLOYE;
import static calculetatcomptes.GestionErreurs.messageErreur;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class GestionEmploye {

    private static String source;
    
    public static Employe creerEmployeFromJson() throws Exception, ClassExceptions {

        Employe objetEmploye = new Employe();
        JSONObject employe = null;
        try{
        employe = JSONObject.fromObject(source);
        validerProprietesEmploye(employe);
        }catch(JSONException e){
           messageErreur ="Verifier la Syntaxe de votre Fichier JSON !!!!";  
           throw new ClassExceptions(messageErreur);
        }
        objetEmploye.setMatricule(employe.getInt(MATRICULE_EMPLOYE));
        objetEmploye.setTypeEmploye(employe.getInt(TYPE_EMPLOYE));
        objetEmploye.setTauxMin((double)convertirStringEnDouble(employe.getString(TAUX_HORAIRES_MIN)));  
        objetEmploye.setTauxMax((double)convertirStringEnDouble(employe.getString(TAUX_HORAIRES_MAX)));    
        JSONArray listInterventions = employe.getJSONArray(INTERVENTION);
        ArrayList<Intervention> interventions = remplireTableauInterventions(listInterventions);
        objetEmploye.setInterventions(interventions);

        return objetEmploye;
    }
    
    private static void validerProprietesEmploye( JSONObject employe ) throws Exception, ClassExceptions {
        
        GestionErreurs.validerMatriculeEmploye(employe);
        GestionErreurs.validerTypeEmploye(employe);
        GestionErreurs.validerTauxHorairesMin(employe);
        GestionErreurs.validerTauxHorairesMax(employe);
        GestionErreurs.validerIntervention(employe);
 
    }
    
    private static ArrayList<Intervention> remplireTableauInterventions(JSONArray listInterventions) throws Exception ,ClassExceptions  {
        JSONObject singleIntervention;
        ArrayList<Intervention> interventions = new ArrayList<>();

        for (int i = 0; i < listInterventions.size(); i++) {

            singleIntervention = listInterventions.getJSONObject(i);
            Intervention intervention = new Intervention();
            validerProprietesInterventions(singleIntervention );
            
            intervention.setCodeClient(singleIntervention.getString(CODE_CLIENT));
            intervention.setDistanceDeplacement(singleIntervention.getInt(DISTANCE_DEPLACEMENT));
            intervention.setOvertime(singleIntervention.getInt(OVERTIME));
            intervention.setNombresHeures(singleIntervention.getInt(NOMBRES_HEURES));
            intervention.setDateIntervention(singleIntervention.getString(DATE_INTERVENTION)); 
            interventions.add(intervention);
        }
        boolean isvalid=verifierConflitIntervention(interventions);
        GestionErreurs.validerConflitInterventions(isvalid);
        
        return interventions;
    }
    
    private static void validerProprietesInterventions( JSONObject singleIntervention ) throws Exception, ClassExceptions {
        
        GestionErreurs.validerCodeClient(singleIntervention);
        GestionErreurs.validerDistanceDeplacement(singleIntervention);
        GestionErreurs.validerOvertime(singleIntervention);
        GestionErreurs.validerNombreHeures(singleIntervention);
        GestionErreurs.validerDate(singleIntervention);
        
    }

    private static boolean verifierConflitIntervention(ArrayList<Intervention> interventions) {
        String tabCodesClient[] = new String[interventions.size()];
        String tabDateIntervention[] = new String[interventions.size()];
         boolean isValid = false;
        
        for(int k=0;k<interventions.size();k++){   
            tabCodesClient[k]=interventions.get(k).getCodeClient();
            tabDateIntervention[k]=interventions.get(k).getDateIntervention();
        }
        
        for (int i = 0; i < tabCodesClient.length; i++) {
            for (int j = i + 1; j < tabCodesClient.length; j++) {
                if (tabCodesClient[i].equals(tabCodesClient[j])) {
                    if (tabDateIntervention[i].equals(tabDateIntervention[j])) {
                        isValid = true;
                        break;

                    }
                }
            }
        }
        return isValid;
    }
   
    public static double convertirStringEnDouble(String chaine) {

        chaine = chaine.split(" ")[0].replaceAll(",",".");
        double decimal = Double.parseDouble(chaine);
        return decimal;
    }

    public static void lireFichierEntree(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IOException("Fichier d'entree manquant.");
        }
        try {
            String texteSource = new String(Files.readAllBytes(Paths.get(args[0])));
            source = texteSource;

        } catch (IOException e) {
            throw new IOException("Erreur dans la lecture du fichier d'entree.");
        }
    }

}