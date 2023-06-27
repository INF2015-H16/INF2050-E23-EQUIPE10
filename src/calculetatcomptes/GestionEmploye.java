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

    public static final String MATRICULE_EMPLOYE = "matricule_employe";
    public static final String TYPE_EMPLOYE = "type_employe";
    public static final String TAUX_HORAIRES_MIN = "taux_horaire_min";
    public static final String TAUX_HORAIRES_MAX = "taux_horaire_max";
    public static final String INTERVENTION = "interventions";
    public static final String CODE_CLIENT = "code_client";
    public static final String DISTANCE_DEPLACEMENT = "distance_deplacement";
    public static final String OVERTIME = "overtime";
    public static final String NOMBRES_HEURES = "nombre_heures";
    public static final String DATE_INTERVENTION = "date_intervention";

    /**
     * methode qui cree l objet employe a partir du fichier Json
     *
     * @return objetEmploye objet qui contient tous les information de l employe
     * @throws java.io.IOException
     * @throws calculetatcomptes.ClassExceptions
     */
    public static Employe RecupererJson() throws IOException, ClassExceptions {

        Employe objetEmploye = new Employe();
        try {
            JSONObject employe = JSONObject.fromObject(source);
            int matriculeLocal = employe.getInt(MATRICULE_EMPLOYE);
            GestionErreurs.verifierEntierNegative(matriculeLocal);
            objetEmploye.setMatricule(matriculeLocal);
            int typeEmployeLocal = employe.getInt(TYPE_EMPLOYE);
            GestionErreurs.validerTypeEmploye(typeEmployeLocal);
            objetEmploye.setTypeEmploye(typeEmployeLocal);
            String tauxMinLocal = employe.getString(TAUX_HORAIRES_MIN);
            double tauxMinLocal1 = convertirString(tauxMinLocal);
            GestionErreurs.validerTauxHoraires(tauxMinLocal1);
            objetEmploye.setTauxMin(tauxMinLocal1);
            String tauxMaxLocal = employe.getString(TAUX_HORAIRES_MAX);
            double tauxMaxLocal1 = convertirString(tauxMaxLocal);
            GestionErreurs.validerTauxHoraires(tauxMaxLocal1);
            objetEmploye.setTauxMax(tauxMaxLocal1);
            JSONArray listInterventions = employe.getJSONArray(INTERVENTION);
            ArrayList<Intervention> interventions;
            interventions = remplireInetrventions(listInterventions);
            GestionErreurs.validerIntervention(interventions);
            objetEmploye.setInterventions(interventions);
        } catch (JSONException e) {
            System.out.println("Verifie la syntaxe de votre fichier Json SVP !");
        }

        return objetEmploye;
    }

    /**
     * prend l objet json qui contients les interventions et remplir l objet
     * intervention dans un tableau
     *
     * @param listInterventions liste des objets Json
     * @return interventions la listes de tous les interventions
     */
    public static ArrayList<Intervention> remplireInetrventions(JSONArray listInterventions) {
        JSONObject singleIntervention;

        ArrayList<Intervention> interventions = new ArrayList<>();

        for (int i = 0; i < listInterventions.size(); i++) {

            singleIntervention = listInterventions.getJSONObject(i);
            Intervention intervention = new Intervention();
            intervention.setCodeClient(singleIntervention.getString(CODE_CLIENT));
            intervention.setDistanceDeplacement(singleIntervention.getInt(DISTANCE_DEPLACEMENT));
            intervention.setOvertime(singleIntervention.getInt(OVERTIME));
            intervention.setNombresHeures(singleIntervention.getInt(NOMBRES_HEURES));
            intervention.setDateIntervention(singleIntervention.getString(DATE_INTERVENTION));

            interventions.add(intervention);
        }
        return interventions;
    }

    /**
     * convert la chaine en double et supprime "$"
     *
     * @param chaine la chaine de caractere
     * @return decimal returne le decimal apres la conversion
     */
    public static double convertirString(String chaine) {

        chaine = chaine.replace(" $", "");
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
