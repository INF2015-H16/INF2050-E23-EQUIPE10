/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculetatcomptes;

import java.util.ArrayList;

/**
 *Class des objets EtatEmploye
 * @author lemaicirabah
 */
public class EtatEmploye {
    
    private int matriculeEmploye;
    private double etatCompte;
    private double coutFixe;
    private double coutVariable;
    private ArrayList<Client> clients=new ArrayList<>();
    
    public void setMatriculeEmploye(int matriculeEmploye){
        this.matriculeEmploye=matriculeEmploye;
    }    
    public void setEtatCompte(double etatCompte){
        this.etatCompte=etatCompte;
    }  
    public void setCoutFixe(double coutFixe){
        this.coutFixe=coutFixe;
    }  
    public void setCoutVariable(double coutVariable){
        this.coutVariable=coutVariable;
    }   
    public int getMatriculeEmploye(){
        return this.matriculeEmploye;
    }    
    public double getEtatCompte(){
        return this.etatCompte;
    }  
    public double getCoutFixe(){
        return this.coutFixe;
    }  
    public double getCoutVariable(){
        return this.coutVariable;
        
    } 
    public  void setClients(ArrayList<Client>clients){
        
        this.clients=clients;
       
    }
    public  ArrayList<Client> getClients(){
        
        return this.clients;
    }
    
}
