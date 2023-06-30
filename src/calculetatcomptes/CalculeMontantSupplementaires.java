/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculetatcomptes;

import java.io.IOException;
import java.util.ArrayList;


/**
 * classe qui sert à calculer le montant Supplementaire 
 *  des interventions de chaque client selon le type d'employé. 
 * appel la methode pour faire les calcules dans la classe principal du projet ( calculetatcomptes ).
 * @author AymenMessaad
 */
public class CalculeMontantSupplementaires {

    private String codeClient;
    private double MontantHeuresSupp = 0;

    /**
     * constructeur de la classe CalculeMontantSupplementaires.
     *
     * @param codeCl
     * @param montantHeureSupp
     */
    public CalculeMontantSupplementaires ( String codeCl, double montantHeureSupp ) {

        this.MontantHeuresSupp = montantHeureSupp;
        this.codeClient = codeCl;

    }

    public CalculeMontantSupplementaires () {
    }

    /**
     * cette methode elle verifier si le code client (code) de l'intervention
     * est deja disponible sur le tableau (tab). le tableau tab prends tout les
     * codes client qu'on a deja calculer leur montant supplementaire.
     *
     * @param code
     * @param tab
     * @return boolean compatible
     */
    public static boolean verifierLeCodeClient ( String code, String tab[] ) {

        boolean compatible = true;

        for ( int i = 0 ;i < tab.length ;i++ ) {
            if ( code.equals ( tab[ i ] ) ) {
                compatible = false;
            }
        }

        return compatible;
    }

    /**
     * cette methode elle va prendre en parametre le tableau d'objet et elle va
     * le remplir avec un objet qui contient le montant supplimentaire et le
     * code client de chaque intervention. 
     * faire des calcule de montant
     * supplimentaire de chaque intervention.
     *
     * @return > tableau
     *
     * @throws IOException
     * @throws calculetatcomptes.ClassExceptions
     */
    public static CalculeMontantSupplementaires[] calculeMontantSupplementaire () throws IOException, ClassExceptions {
        CalculeMontantSupplementaires[] tableau;

        String codeCL = "";
        int nombreDheureCl = 0;
        int overtime = 0;
        double MontantHeuresSupp = 0;
        boolean var;
        int tailleTableau = 0;

        Employe employe;
        Intervention intervention;
        employe = GestionEmploye.creerEmployeFromJson ();
        ArrayList<Intervention> interventions;
        interventions = employe.getInterventions ();

        String codeClientRepetitif[] = new String[ interventions.size () ];
        int montantcd[] = new int[ interventions.size () ];
        String codecl[] = new String[ interventions.size () ];
        CalculeMontantSupplementaires[] tabo = new CalculeMontantSupplementaires[ interventions.size () ];

        if ( employe.getTypeEmploye () == 1 || employe.getTypeEmploye () == 2 || employe.getTypeEmploye () == 0) {
            for ( int i = 0 ;i < interventions.size () ;i++ ) {
                intervention = interventions.get ( i );
                codeCL = intervention.getCodeClient ();
                nombreDheureCl = intervention.getNombresHeures ();
                overtime = intervention.getOvertime () + 2;
                if ( verifierLeCodeClient ( codeCL, codeClientRepetitif ) ) {
                    tailleTableau += 1;
                    for ( int j = i + 1 ;j < interventions.size () ;j++ ) {
                        intervention = interventions.get ( j );
                        var = verifierLeCodeClient ( codeCL, codeClientRepetitif );
                        if ( codeCL.equals ( intervention.getCodeClient () ) && var ) {
                            nombreDheureCl += intervention.getNombresHeures ();
                            overtime += intervention.getOvertime () + 2;
                            montantcd[ i ] = nombreDheureCl;
                        }
                    }
                    if ( employe.getTypeEmploye () == 1 ) {

                        if ( nombreDheureCl <= 4 ) {
                            MontantHeuresSupp = 0;
                        } else if ( nombreDheureCl > 4 && nombreDheureCl <= 8 ) {
                            MontantHeuresSupp = overtime * 50;
                        } else if ( nombreDheureCl > 8 ) {
                            MontantHeuresSupp = overtime * 100;
                        }

                    }
                    if ( employe.getTypeEmploye () == 2 ) {

                        if ( overtime <= 4 ) {
                            MontantHeuresSupp = overtime * 75;
                        } else if ( overtime > 4 ) {
                            MontantHeuresSupp = overtime * 150;
                            if ( MontantHeuresSupp >= 1500 ) {
                                MontantHeuresSupp = 1500;
                            }
                        }
                    }if ( employe.getTypeEmploye () == 0 ) {
                       MontantHeuresSupp = 0;
                         }
                    CalculeMontantSupplementaires intervention1 = new CalculeMontantSupplementaires ();
                    intervention1.setCodeClient ( codeCL );
                    intervention1.setMontantHeuresSupp ( MontantHeuresSupp );
                    tabo[ i ] = intervention1;
                    codeClientRepetitif[ i ] = codeCL;

                }
            }

        }
        tableau = new CalculeMontantSupplementaires[ tailleTableau ];
        for ( int k = 0 ;k < tabo.length ;k++ ) {
            if ( tabo[ k ] != null ) {
                tableau[ k ] = tabo[ k ];
            }

        }
        return tableau;
    }

    /**
     *
     * @param codeClient
     */
    public void setCodeClient ( String codeClient ) {

        this.codeClient = codeClient;
    }

    /**
     *
     * @param MontantHeuresSupp
     */
    public void setMontantHeuresSupp ( double MontantHeuresSupp ) {

        this.MontantHeuresSupp = MontantHeuresSupp;
    }

    /**
     *
     * @return codeClient
     */
    public String getCodeClient () {

        return codeClient;
    }

    /**
     *
     * @return MontantHeuresSupp
     */
    public double getMontantHeuresSupp () {

        return MontantHeuresSupp;

    }

}
