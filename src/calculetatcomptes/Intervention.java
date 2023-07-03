/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculetatcomptes;

public class Intervention {       
    
    private String codeClient;
    private int distanceDeplacement;
    private int overtime;
    private int nombresHeures;
    private String dateIntervention;
    
    public void setCodeClient(String codeClient){
        this.codeClient=codeClient;
    }    
    public void setDistanceDeplacement(int distanceDeplacement){
        this.distanceDeplacement=distanceDeplacement;
    }  
    public void setOvertime(int overtime){
        this.overtime=overtime;
    }  
    public void setNombresHeures(int nombresHeures){
        this.nombresHeures=nombresHeures;
    }
    public void setDateIntervention(String dateIntervention){
        this.dateIntervention=dateIntervention;
    }
    public int getDistanceDeplacement(){
        return this.distanceDeplacement;
    }
    public String getCodeClient(){
        return this.codeClient;
    }    
    public int getOvertime(){
        return this.overtime;
    }  
    public int getNombresHeures(){
        return this.nombresHeures;
    }  
    public String getDateIntervention(){
        return this.dateIntervention;
        
    } 
    
}
