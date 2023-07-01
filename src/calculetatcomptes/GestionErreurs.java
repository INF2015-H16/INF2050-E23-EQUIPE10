/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculetatcomptes;

import java.io.IOException;
import java.util.ArrayList;
import net.sf.json.JSONObject;

/**
 *
 * @author seif saidi
 */
public class GestionErreurs {
    
    static public String messageErreur;

    public static void verifierEntierNegative(int nombre) throws ClassExceptions {
        if (nombre < 0) {
            messageErreur="Code employée est invalide !!";
            throw new ClassExceptions("Code employée invalide !!");

        }

    }

    public static void validerTypeEmploye(int type) throws Exception, ClassExceptions {
        
        if (type != 0 && type != 1 && type != 2) {
           messageErreur="Type employée invalide !!";
           throw new ClassExceptions("Type employée invalide !!");
        }

    }

    public static void validerTauxHoraires(double tauxHoraire) throws ClassExceptions, IOException {
        
        if (tauxHoraire < 0) {
            messageErreur="Taux horaires invalide !!";
            throw new ClassExceptions("Taux horaires invalide !!");
        }

    }

    public static void validerIntervention(ArrayList<Intervention> objetEmploye) throws ClassExceptions, IOException {
        
        if (objetEmploye.isEmpty()) {
            messageErreur="Auccune intervention !!";
            throw new ClassExceptions("Auccune intervention !!");

        } else if (objetEmploye.size() > 10) {
            messageErreur="Les interventions dépasse 10 !!";
            throw new ClassExceptions("Les interventions dépasse 10 !!");     

        }
    }
    public static void validerDistanceDeplacement(int distanceDep) throws ClassExceptions, IOException {

        if (distanceDep < 0 || distanceDep > 100) {
            messageErreur="la distance doit etre entre 0 et 100 !!";
            throw new ClassExceptions("la distance doit etre entre 0 et 100 !!");

        }

    }

    public static void validerDate(String maDate) throws ClassExceptions, IOException {
        //JSONObject objetMessageErreur = CalculEtatComptes.creerJsonErreurMessage("Format de date invalide !!");

        String pattern = "^\\d{4}-\\d{2}-\\d{2}$";
        if (!maDate.matches(pattern)) {
            messageErreur="Format de date invalide !!";
            throw new ClassExceptions("Format de date invalide !!");

        }
        
    }
    public static void validerOvertime(int overT) throws ClassExceptions{
       
        if(overT<0 || overT>4 ){ 
            messageErreur="Overtime doit etre entre 0 et 4 !!";
            throw new ClassExceptions("Overtime doit etre entre 0 et 4 !!");
        }
        
    }
    public static void validerNombreHeures(double nbrHeure)  throws ClassExceptions{
         if (nbrHeure < 0) {
            messageErreur="nombre heures negative !!"; 
            throw new ClassExceptions("nombre heures negative !!");
        }
    } 
public String getMessageErr(){  
  return this.messageErreur;  
}
public void setMessageErr(String s){
    this.messageErreur=s;
    
}
}