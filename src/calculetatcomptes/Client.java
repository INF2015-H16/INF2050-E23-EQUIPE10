/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculetatcomptes;


public class Client {
    
    private String codeClient;
    private double etatParClient;      
    
        public Client(String codeClient, double etatParClient) {
        this.codeClient = codeClient;
        this.etatParClient = etatParClient;
    }    

    public Client() {        
    }
    
    public void setCodeClient(String codeClient){
        this.codeClient=codeClient;
    }    
    public void setEtatParClient(double etatParClient){
        this.etatParClient=etatParClient;
    }  
    public String getCodeClient(){
        return this.codeClient;
    }    
    public double getEtatParClient(){
        return this.etatParClient;
    }  
    
}
