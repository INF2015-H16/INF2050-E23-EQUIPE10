/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculetatcomptes;

//import java.io.IOException;

import java.io.IOException;
import java.util.ArrayList;

//import java.util.ArrayList;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
/**
 *
 * @author AymenMessaad
 */
 
public class CalculeMontantSupplementaires {
    
    private String codeClient;
    private double MontantHeuresSupp=0;
    
    
   /**constructeur de la classe CalculeMontantSupplementaires.
    * 
    * @param codeCl
    * @param montantHeureSupp 
    */
    
    public CalculeMontantSupplementaires (String codeCl,double montantHeureSupp){
        
        this.MontantHeuresSupp=montantHeureSupp;
        this.codeClient=codeCl;
        
        
    }
     public CalculeMontantSupplementaires (){
    }
    
  
    /**cette methode elle verifier si le code client (code) de l'intervention est deja disponible sur le tableau (tab). 
     * le tableau tab prends tout les codes clients qu'on a deja calculer leur montant supplementaire.
     * 
     * @param code
     * @param tab
     * @return boolean compatible
     */
    
   public static boolean verifierLeCodeClient(String code, String tab []){
        
        boolean compatible = true ;
        
        for(int i =0 ; i < tab.length;i++){
          if (code.equals(tab[i])){
          compatible=false;
          }
        }
        
        return compatible;
    }
    /** cette methode elle va prendre en parametre le tableau dobjet et elle va le remplir avec un objet qui contient le montant supplimentaire
     * et le code client de chaque intervention.
     * faire des calcule de montant supplimentaire de chaque intervention.
     *  
     * @param tableau
     * @return > tableau
     *
     * @throws IOException 
     */
    public static CalculeMontantSupplementaires []  calculeMontantSupplementaire()throws IOException{
        CalculeMontantSupplementaires [] tableau;
        String codeCL="";
        int nombreDheureCl=0;
        int overtime=0;
        double MontantHeuresSupp=0;
        boolean var;        
       
        Employe employe;
        Intervention intervention;
        employe=GestionEmploye.RecupererJson();
        ArrayList<Intervention> interventions;
        interventions=employe.getInterventions();
        
        String codeClientRepetitif[]=new String[interventions.size()];
        int montantcd []=new int[interventions.size()];
        String codecl []=new String[interventions.size()];
        CalculeMontantSupplementaires [] tabo = new CalculeMontantSupplementaires[interventions.size()] ;
        
        // confirmation de type de l'employer 1 ou 2 
        if (employe.getTypeEmploye()== 1 || employe.getTypeEmploye()== 2 ){
         // parcouration de chaque intervention   
        for(int i=0;i<interventions.size();i++){
            // la recuperation du Code Client et le Nombre D'heures et le Overtime dec chaque intervention 
            intervention=interventions.get(i);
            codeCL = intervention.getCodeClient();
            nombreDheureCl=intervention.getNombresHeures()+2;
            overtime=intervention.getOvertime();
            // verification si le Code de client (premiere intervention) et compatible avec le code de client suivant (deuxieme intervention).
            if (verifierLeCodeClient(codeCL,codeClientRepetitif)){
                
           for (int j=i+1;j<interventions.size();j++){
               
               intervention=interventions.get(j);
               var = verifierLeCodeClient(codeCL,codeClientRepetitif);
               
               if(codeCL.equals(intervention.getCodeClient()) && var ){
                   
                   nombreDheureCl+=intervention.getNombresHeures()+2;
                   overtime+=intervention.getOvertime();
                   System.out.println("dkhel hna ");
                   montantcd[i]=nombreDheureCl;
               }
               
               
           }
           if (employe.getTypeEmploye()== 1){
               
            if(nombreDheureCl <=4){
                MontantHeuresSupp =0;
            }else if (nombreDheureCl >4 && nombreDheureCl <=8){
                MontantHeuresSupp=overtime*50;
            }else if (nombreDheureCl >8){
                MontantHeuresSupp=overtime*100;
            }
        
        }if (employe.getTypeEmploye()== 2){
            
            if(overtime <=4){
                MontantHeuresSupp =overtime*75;
            }else if (overtime >4){
                MontantHeuresSupp=overtime*150;
                if (MontantHeuresSupp >= 1500){
                     MontantHeuresSupp=1500;
                }
                }
            }
         CalculeMontantSupplementaires intervention1 = new CalculeMontantSupplementaires();
           intervention1.setCodeClient(codeCL);
           intervention1.setMontantHeuresSupp(MontantHeuresSupp);
           tabo[i]=intervention1;
           codeClientRepetitif[i]=codeCL;
         
           
         } 
        }
           

        }else if (employe.getTypeEmploye()== 0){
        MontantHeuresSupp=0;
        }
        tableau=new CalculeMontantSupplementaires[interventions.size()];

        for (int k = 0; k < tabo.length; k++) {
               if (tabo[k]!=null){
                 
               tableau[k]=tabo[k];
              
               }
            
             }
   
     return tableau;
    }
    
    public void setCodeClient(String codeClient){
        
        this.codeClient=codeClient;
    }
    public void setMontantHeuresSupp(double MontantHeuresSupp){
        
        this.MontantHeuresSupp=MontantHeuresSupp;
    }
    
    public String getCodeClient(){
        
        return codeClient;
    }
    public double getMontantHeuresSupp(){
        
        return MontantHeuresSupp;
        
    }
   
}