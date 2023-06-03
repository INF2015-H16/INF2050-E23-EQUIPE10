/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculetatcomptes;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *class de methodes qui permet de gerer 
 * tous les montant calculer dans les autres class
 * et les regrouper dans un seul objet etatEmploye
 * 
 * @author rabahlemici
 */
public class GestionEtatCompte {
    
    public static final double MANTANT_AJOUTE=733.77;
    
    private static Client calculEtatClient(ObjetMontantRegulier montantReg,CalculMontantDeplacement montantDep,CalculeMontantSupplementaires montantSupp){
        
        
        Client etatClient=new Client();
        double montantRegulier=montantReg.getMontantRegulier();
        montantRegulier=changeFormatDecimal(montantRegulier);
        double montantDeplacement=montantDep.getMontantDeplacement();
        montantDeplacement=changeFormatDecimal(montantDeplacement);
        double montantHeuresSupp=montantSupp.getMontantHeuresSupp();
        montantHeuresSupp=changeFormatDecimal(montantHeuresSupp);
        double resultat=montantRegulier + montantDeplacement + montantHeuresSupp;
        resultat=changeFormatDecimal(resultat);
        etatClient.setEtatParClient(resultat);
        etatClient.setCodeClient(montantReg.getCodeClient());
        
        return etatClient;  
    }
    private static double calculEtatcompte(ObjetMontantRegulier [] montantReg,CalculMontantDeplacement [] montantDep,CalculeMontantSupplementaires [] monatantSupp){
        
        
        double etatCompte ;
        double etatClient=0;
        
        for(int i=0;i<montantReg.length;i++){
            etatClient+=montantReg[i].getMontantRegulier() + 
            montantDep[i].getMontantDeplacement() + 
            monatantSupp[i].getMontantHeuresSupp();            
        }
        
        etatCompte=etatClient+MANTANT_AJOUTE;
        etatCompte=changeFormatDecimal(etatCompte);
        
        return etatCompte;
    } 
    private static double calculMantantFix(double etatCompte){
        

        double mantantFix;

        mantantFix=etatCompte*1.2;
        mantantFix=changeFormatDecimal(mantantFix);
        
        return mantantFix;
    } 
    private static double calculCoutVariable(double etatCompte){
        
        double coutVariable;
        coutVariable=etatCompte*2.5;
        coutVariable=changeFormatDecimal(coutVariable);
        
        return coutVariable;
    } 
    public static EtatEmploye RemplirObjetEtatCompte() throws IOException{
        
        
        EtatEmploye objetEtatCompte=new EtatEmploye();
        Employe employe;
        employe=GestionEmploye.RecupererJson();
        objetEtatCompte.setMatriculeEmploye(employe.getMatricule());
             
        ObjetMontantRegulier[] montantReg= CalculMontantRegulier.calculMontant();
        CalculeMontantSupplementaires [] montantSupp=CalculeMontantSupplementaires.calculeMontantSupplementaire();
        CalculMontantDeplacement [] montantDep=CalculMontantDeplacement.calculMontanDeplacement();
        
        double etatCompte=calculEtatcompte(montantReg,montantDep,montantSupp);
        etatCompte=changeFormatDecimal(etatCompte);

        
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
    public static double changeFormatDecimal(double d){
        
        //DecimalFormat df=new DecimalFormat("0.00");

        //df.setMaximumFractionDigits(2);        
        //df.setMinimumFractionDigits(2);
        //df.setDecimalSeparatorAlwaysShown(true);
        //String s=df.format(d);
        String s=String.format("%.2f",d);
        s=s.replace(",", ".");
        d=Double.parseDouble(s);
        
        return d;
    }
    
}
