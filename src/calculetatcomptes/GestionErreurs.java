/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculetatcomptes;

import java.io.IOException;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 *
 * @author seif saidi
 */
public class GestionErreurs {

    static public String messageErreur;

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

    public static void validerMatriculeEmploye(JSONObject jsonObject) throws ClassExceptions, IOException {
        try {
            int matriculeEmploye = jsonObject.getInt(MATRICULE_EMPLOYE);

            if (matriculeEmploye < 0) {
                messageErreur = "Matricule employée est invalide!!";
                throw new ClassExceptions(messageErreur);
            }
        } catch (JSONException e) {
            messageErreur = "La propriété <matricule_employe> n'existe pas ou le format du texte est incorrecte!!";
            throw new ClassExceptions(messageErreur);
        }
    }

    public static void validerTypeEmploye(JSONObject jsonObject) throws ClassExceptions {
        try {
            int typeEmploye = jsonObject.getInt(TYPE_EMPLOYE);
            if (isValideTypeEmploye(typeEmploye)) {
                messageErreur = "Type employée invalide!!";
                throw new ClassExceptions(messageErreur);
            }
        } catch (JSONException e) {
            messageErreur = "La propriété <type_employe> n'existe pas ou le format du texte est incorrecte!!";
            throw new ClassExceptions(messageErreur);
        }
    }

    private static boolean isValideTypeEmploye(int typeEmploye) {

        return (typeEmploye != 0 && typeEmploye != 1 && typeEmploye != 2);
    }

    public static void validerTauxHorairesMin(JSONObject jsonObject) throws ClassExceptions {
        try {
            double tauxHoraire = GestionEmploye.convertirStringEnDouble(jsonObject.getString(TAUX_HORAIRES_MIN).trim());
            if (tauxHoraire < 0) {
                messageErreur = "Taux horaire min invalide!!";
                throw new ClassExceptions(messageErreur);
            }
        } catch (NumberFormatException | JSONException e) {
            messageErreur = "La propriété <taux_horaires_min> n'existe pas ou le format du texte est incorrecte!!";
            throw new ClassExceptions(messageErreur);
        }
    }

    public static void validerTauxHorairesMax(JSONObject jsonObject) throws ClassExceptions {
        try {
            double tauxHoraire = GestionEmploye.convertirStringEnDouble(jsonObject.getString(TAUX_HORAIRES_MAX).trim());
            if (tauxHoraire < 0) {
                messageErreur = "Taux horaires max invalide!!";
                throw new ClassExceptions(messageErreur);
            }
        } catch (NumberFormatException | JSONException e) {
            messageErreur = "La propriété <taux_horaires_max> n'existe pas ou le format du texte est incorrecte!!";
            throw new ClassExceptions(messageErreur);
        }
    }

    public static void validerIntervention(JSONObject jsonObject) throws ClassExceptions {
        try {
            JSONArray interventions = jsonObject.getJSONArray(INTERVENTION);
            if (interventions.isEmpty()) {
                messageErreur = "Aucune intervention!!";
                throw new ClassExceptions(messageErreur);
            } else if (interventions.size() > 10) {
                messageErreur = "Les nombre d'interventions dépasse 10!!";
                throw new ClassExceptions(messageErreur);
            }
        } catch (JSONException e) {
            messageErreur = "La propriété <intervention> n'existe pas ou le format du texte est incorrecte!!";
            throw new ClassExceptions(messageErreur);
        }
    }

    public static void validerDistanceDeplacement(JSONObject jsonObject) throws ClassExceptions {
        try {
            int distanceDep = jsonObject.getInt(DISTANCE_DEPLACEMENT);

            if (distanceDep < 0 || distanceDep > 100) {
                messageErreur = "La distance de deplacement doit etre entre 0 et 100 kilomètres!!";
                throw new ClassExceptions(messageErreur);
            }
        } catch (JSONException e) {
            messageErreur = "La propriété <distance_deplacement> n'existe pas ou le format du texte est incorrecte!!";
            throw new ClassExceptions(messageErreur);
        }
    }

    public static void validerDate(JSONObject jsonObject) throws ClassExceptions {
        try {
            String maDate = jsonObject.getString(DATE_INTERVENTION);

            String pattern = "^\\d{4}-\\d{2}-\\d{2}$";
            if (!maDate.matches(pattern)) {
                messageErreur = "Format de date invalide!!";
                throw new ClassExceptions(messageErreur);
            }
        } catch (JSONException e) {
            messageErreur = "La propriété <date_intervention> n'existe pas ou le format du texte est incorrecte!!";
            throw new ClassExceptions(messageErreur);
        }
    }

    public static void validerCodeClient(JSONObject jsonObject) throws ClassExceptions {
        try {
            String codeClient = jsonObject.getString(CODE_CLIENT).trim();
            String pattern = "^[C]\\d{3}$";
            
            if (codeClient.equals("")) {
                throw new ClassExceptions("La propriete <code_client> ne doit pas etre vide.");
            } else if (!codeClient.matches(pattern)) {
                messageErreur = "Format de code de client invalide!!";
                throw new ClassExceptions(messageErreur);
            }
        } catch (JSONException e) {
            messageErreur = "La propriété <code_client> n'existe pas ou le format du texte est incorrecte!!";
            throw new ClassExceptions(messageErreur);
        }
    }

    public static void validerOvertime(JSONObject jsonObject) throws ClassExceptions {
        try {
            int overT = jsonObject.getInt(OVERTIME);

            if (overT < 0 || overT > 4) {
                messageErreur = "Overtime doit etre entre 0 et 4!!";
                throw new ClassExceptions(messageErreur);
            }
        } catch (NumberFormatException | JSONException e) {
            messageErreur = "La propriété <overtime> n'existe pas ou le format du texte est incorrecte!!";
            throw new ClassExceptions(messageErreur);
        }
    }

    public static void validerNombreHeures(JSONObject jsonObject) throws ClassExceptions {
        try {
            int nbrHeure = jsonObject.getInt(NOMBRES_HEURES);

            if (nbrHeure < 0 || nbrHeure > 8) {
                messageErreur = "nombres heures negative ou supérieur a 8 heures!!";
                throw new ClassExceptions(messageErreur);
            }
        } catch (NumberFormatException | JSONException e) {
            messageErreur = "La propriété <nombres_heures> n'existe pas ou le format du texte est incorrecte!!";
            throw new ClassExceptions(messageErreur);
        }
    }

    public static void validerConflitInterventions(boolean isvalide) throws ClassExceptions {

        if (isvalide) {
            messageErreur = "La combinaison des valeurs des champs <code_client> et <date_intervention> "
                    + "intervention n\'est pas unique !! ";
            throw new ClassExceptions(messageErreur);
        }

    }
}
