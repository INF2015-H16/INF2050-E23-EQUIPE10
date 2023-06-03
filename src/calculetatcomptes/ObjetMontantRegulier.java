/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculetatcomptes;

/**
 *
 * @author seif saidi
 */
public class ObjetMontantRegulier {

    private String codeClient;
    private double montantRegulier;

    /**
     * Constructeur de l'objet montantRegulier
     *
     * @param codeClient
     * @param montantRegulier
     */
    public ObjetMontantRegulier(String codeClient, double montantRegulier) {
        this.codeClient = codeClient;
        this.montantRegulier = montantRegulier;

    }

    /**
     * Methode pour apporter un code client
     *
     * @return
     */
    public String getCodeClient() {
        return codeClient;
    }

    /**
     * Methode pour apporter le montant regulier
     *
     * @return
     */
    public double getMontantRegulier() {
        return montantRegulier;
    }

    /**
     * Methode pour modifier le code client
     *
     * @param codeClient
     */
    public void setCodeClient(String codeClient) {
        this.codeClient = codeClient;
    }

    /**
     * Methode pour modifier le montant régulier
     *
     * @param montantRegulier
     */
    public void setMontantRegulier(double montantRegulier) {
        this.montantRegulier = montantRegulier;
    }

}
