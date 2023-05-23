/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculetatcomptes;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author lesuareg
 */
public class CalculMontantDeplacement {

    /**
     *
     * @param employes
     * @return
     */
    public static JSONArray calcul(JSONArray employes) {

        JSONArray interventions = new JSONArray();
        JSONArray montant = new JSONArray();
        JSONArray etatClient = new JSONArray();

        JSONObject unMontant = new JSONObject();
        JSONObject unEtatClient = new JSONObject();

        JSONObject unEmploye, uneIntervention, uneAutreIntervention;

        for (int i = 0; i < employes.size(); i++) {
            unEmploye = employes.getJSONObject(i);

            if (unEmploye.getInt("type_employe") == 0) {
                unEtatClient.put("matricule_employe", unEmploye.getString("matricule_employe"));
                interventions = unEmploye.getJSONArray("interventions");
                double cout = 0;
                for (int j = 0; j < interventions.size() - 1; j++) {
                    uneIntervention = interventions.getJSONObject(j);

                    for (int k = j + 1; k < interventions.size(); k++) {
                        uneAutreIntervention = interventions.getJSONObject(k);

                        if (uneIntervention.getString("code_client").equalsIgnoreCase(uneAutreIntervention.getString("code_client"))) {
                            cout += 200.00 - ((uneIntervention.getInt("distance_deplacement") + uneAutreIntervention.getInt("distance_deplacement")*(5/100)));//*montant regulier   
                            unMontant.put("code_client", uneIntervention.getString("code_client"));
                            unMontant.put("etat_par_client", cout);
                            montant.add(unMontant);
                            cout = 0;
                        }
                    }
                    if (!uneIntervention.getString("code_client").equalsIgnoreCase(unMontant.getString("code_client"))) {
                        cout += 200.00 - (uneIntervention.getInt("distance_deplacement")*(5/100)); //*montant regulier                     
                        unMontant.put("code_client", uneIntervention.getString("code_client"));
                        unMontant.put("etat_par_client", cout);
                        montant.add(unMontant);
                        cout = 0;
                    }
                }
                unEtatClient.put("clients", montant);
                etatClient.add(unEtatClient);
                montant.clear();
            }
        }
        return etatClient;
    }
}
