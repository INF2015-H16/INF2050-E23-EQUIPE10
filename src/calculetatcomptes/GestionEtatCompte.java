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
    /**
     * Methode pour calculer l"état de compte de chaque client en fonction du montant régulier, montant de déplacement et montant supplémentaire
     * Elle prend les paramétre d'entrée ci-dessous :
     * @param montantReg
     * @param montantDep
     * @param montantSupp
     * @return 
     */
    
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
    
    /**
     *  Methode pour calculer l"état de compte de l'employé en fonction du montant régulier, montant de déplacement et montant supplémentaire
     * Elle prend les paramétre d'entrée ci-dessous :
     * @param montantReg
     * @param montantDep
     * @param monatantSupp
     * @return 
     */
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
    
    /**
     * Cette méthode permet de calculer le montant fix en fonction de l'état de compte.
     * @param etatCompte
     * @return 
     */
    private static double calculMantantFix(double etatCompte){
        

        double mantantFix;

        mantantFix=etatCompte*0.012;
        mantantFix=changeFormatDecimal(mantantFix);
        
        return mantantFix;
    } 
    
    /**
     * Cette méthode permet de calculer le cout variable en fonction de l'état de compte.
     * @param etatCompte
     * @return 
     */
    private static double calculCoutVariable(double etatCompte){
        
        double coutVariable;
        coutVariable=etatCompte*0.025;
        coutVariable=changeFormatDecimal(coutVariable);
        
        return coutVariable;
    }
    /**
     * Cette fonction permet de remplir un objet par l"état de compte de l'employé
     * @return
     * @throws IOException 
     */
    public static EtatEmploye RemplirObjetEtatCompte() throws IOException, ClassExceptions{
        
        
        EtatEmploye objetEtatCompte=new EtatEmploye();
        Employe employe;
        employe=GestionEmploye.creerEmployeFromJson();
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
    /**
     * Cette méthode permet de modifier la partie décimal d'un double tel que deux chiffres aprés son virgule
     * Elle prend comme entrée un double
     * @param d
     * @return 
     */
    public static double changeFormatDecimal(double d){
        
        DecimalFormat df=new DecimalFormat("#.00");
 
        String s=df.format(d);
        s=s.replace(",", ".");
        d=Double.parseDouble(s);
        
        return d;
    }
    
}
