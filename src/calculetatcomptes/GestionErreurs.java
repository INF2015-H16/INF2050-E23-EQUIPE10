/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculetatcomptes;

import static calculetatcomptes.CalculEtatComptes.creerJson;
import java.io.IOException;
import java.util.ArrayList;
import net.sf.json.JSONObject;

/**
 *
 * @author seif saidi
 */
public class GestionErreurs {

    public static void verifierEntierNegative(int nombre) throws ClassExceptions {
        if (nombre < 0) {
            // throw new ClassExceptions("Le code employée est invalide !!");

        }

    }

    public static void validerTypeEmploye(int type) throws ClassExceptions, IOException {
        JSONObject objetMessageErreur = CalculEtatComptes.creerJsonErreurMessage("Type employée est invalide !!");
        if (type != 0 && type != 1 && type != 2) {

            CalculEtatComptes.ecrireFichierSortie("C:\\Users\\seif saidi\\Desktop\\Tp session d'été\\projet inf2050\\INF2050-E23-EQUIPE10\\sortiee.txt", objetMessageErreur);
        }

    }

    public static void validerTauxHoraires(double tauxHoraire) throws ClassExceptions, IOException {
        JSONObject objetMessageErreur = CalculEtatComptes.creerJsonErreurMessage("Taux horaires est invalide !!");

        if (tauxHoraire < 0) {
            CalculEtatComptes.ecrireFichierSortie("C:\\Users\\seif saidi\\Desktop\\Tp session d'été\\projet inf2050\\INF2050-E23-EQUIPE10\\sortiee.txt", objetMessageErreur);
        }

    }

    public static void validerIntervention(ArrayList<Intervention> objetEmploye) throws ClassExceptions, IOException {
        JSONObject objetMessageErreur = CalculEtatComptes.creerJsonErreurMessage("Auccune intervention !!");
        JSONObject objetMessageErreur1 = CalculEtatComptes.creerJsonErreurMessage("Les interventions dépasse 10 !!");

        if (objetEmploye.isEmpty()) {
            CalculEtatComptes.ecrireFichierSortie("C:\\Users\\seif saidi\\Desktop\\Tp session d'été\\projet inf2050\\INF2050-E23-EQUIPE10\\sortiee.txt", objetMessageErreur);

        } else if (objetEmploye.size() > 10) {
            CalculEtatComptes.ecrireFichierSortie("C:\\Users\\seif saidi\\Desktop\\Tp session d'été\\projet inf2050\\INF2050-E23-EQUIPE10\\sortiee.txt", objetMessageErreur1);

           

        }

    }

    public static void validerDistanceDeplacement(int distanceDep) throws ClassExceptions, IOException {
        JSONObject objetMessageErreur = CalculEtatComptes.creerJsonErreurMessage("La distance de deplacement doit etre entre 0 et 100 !!");

        if (distanceDep < 0 || distanceDep > 100) {
            CalculEtatComptes.ecrireFichierSortie("C:\\Users\\seif saidi\\Desktop\\Tp session d'été\\projet inf2050\\INF2050-E23-EQUIPE10\\sortiee.txt", objetMessageErreur);

        }

    }

    public static void validerDate(String maDate) throws ClassExceptions, IOException {
        JSONObject objetMessageErreur = CalculEtatComptes.creerJsonErreurMessage("Format de date invalide !!");

        String pattern = "^\\d{4}-\\d{2}-\\d{2}$";
        if (!maDate.matches(pattern)) {
            CalculEtatComptes.ecrireFichierSortie("C:\\Users\\seif saidi\\Desktop\\Tp session d'été\\projet inf2050\\INF2050-E23-EQUIPE10\\sortiee.txt", objetMessageErreur);

        }

    }

}
