/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculetatcomptes;

import java.io.IOException;

/**
 *
 * @author seif saidi
 */
public class CalculMontantRegulier {
    private static double convertireStringEnDouble(String chaine) throws IOException {
        double chaineConvertit;
        chaine = chaine.replace("$", "");
        chaineConvertit = Double.parseDouble(chaine);
        return chaineConvertit;
    }
    private static boolean verifCodesClient(String codeClient, String tab[]) {
        boolean verif = false;
        for (String tab1 : tab) {
            if (codeClient.equals(tab1)) {
                verif = true;
                break;
            }
        }
        return verif;
    }

    
}
