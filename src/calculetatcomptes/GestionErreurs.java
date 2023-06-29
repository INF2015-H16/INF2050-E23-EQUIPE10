/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculetatcomptes;

import java.util.ArrayList;



/**
 *
 * @author seif saidi
 */
public class GestionErreurs {

    public static void verifierEntierNegative(int nombre) throws ClassExceptions {
        if (nombre < 0) {
            throw new ClassExceptions("Le code employée est invalide !!");
        }
        
    }
    
     public static void validerTypeEmploye(int type)  throws ClassExceptions{
    if(type != 0 && type != 1 && type != 2 ){
        throw new ClassExceptions("Type employée est invalide !!");
    
    }
   
    }
     
      public static void validerTauxHoraires(double tauxHoraire)  throws ClassExceptions{
         if (tauxHoraire < 0) {
            throw new ClassExceptions("Taux horaire négative !!");
        }
         
    }
    
      public static void validerIntervention(ArrayList<Intervention> objetEmploye) throws ClassExceptions{
        
        if(objetEmploye.isEmpty()){
             throw new ClassExceptions("Auccune intervention !!");
        } 
        
        else if(objetEmploye.size()>10){
            throw new ClassExceptions("Les interventions dépasse 10 !!");
        
        }
    
    }
    public static void validerDistanceDeplacement(int distanceDep) throws ClassExceptions{
        
        if(distanceDep<0 || distanceDep>100){
             throw new ClassExceptions("La distance de deplacement doit etre entre 0 et 100 !!");
        } 
        
    
    }
   
   
   
}
