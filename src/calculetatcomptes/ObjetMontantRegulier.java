
package calculetatcomptes;

public class ObjetMontantRegulier {

    private String codeClient;
    private double montantRegulier;

    public ObjetMontantRegulier(String codeClient, double montantRegulier) {
        this.codeClient = codeClient;
        this.montantRegulier = montantRegulier;

    }

    ObjetMontantRegulier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getCodeClient() {
        return codeClient;
    }

    public double getMontantRegulier() {
        return montantRegulier;
    }

    public void setCodeClient(String codeClient) {
        this.codeClient = codeClient;
    }

    public void setMontantRegulier(double montantRegulier) {
        this.montantRegulier = montantRegulier;
    }

}
