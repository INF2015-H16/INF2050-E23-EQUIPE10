/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculetatcomptes;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author LP
 */
public class CalculEtatComptes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        Employe employe;
        Intervention intervention;
        employe=GestionEmploye.RecupererJson();
        ArrayList<Intervention> interventions;
        interventions=employe.getInterventions();
        intervention=interventions.get(1);
        
        System.out.print("matricule: "+employe.getMatricule()+"\ntaux horaires maximal :\nnombre d'heure du client <> est : "+intervention.getNombresHeures()  );

        //System.out.print("test projet");
        // TODO code application logic here
    }
    
}
