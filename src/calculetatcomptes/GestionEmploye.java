/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculetatcomptes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * Class qui lit le fichier d entree et revoie l objet employee creer et gere
 * les exception du fichier d entree
 *
 * @author rabahlemaici
 */
public class GestionEmploye {

    private static String source;

    public static final String matricule_employe = "matricule_employe";
    public static final String type_employe = "type_employe";
    public static final String taux_horaires_min = "taux_horaire_min";
    public static final String taux_horaires_max = "taux_horaire_max";
    public static final String intervention = "interventions";
    public static final String code_client = "code_client";
    public static final String distance_deplacement = "distance_deplacement";
    public static final String overtime = "overtime";
    public static final String nombres_heures = "nombre_heures";
    public static final String date_intervention = "date_intervention";

    /**
     * methode qui cree l objet employe a partir du fichier Json
     *
     * @return objetEmploye objet qui contient tous les information de l employe
     * @throws java.io.IOException
     * @throws calculetatcomptes.ClassExceptions
     */
    public static Employe creerEmployeFromJson() throws Exception, ClassExceptions {

        Employe objetEmploye = new Employe();
        
            JSONObject employe = JSONObject.fromObject(source);
            GestionErreurs.validerMatriculeEmploye(employe);
            int matriculeLocal = employe.getInt(matricule_employe);            
            objetEmploye.setMatricule(matriculeLocal);
            
            GestionErreurs.validerTypeEmploye(employe);
            int typeEmployeLocal = employe.getInt(type_employe);
            objetEmploye.setTypeEmploye(typeEmployeLocal);
            
            GestionErreurs.validerTauxHorairesMin(employe);
            String tauxMinLocal = employe.getString(taux_horaires_min);
            double tauxMinLocal1 = convertirStringEnDouble(tauxMinLocal);
            objetEmploye.setTauxMin(tauxMinLocal1);
            
            GestionErreurs.validerTauxHorairesMax(employe);
            String tauxMaxLocal = employe.getString(taux_horaires_max);
            double tauxMaxLocal1 = convertirStringEnDouble(tauxMaxLocal);
            objetEmploye.setTauxMax(tauxMaxLocal1);
            
            GestionErreurs.validerIntervention(employe);
            JSONArray listInterventions = employe.getJSONArray(intervention);
            ArrayList<Intervention> interventions;
            interventions = remplireTableauInterventions(listInterventions);
            objetEmploye.setInterventions(interventions);
        

        return objetEmploye;
    }

    /**
     * prend l objet json qui contients les interventions et remplir l objet
     * intervention dans un tableau
     *
     * @param listInterventions liste des objets Json
     * @return interventions la listes de tous les interventions
     * @throws calculetatcomptes.ClassExceptions
     */
    public static ArrayList<Intervention> remplireTableauInterventions(JSONArray listInterventions) throws ClassExceptions, IOException {
        JSONObject singleIntervention;

        ArrayList<Intervention> interventions = new ArrayList<>();

        for (int i = 0; i < listInterventions.size(); i++) {

            singleIntervention = listInterventions.getJSONObject(i);
            Intervention intervention = new Intervention();
            
            GestionErreurs.validerCodeClient(singleIntervention);
            intervention.setCodeClient(singleIntervention.getString(code_client));
           
            GestionErreurs.validerDistanceDeplacement(singleIntervention);
            intervention.setDistanceDeplacement(singleIntervention.getInt(distance_deplacement));
            
            GestionErreurs.validerOvertime(singleIntervention);
            intervention.setOvertime(singleIntervention.getInt(overtime));
            
            GestionErreurs.validerNombreHeures(singleIntervention);
            intervention.setNombresHeures(singleIntervention.getInt(nombres_heures));
            
            GestionErreurs.validerDate(singleIntervention);
            intervention.setDateIntervention(singleIntervention.getString(date_intervention));
            
            interventions.add(intervention);
        }
        boolean isvalid=verifierConflitIntervention(interventions);
        GestionErreurs.validerConflitInterventions(isvalid);
        
        return interventions;
    }

    public static boolean verifierConflitIntervention(ArrayList<Intervention> interventions) {
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

    /**
     * convert la chaine en double et supprime "$"
     *
     * @param chaine la chaine de caractere
     * @return decimal returne le decimal apres la conversion
     */
    public static double convertirStringEnDouble(String chaine) {

        chaine = chaine.split(" ")[0].replaceAll(",",".");
        double decimal = Double.parseDouble(chaine);
        return decimal;
    }

    /**
     * lire le fichier d entree et gere l exception
     *
     * @param args argumets des fichier d entree
     * @throws java.io.IOException
     */
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