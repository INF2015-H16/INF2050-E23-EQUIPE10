/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculetatcomptes;
import java.io.IOException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 *
 * @author LP
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
    
            
    public  Employe RecupererJson() throws IOException {
    
        Employe objetEmploye=null;
        String myJSON = FileReader.loadFileIntoString("Json.txt",
       "UTF-8");
        JSONObject employe=JSONObject.fromObject(myJSON);
        int matriculeLocal= employe.getInt(MATRICULE_EMPLOYE);
        objetEmploye.setMatricule(matriculeLocal);
        int typeEmployeLocal= employe.getInt(TYPE_EMPLOYE);
        objetEmploye.setTauxMin(typeEmployeLocal);
        int tauxMinLocal= employe.getInt(TAUX_HORAIRES_MIN);
        objetEmploye.setTauxMin(tauxMinLocal);
        int tauxMaxLocal= employe.getInt(TAUX_HORAIRES_MAX);
        objetEmploye.setTauxMax(tauxMaxLocal);
        
        
        return objetEmploye;
    }
    
}
