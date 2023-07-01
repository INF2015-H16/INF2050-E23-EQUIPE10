/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculetatcomptes;

import java.io.IOException;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 *
 * @author seif saidi
 */
public class GestionErreurs {
    
    static public String messageErreur;
    
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

    public static void validerMatriculeEmploye(JSONObject jsonObject) throws ClassExceptions {
        try{
            int matriculeEmploye =jsonObject.getInt(matricule_employe);
            if (matriculeEmploye < 0) {
                messageErreur="Code employée est invalide !!";
                throw new ClassExceptions("Code employée invalide !!");
            }
        }catch(JSONException e ){
            messageErreur="propriete <matricule_employe> n'existe pas ou le format du texte est incorrecte!!";
            throw new ClassExceptions("propriete <matricule_employe> n'existe pas ou le format du texte est incorrecte!!");
        }

    }
    public static void validerTypeEmploye(JSONObject jsonObject) throws ClassExceptions {
        try{
            int typeEmploye =jsonObject.getInt(type_employe);
            if (isValideTypeEmploye(typeEmploye)) {
                messageErreur="Type employée invalide !!";
                throw new ClassExceptions("Type employée invalide !!");
            }
        }catch(JSONException e ){
            messageErreur="propriete <type_employe> n'existe pas ou le format du texte est incorrecte!!";
            throw new ClassExceptions("propriete <type_employe> n'existe pas ou le format du texte est incorrecte!!");
        }

    }
    public static void validerTauxHorairesMin(JSONObject jsonObject) throws ClassExceptions {
        try{
            double tauxHoraire =GestionEmploye.convertirStringEnDouble(jsonObject.getString(taux_horaires_min));  
            if (tauxHoraire<0) {
                messageErreur="Taux horaires min invalide !!";
                throw new ClassExceptions("Taux horaires min invalide !!");
            }
        }catch(JSONException e ){
            messageErreur="propriete <taux_horaires_min> n'existe pas ou le format du texte est incorrecte!!";
            throw new ClassExceptions("propriete <taux_horaires_min> n'existe pas ou le format du texte est incorrecte!!");
        }
    }
    
    public static void validerTauxHorairesMax(JSONObject jsonObject) throws ClassExceptions {
        try{
            double tauxHoraire =GestionEmploye.convertirStringEnDouble(jsonObject.getString(taux_horaires_max));  
            if (tauxHoraire<0) {
                messageErreur="Taux horaires max invalide !!";
                throw new ClassExceptions("Taux horaires max invalide !!");
            }
        }catch(JSONException e ){
            messageErreur="propriete <taux_horaires_max> n'existe pas ou le format du texte est incorrecte!!";
            throw new ClassExceptions("propriete <taux_horaires_max> n'existe pas ou le format du texte est incorrecte!!");
        }
    }
    
    public static void validerIntervention(JSONObject jsonObject) throws ClassExceptions {
        try{
            JSONArray interventions =jsonObject.getJSONArray(intervention);  
            if (interventions.isEmpty()) {
                messageErreur="Auccune intervention !!";
            throw new ClassExceptions("Auccune intervention !!");

            }else if (interventions.size() > 10) {
                messageErreur="Les interventions dépasse 10 !!";
                throw new ClassExceptions("Les interventions dépasse 10 !!");     

        }
        }catch(JSONException e ){
            messageErreur="propriete <intervention> n'existe pas ou le format du texte est incorrecte!!";
            throw new ClassExceptions("propriete <intervention> n'existe pas ou le format du texte est incorrecte!!");
        }
    }
    

    public static void validerDistanceDeplacement(JSONObject jsonObject) throws ClassExceptions {
        try{
            int distanceDep =jsonObject.getInt(distance_deplacement);  
            
            if (distanceDep < 0 || distanceDep > 100) {
                messageErreur="la distance de deplacement doit etre entre 0 et 100 !!";
                throw new ClassExceptions("la distance de deplacement doit etre entre 0 et 100 !!");
        }
        }catch(JSONException e ){
            messageErreur="propriete <distance_deplacement> n'existe pas ou le format du texte est incorrecte!!";
            throw new ClassExceptions("propriete <distance_deplacement> n'existe pas ou le format du texte est incorrecte!!");
        }
    }
    
    public static void validerDate(JSONObject jsonObject) throws ClassExceptions {
        try{
            String maDate =jsonObject.getString(date_intervention);  
            
            String pattern = "^\\d{4}-\\d{2}-\\d{2}$";
            if (!maDate.matches(pattern)) {
            messageErreur="Format de date invalide !!";
            throw new ClassExceptions("Format de date invalide !!");
        }
        }catch(JSONException e ){
            messageErreur="propriete <date_intervention> n'existe pas ou le format du texte est incorrecte!!";
            throw new ClassExceptions("propriete <date_intervention> n'existe pas ou le format du texte est incorrecte!!");
        }
    }
    
    public static void validerCodeClient(JSONObject jsonObject) throws ClassExceptions {
        try{
            jsonObject.getString(code_client);  
            
        }catch(JSONException e ){
            messageErreur="propriete <code_client> n'existe pas ou le format du texte est incorrecte!!";
            throw new ClassExceptions("propriete <code_client> n'existe pas ou le format du texte est incorrecte!!");
        }
    }
    
    public static void validerOvertime(JSONObject jsonObject) throws ClassExceptions {
        try{
            int overT =jsonObject.getInt(overtime);  
            
            if(overT<0 || overT>4 ){ 
            messageErreur="Overtime doit etre entre 0 et 4 !!";
            throw new ClassExceptions("Overtime doit etre entre 0 et 4 !!");
        }
        }catch(JSONException e ){
            messageErreur="propriete <overtime> n'existe pas ou le format du texte est incorrecte!!";
            throw new ClassExceptions("propriete <overtime> n'existe pas ou le format du texte est incorrecte!!");
        }
    }
    
    public static void validerNombreHeures(JSONObject jsonObject) throws ClassExceptions {
        try{
            int nbrHeure =jsonObject.getInt(nombres_heures);  
            
            if (nbrHeure < 0 || nbrHeure > 8) {
            messageErreur="nombres heures negative !!"; 
            throw new ClassExceptions("nombres heures negative !!");
        }
        }catch(JSONException e ){
            messageErreur="propriete <nombres_heures> n'existe pas ou le format du texte est incorrecte!!";
            throw new ClassExceptions("propriete <nombres_heures> n'existe pas ou le format du texte est incorrecte!!");
        }
    }
    public static void validerConflitInetrventions(boolean isvalide ) throws ClassExceptions {
        
            if (isvalide) {
            messageErreur="La combinaison des valeurs des champs <code_client> et <date_intervention> " +
            "intervention n\'est pas unique !! "; 
            throw new ClassExceptions("La combinaison des valeurs des champs <code_client> et <date_intervention> " +
            "intervention n\'est pas unique !! ");
        }
        
    }
    
    
    
    private static boolean isValideTypeEmploye(int typeEmploye){
    
        return (typeEmploye != 0 && typeEmploye != 1 && typeEmploye != 2); 
    }    
    

}