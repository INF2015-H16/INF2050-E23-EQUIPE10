package calculetatcomptes;

import java.util.ArrayList;

public class EtatEmploye {

    private int matriculeEmploye;
    private double etatCompte;
    private double coutFixe;
    private double coutVariable;
    private ArrayList<Client> clients = new ArrayList<>();

    public EtatEmploye(int matriculeEmploye, double etatCompte, double coutFixe, double coutVariable, ArrayList<Client> clients) {
        this.matriculeEmploye = matriculeEmploye;
        this.etatCompte = etatCompte;
        this.coutFixe = coutFixe;
        this.coutVariable = coutVariable;
        this.clients = clients;
    }

    public EtatEmploye() {
    }

    public void setMatriculeEmploye(int matriculeEmploye) {
        this.matriculeEmploye = matriculeEmploye;
    }

    public void setEtatCompte(double etatCompte) {
        this.etatCompte = etatCompte;
    }

    public void setCoutFixe(double coutFixe) {
        this.coutFixe = coutFixe;
    }

    public void setCoutVariable(double coutVariable) {
        this.coutVariable = coutVariable;
    }

    public int getMatriculeEmploye() {
        return this.matriculeEmploye;
    }

    public double getEtatCompte() {
        return this.etatCompte;
    }

    public double getCoutFixe() {
        return this.coutFixe;
    }

    public double getCoutVariable() {
        return this.coutVariable;

    }

    public void setClients(ArrayList<Client> clients) {

        this.clients = clients;

    }

    public ArrayList<Client> getClients() {

        return this.clients;
    }
  
}
