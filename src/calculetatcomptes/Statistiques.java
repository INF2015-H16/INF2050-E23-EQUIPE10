/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculetatcomptes;

/**
 *
 * @author Lemaici Rabah
 */
import java.util.HashMap;
import java.util.Map;

public class Statistiques {
    private int nombreTotalInterventions;
    private int nombreOccurrencesMoins1000;
    private int nombreOccurrencesEntre1000Et10000;
    private int nombreOccurrencesPlus10000;
    private int nombreInterventionsParTypeEmploye;
    private int nombreHeuresMaximal;
    private double etatMaximalPourClient;

    

    public int getNombreTotalInterventions() {
        return nombreTotalInterventions;
    }

    public void setNombreTotalInterventions(int nombreTotalInterventions) {
        this.nombreTotalInterventions = nombreTotalInterventions;
    }

    public int getNombreOccurrencesMoins1000() {
        return nombreOccurrencesMoins1000;
    }

    public void setNombreOccurrencesMoins1000(int nombreOccurrencesMoins1000) {
        this.nombreOccurrencesMoins1000 = nombreOccurrencesMoins1000;
    }

    public int getNombreOccurrencesEntre1000Et10000() {
        return nombreOccurrencesEntre1000Et10000;
    }

    public void setNombreOccurrencesEntre1000Et10000(int nombreOccurrencesEntre1000Et10000) {
        this.nombreOccurrencesEntre1000Et10000 = nombreOccurrencesEntre1000Et10000;
    }

    public int getNombreOccurrencesPlus10000() {
        return nombreOccurrencesPlus10000;
    }

    public void setNombreOccurrencesPlus10000(int nombreOccurrencesPlus10000) {
        this.nombreOccurrencesPlus10000 = nombreOccurrencesPlus10000;
    }

    public int getInterventionsParTypeEmploye() {
        return nombreInterventionsParTypeEmploye;
    }

    public void setInterventionsParTypeEmploye(int nombreInterventionsParTypeEmploye) {
        this.nombreInterventionsParTypeEmploye = nombreInterventionsParTypeEmploye;
    }

    public int getNombreHeuresMaximal() {
        return nombreHeuresMaximal;
    }

    public void setNombreHeuresMaximal(int nombreHeuresMaximal) {
        this.nombreHeuresMaximal = nombreHeuresMaximal;
    }

    public double getEtatMaximalPourClient() {
        return etatMaximalPourClient;
    }

    public void setEtatMaximalPourClient(double etatMaximalPourClient) {
        this.etatMaximalPourClient = etatMaximalPourClient;
    }
}
