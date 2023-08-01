/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculetatcomptes;

import static calculetatcomptes.EmployeTest.instance;
import static calculetatcomptes.EtatEmployeTest.instance;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Napo
 */
public class GestionErreursTest {

    static JSONObject jsonObject;
    static Intervention intervention;
    static ArrayList<Intervention> interventions;

    public GestionErreursTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        jsonObject = new JSONObject();
        intervention = new Intervention();
        interventions = new ArrayList<>();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of validerMatriculeEmploye method, of class GestionErreurs.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = ClassExceptions.class)
    public void testValiderMatriculeEmploye() throws Exception {
        System.out.println("validerMatriculeEmploye");
        jsonObject.accumulate("matricule_employe", -1);
        GestionErreurs.validerMatriculeEmploye(jsonObject);
    }

    /**
     * Test of validerTypeEmploye method, of class GestionErreurs.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = ClassExceptions.class)
    public void testValiderTypeEmploye() throws Exception {
        System.out.println("validerTypeEmploye");
        jsonObject.accumulate("type_employe", 3);
        GestionErreurs.validerTypeEmploye(jsonObject);
    }

    /**
     * Test of validerTauxHorairesMin method, of class GestionErreurs.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = ClassExceptions.class)
    public void testValiderTauxHorairesMin() throws Exception {
        System.out.println("validerTauxHorairesMin");
        jsonObject.accumulate("taux_horaire_min", -1);
        GestionErreurs.validerTauxHorairesMin(jsonObject);
    }

    /**
     * Test of validerTauxHorairesMax method, of class GestionErreurs.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = ClassExceptions.class)
    public void testValiderTauxHorairesMax() throws Exception {
        System.out.println("validerTauxHorairesMax");
        jsonObject.accumulate("taux_horaire_max", -1);
        GestionErreurs.validerTauxHorairesMax(jsonObject);

    }

    /**
     * Test of validerIntervention method, of class GestionErreurs.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = ClassExceptions.class)
    public void testValiderIntervention() throws Exception {
        System.out.println("validerIntervention");
        Intervention[] tabinterventions = {};       
        Collections.addAll(interventions, tabinterventions);
        jsonObject.accumulate("interventions", interventions);
        GestionErreurs.validerIntervention(jsonObject);
        //jsonObject.remove("interventions", interventions);
    }

    /**
     * Test of validerDistanceDeplacement method, of class GestionErreurs.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = ClassExceptions.class)
    public void testValiderDistanceDeplacement() throws Exception {
        System.out.println("validerDistanceDeplacement");
        intervention.setDistanceDeplacement(-10);
        interventions.add(intervention);
        jsonObject.accumulate("interventions", interventions);
        GestionErreurs.validerDistanceDeplacement(jsonObject);
    }

    /**
     * Test of validerDate method, of class GestionErreurs.
     * @throws java.lang.Exception
     */  
    @Test(expected = ClassExceptions.class)
    public void testValiderDate() throws Exception {
        System.out.println("validerDate");
        intervention.setDateIntervention(" ");
        interventions.add(intervention);
        jsonObject.accumulate("interventions", interventions);           
        GestionErreurs.validerDate(jsonObject);
    }
     
    /**
     * Test of validerCodeClient method, of class GestionErreurs.
     * @throws java.lang.Exception 
     */          
    @Test(expected = ClassExceptions.class)
    public void testValiderCodeClient() throws Exception {
        System.out.println("validerCodeClient");
        intervention.setCodeClient(" ");
        interventions.add(intervention);
        jsonObject.accumulate("interventions", interventions); 
        GestionErreurs.validerCodeClient(jsonObject);      
    }
     
    /**
     * Test of validerOvertime method, of class GestionErreurs.
     * @throws java.lang.Exception
     */
    @Test(expected = ClassExceptions.class)
    public void testValiderOvertime() throws Exception {
        System.out.println("validerOvertime");
        intervention.setOvertime(-1);
        interventions.add(intervention);
        jsonObject.accumulate("interventions", interventions); 
        GestionErreurs.validerOvertime(jsonObject);
    }
    
    /**
     * Test of validerNombreHeures method, of class GestionErreurs.
     * @throws java.lang.Exception
     */
    @Test(expected = ClassExceptions.class)
    public void testValiderNombreHeures() throws Exception {
        System.out.println("validerNombreHeures");
        intervention.setNombresHeures(-1);
        interventions.add(intervention);
        jsonObject.accumulate("interventions", interventions); 
        GestionErreurs.validerNombreHeures(jsonObject);
    }
     
    /**
     * Test of validerConflitInterventions method, of class GestionErreurs.
     * @throws java.lang.Exception
     */
    @Test
    public void testValiderConflitInterventions() throws Exception {
        System.out.println("validerConflitInterventions");
        boolean isvalide = false;
        GestionErreurs.validerConflitInterventions(isvalide);
    }
    
    /**
     * Test of convertStringToLocalDate method, of class GestionErreurs.
     */
    @Test
    public void testConvertStringToLocalDate() {
        System.out.println("convertStringToLocalDate");
        String dateString = "2023-03-15";
        LocalDate expResult = LocalDate.of(2023, 3, 15);
        LocalDate result = GestionErreurs.convertStringToLocalDate(dateString);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of validerEcartDate method, of class GestionErreurs.
     * @throws java.lang.Exception
     */
    @Test
    public void testValiderEcartDate() throws Exception {
        System.out.println("validerEcartDate");
        Intervention[] tabIntervention = {new Intervention ("C123", 4, 0, 4,"2023-04-14"),
            new Intervention ("C123", 4, 0, 4,"2023-04-14")};
        Collections.addAll(interventions, tabIntervention);
        GestionErreurs.validerEcartDate(interventions);
    }
     
    /**
     * Test of depasserCoutFix method, of class GestionErreurs.
     */
    @Test
    public void testDepasserCoutFix() {
        System.out.println("depasserCoutFix");
        double coutFix = 0.0;
        GestionErreurs.depasserCoutFix(coutFix);
    }
    
    /**
     * Test of depasserEtatCompte method, of class GestionErreurs.
     */
    @Test
    public void testDepasserEtatCompte() {
        System.out.println("depasserEtatCompte");
        double etatCompte = 0.0;
        GestionErreurs.depasserEtatCompte(etatCompte);
    }
    
    /**
     * Test of depasserTauxHoraire method, of class GestionErreurs.
     */
    @Test
    public void testDepasserTauxHoraire() {
        System.out.println("depasserTauxHoraire");
        double tauxHoraireMin = 0.0;
        double tauxHoraireMax = 0.0;
        GestionErreurs.depasserTauxHoraire(tauxHoraireMin, tauxHoraireMax);
    }
    
    /**
     * Test of depasserDistanceDeplacement method, of class GestionErreurs.
     */
    @Test
    public void testDepasserDistanceDeplacement() {
        System.out.println("depasserDistanceDeplacement");
        //String codeClient, int distanceDeplacement, int overtime, int nombresHeures, String dateIntervention
        Intervention[] tabIntervention = {new Intervention ("C123", 4, 0, 4,"2023-04-14"), new Intervention ("C123", 4, 0, 4,"2023-04-14")};
        
        ArrayList<Intervention> interventions_1 = new ArrayList<>();
        Collections.addAll(interventions_1, tabIntervention);
        
        //int matricule, int typeEmploye, double tauxMin, double tauxMax, ArrayList<Intervention> interventions        
        Employe employe = new Employe (1234, 1, 10.2, 20.0, interventions_1);
        
        GestionErreurs.depasserDistanceDeplacement(employe);
    }
     
    /**
     * Test of depasserEtatCompteCoutVariable method, of class GestionErreurs.
     */
    @Test
    public void testDepasserEtatCompteCoutVariable() {
        System.out.println("depasserEtatCompteCoutVariable");
                //String codeClient, double etatParClient
        Client[] tabClient = {new Client("C123", 858.0), new Client("C456", 817.0), new Client("C789", 1219.5)};
        ArrayList<Client> clients = new ArrayList<>();
        Collections.addAll(clients, tabClient);

        //int matriculeEmploye, double etatCompte, double coutFixe, double coutVariable, ArrayList<Client> clients = new ArrayList<>();      
        EtatEmploye etatEmploye = new EtatEmploye(123456789, 3628.25, 43.55, 90.70, clients);
        
        GestionErreurs.depasserEtatCompteCoutVariable(etatEmploye);
    }   
}
