/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculetatcomptes;

/**
 *
 * @author LP
 */
public class Employe {
    
    private int matricule;
    private int typeEmploye;
    private double tauxMin;
    private double tauxMax;
   //private Intervention interventions;
           
    
    
    public void setMatricule(int matricule){
        this.matricule=matricule;
    }    
    public void setTypeEmploye(int typeEmploye){
        this.typeEmploye=typeEmploye;
    }  
    public void setTauxMin(double tauxMin){
        this.tauxMin=tauxMin;
    }  
    public void setTauxMax(double tauxMax){
        this.tauxMax=tauxMax;
    }   
    public int getMatricule(int matricule){
        return this.matricule;
    }    
    public int getTypeEmploye(int typeEmploye){
        return this.typeEmploye;
    }  
    public double getTauxMin(double tauxMin){
        return this.tauxMin;
    }  
    public double getTauxMax(){
        return this.tauxMax;
        
    } 
    
    
    
}
