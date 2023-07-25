package calculetatcomptes;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class GestionErreurs {

    static public String messageErreur;
    static public JSONArray observations = new JSONArray();

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
                System.err.print(messageErreur);
                System.exit(0);
            }
        } catch (NumberFormatException | JSONException e) {
            messageErreur = "La propriété <matricule_employe> n'existe pas ou le format du texte est incorrecte!!";
            System.err.print(messageErreur);
            System.exit(0);
        }
    }

    public static void validerTypeEmploye(JSONObject jsonObject) throws ClassExceptions {
        try {
            int typeEmploye = jsonObject.getInt(TYPE_EMPLOYE);
            if (isValideTypeEmploye(typeEmploye)) {
                messageErreur = "Type employée invalide!!";
                System.err.print(messageErreur);
                System.exit(0);
            }
        } catch (NumberFormatException | JSONException e) {
            messageErreur = "La propriété <type_employe> n'existe pas ou le format du texte est incorrecte!!";
            System.err.print(messageErreur);
            System.exit(0);
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
                System.err.print(messageErreur);
                System.exit(0);
            }
        } catch (NumberFormatException | JSONException e) {
            messageErreur = "La propriété <taux_horaires_min> n'existe pas ou le format du texte est incorrecte!!";
            System.err.print(messageErreur);
            System.exit(0);
        }
    }

    public static void validerTauxHorairesMax(JSONObject jsonObject) throws ClassExceptions {
        try {
            double tauxHoraire = GestionEmploye.convertirStringEnDouble(jsonObject.getString(TAUX_HORAIRES_MAX).trim());
            if (tauxHoraire < 0) {
                messageErreur = "Taux horaires max invalide!!";
                System.err.print(messageErreur);
                System.exit(0);
            }
        } catch (NumberFormatException | JSONException e) {
            messageErreur = "La propriété <taux_horaires_max> n'existe pas ou le format du texte est incorrecte!!";
            System.err.print(messageErreur);
            System.exit(0);
        }
    }

    public static void validerIntervention(JSONObject jsonObject) throws ClassExceptions {
        try {
            JSONArray interventions = jsonObject.getJSONArray(INTERVENTION);
            if (interventions.isEmpty()) {
                messageErreur = "Aucune intervention!!";
                System.err.print(messageErreur);
                System.exit(0);
            } else if (interventions.size() > 10) {
                messageErreur = "Les nombre d'interventions dépasse 10!!";
                System.err.print(messageErreur);
                System.exit(0);
            }
        } catch (JSONException e) {
            messageErreur = "La propriété <intervention> n'existe pas ou le format du texte est incorrecte!!";
            System.err.print(messageErreur);
            System.exit(0);
        }
    }

    public static void validerDistanceDeplacement(JSONObject jsonObject) throws ClassExceptions {
        try {
            int distanceDep = jsonObject.getInt(DISTANCE_DEPLACEMENT);

            if (distanceDep < 0 || distanceDep > 100) {
                messageErreur = "La distance de deplacement doit etre entre 0 et 100 kilomètres!!";
                System.err.print(messageErreur);
                System.exit(0);
            }
        } catch (NumberFormatException | JSONException e) {
            messageErreur = "La propriété <distance_deplacement> n'existe pas ou le format du texte est incorrecte!!";
            System.err.print(messageErreur);
            System.exit(0);
        }
    }

    public static void validerDate(JSONObject jsonObject) throws ClassExceptions {
        try {
            String maDate = jsonObject.getString(DATE_INTERVENTION);

            String pattern = "^\\d{4}-\\d{2}-\\d{2}$";
            if (!maDate.matches(pattern)) {
                messageErreur = "Format de date invalide!!";
                System.err.print(messageErreur);
                System.exit(0);
            }
        } catch (JSONException e) {
            messageErreur = "La propriété <date_intervention> n'existe pas ou le format du texte est incorrecte!!";
            System.err.print(messageErreur);
            System.exit(0);
        }
    }

    public static void validerCodeClient(JSONObject jsonObject) throws ClassExceptions {
        try {
            String codeClient = jsonObject.getString(CODE_CLIENT).trim();

            if (codeClient.equals("")) {
                System.err.print(messageErreur);
                System.exit(0);
            }
        } catch (JSONException e) {
            messageErreur = "La propriété <code_client> n'existe pas ou le format du texte est incorrecte!!";
            System.err.print(messageErreur);
            System.exit(0);
        }
    }

    public static void validerOvertime(JSONObject jsonObject) throws ClassExceptions {
        try {
            int overT = jsonObject.getInt(OVERTIME);

            if (overT < 0 || overT > 4) {
                messageErreur = "Overtime doit etre entre 0 et 4!!";
                System.err.print(messageErreur);
                System.exit(0);
            }
        } catch (NumberFormatException | JSONException e) {
            messageErreur = "La propriété <overtime> n'existe pas ou le format du texte est incorrecte!!";
            System.err.print(messageErreur);
            System.exit(0);
        }
    }

    public static void validerNombreHeures(JSONObject jsonObject) throws ClassExceptions {
        try {
            int nbrHeure = jsonObject.getInt(NOMBRES_HEURES);

            if (nbrHeure < 0 || nbrHeure > 8) {
                messageErreur = "nombres heures negative ou supérieur a 8 heures!!";
                System.err.print(messageErreur);
                System.exit(0);
            }
        } catch (NumberFormatException | JSONException e) {
            messageErreur = "La propriété <nombres_heures> n'existe pas ou le format du texte est incorrecte!!";
            System.err.print(messageErreur);
            System.exit(0);
        }
    }

    public static void validerConflitInterventions(boolean isvalide) throws ClassExceptions {

        if (isvalide) {
            messageErreur = "La combinaison des valeurs des champs <code_client> et <date_intervention> "
                    + "intervention n\'est pas unique !! ";
            System.err.print(messageErreur);
            System.exit(0);
        }

    }

    private static boolean isValideDate(LocalDate date1, LocalDate date2) {

        long monthsApart = ChronoUnit.MONTHS.between(date1, date2);
        return Math.abs(monthsApart) >= 6;
    }

    public static LocalDate convertStringToLocalDate(String dateString) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return LocalDate.parse(dateString, formatter);
    }

    public static void validerEcartDate(ArrayList<Intervention> interventions) throws ClassExceptions {

        Intervention singleIntervention1;
        Intervention singleIntervention2;
        for (int i = 0; i < interventions.size(); i++) {
            singleIntervention1 = interventions.get(i);
            LocalDate date1 = convertStringToLocalDate(singleIntervention1.getDateIntervention());

            for (int j = i; j < interventions.size(); j++) {
                singleIntervention2 = interventions.get(j);
                LocalDate date2 = convertStringToLocalDate(singleIntervention2.getDateIntervention());
                if (isValideDate(date1, date2)) {
                    String message = "L’écart maximal entre les dates d’intervention doit être de moins de 6 mois.";
                    observations.add(message);
                }
            }
        }
    }

    public static void depasserCoutFix(double coutFix) {

        if (coutFix > 1500) {
            String message = "Le cout fix payable nécessite des ajustements.";
            observations.add(message);
        }

    }

<<<<<<< HEAD

=======
>>>>>>> 724a8eb307d9a789b10e81b9215a5cee619c7565
    public static void depasserEtatCompte(double etatCompte) {

        if (etatCompte > 3000) {
            String message = "L'etat de compte nécessite des ajustements.";
            observations.add(message);
        }
    }

    public static void depasserTauxHoraire(double tauxHoraireMin, double tauxHoraireMax) {

        if (tauxHoraireMax > (2 * tauxHoraireMin)) {
            String message = "Le taux horaire maximum ne peut pas dépasser deux fois le taux horaire minimum.";
            observations.add(message);
        }
    }

}
