/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculetatcomptes;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author rabahlemici
 */
public class GestionEtatCompte {
    
    public static final double MANTANT_AJOUTE=733.77;
    
    private static Client calculEtatClient(ObjetMontantRegulier montantReg,CalculMontantDeplacement montantDep,CalculeMontantSupplementaires montantSupp){
        
        
        Client etatClient=new Client();
        etatClient.setEtatParClient(montantReg.getMontantRegulier() + montantDep.getMontantDeplacement() + montantSupp.getMontantHeuresSupp());
        etatClient.setCodeClient(montantReg.getCodeClient());
        return etatClient;  
    }
    private static double calculEtatcompte(ObjetMontantRegulier [] montantReg,CalculMontantDeplacement [] montantDep,CalculeMontantSupplementaires [] monatantSupp){
        
        double etatCompte ;
        
        double etatClient=0;
        
        ArrayList <Client> clients=new ArrayList<>(); 
        for(int i=0;i<montantReg.length;i++){
            etatClient+=montantReg[i].getMontantRegulier() + 
            montantDep[i].getMontantDeplacement() + monatantSupp[i].getMontantHeuresSupp();            
        }
        
        etatCompte=etatClient+MANTANT_AJOUTE;        
        return etatCompte;
    } 
    private static double calculMantantFix(double etatCompte){
        
        double mantantFix;
        
        mantantFix=etatCompte*1.2;
        
        return mantantFix;
    } 
    private static double calculCoutVariable(double etatCompte){
        
        double coutVariable;
        
        coutVariable=etatCompte*2.5;
        
        return coutVariable;
    } 
    public static EtatEmploye RemplirObjetEtatCompte() throws IOException{
        
        //DecimalFormat df=new DecimalFormat("0.00");
        
        EtatEmploye objetEtatCompte=new EtatEmploye();
        Employe employe;
        employe=GestionEmploye.RecupererJson();
        objetEtatCompte.setMatriculeEmploye(employe.getMatricule());
        
       
             
        ObjetMontantRegulier[] montantReg= CalculMontantRegulier.calculMontant();
        CalculeMontantSupplementaires [] montantSupp=CalculeMontantSupplementaires.calculeMontantSupplementaire();
        CalculMontantDeplacement [] montantDep=CalculMontantDeplacement.calculMontanDeplacement();
        
        double etatCompte=calculEtatcompte(montantReg,montantDep,montantSupp);
        etatCompte=(double)Math.round(etatCompte*100.0)/100.0;
        objetEtatCompte.setEtatCompte(etatCompte);
        
        objetEtatCompte.setCoutFixe(calculMantantFix(etatCompte));
        objetEtatCompte.setCoutVariable(calculCoutVariable(etatCompte));
        
        Client client;
        ArrayList <Client> clients=new ArrayList<>(); 
        
        for(int i=0;i<montantReg.length;i++){
            
            client = calculEtatClient(montantReg[i],montantDep[i],montantSupp[i]);
            clients.add(client);
        }
        
        objetEtatCompte.setClients(clients);
        
        return objetEtatCompte;
    }
    
}
