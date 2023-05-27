/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculetatcomptes;
import java.io.IOException;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 *
 * @author rabahlemaici
 */
public class GestionEmploye {
    
    public static final String MATRICULE_EMPLOYE="matricule_employe";
    public static final String TYPE_EMPLOYE="type_employe";
    public static final String TAUX_HORAIRES_MIN= "taux_horaire_min";
    public static final String TAUX_HORAIRES_MAX="taux_horaire_max";
    public static final String INTERVENTION= "interventions";      
    public static final String CODE_CLIENT="code_client";
    public static final String DISTANCE_DEPLACEMENT="distance_deplacement";
    public static final String OVERTIME="overtime";
    public static final String NOMBRES_HEURES="nombre_heures";
    public static final String DATE_INTERVENTION="date_intervention";        
    
            
    public static Employe RecupererJson() throws IOException {
    
        Employe objetEmploye=new Employe();
        String myJSON = FileReader.loadFileIntoString("Json.txt",
       "UTF-8");
        JSONObject employe=JSONObject.fromObject(myJSON);
        int matriculeLocal= employe.getInt(MATRICULE_EMPLOYE);
        objetEmploye.setMatricule(matriculeLocal);
        int typeEmployeLocal= employe.getInt(TYPE_EMPLOYE);
        objetEmploye.setTypeEmploye(typeEmployeLocal);
        String tauxMinLocal= employe.getString(TAUX_HORAIRES_MIN);
        double tauxMinLocal1=convertirString(tauxMinLocal);
        objetEmploye.setTauxMin(tauxMinLocal1);
        String tauxMaxLocal= employe.getString(TAUX_HORAIRES_MAX);
        double tauxMaxLocal1=convertirString(tauxMaxLocal);
        objetEmploye.setTauxMax(tauxMaxLocal1);
        JSONArray listInterventions=employe.getJSONArray(INTERVENTION);
        ArrayList<Intervention> interventions;
        interventions=remplireInetrventions(listInterventions);
        objetEmploye.setInterventions(interventions);
        
        
        return objetEmploye;
    }
    public static ArrayList<Intervention> remplireInetrventions(JSONArray listInterventions){
        JSONObject singleIntervention;
        
        ArrayList<Intervention> interventions=new ArrayList<>(); 
        
        
        for (int i = 0; i < listInterventions.size(); i++) {

            singleIntervention = listInterventions.getJSONObject(i);
            Intervention intervention=new Intervention();
            intervention.setCodeClient(singleIntervention.getString(CODE_CLIENT));
            intervention.setDistanceDeplacement(singleIntervention.getInt(DISTANCE_DEPLACEMENT));
            intervention.setOvertime(singleIntervention.getInt(OVERTIME));
            intervention.setNombresHeures(singleIntervention.getInt(NOMBRES_HEURES));
            intervention.setDateIntervention(singleIntervention.getString(DATE_INTERVENTION));

            interventions.add(intervention);
        }    
        return interventions;
    }
    
    public static double convertirString(String chaine){
        
        chaine=chaine.replace(" $", "");
        double decimal=Double.parseDouble(chaine);
        return decimal;
    }  
}

