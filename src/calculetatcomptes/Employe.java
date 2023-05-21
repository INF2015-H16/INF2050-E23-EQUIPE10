/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculetatcomptes;

import java.util.ArrayList;

/**
 *
 * @author LP
 */
public class Employe {
    
    private int matricule;
    private int typeEmploye;
    private String tauxMin;
    private String tauxMax;
    private ArrayList<Intervention> interventions=new ArrayList<>();
           
    
    
    public void setMatricule(int matricule){
        this.matricule=matricule;
    }    
    public void setTypeEmploye(int typeEmploye){
        this.typeEmploye=typeEmploye;
    }  
    public void setTauxMin(String tauxMin){
        this.tauxMin=tauxMin;
    }  
    public void setTauxMax(String tauxMax){
        this.tauxMax=tauxMax;
    }   
    public int getMatricule(){
        return this.matricule;
    }    
    public int getTypeEmploye(){
        return this.typeEmploye;
    }  
    public String getTauxMin(){
        return this.tauxMin;
    }  
    public String getTauxMax(){
        return this.tauxMax;
        
    } 
    public  void setInterventions(ArrayList<Intervention>interventions){
        
        this.interventions=interventions;
       
    }
    public  ArrayList<Intervention> getInterventions(){
        
        return this.interventions;
       
    }
    
}
