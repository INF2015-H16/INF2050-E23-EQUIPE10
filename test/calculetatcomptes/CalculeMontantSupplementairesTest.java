/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package calculetatcomptes;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 256SSD
 */
public class CalculeMontantSupplementairesTest {

    public CalculeMontantSupplementairesTest () {
    }

    @BeforeClass
    public static void setUpClass () {
    }

    @AfterClass
    public static void tearDownClass () {
    }

    @Before
    public void setUp () {
    }

    @After
    public void tearDown () {
    }

    /**
     * Test of verifierLeCodeClient method, of class
     * CalculeMontantSupplementaires.
     */
    @Test
    public void testVerifierLeCodeClient () {
        System.out.println ( "verifierLeCodeClient" );
        String code = "C123";
        String[] tab = { "C123", "C456", "C789" };
        boolean expResult = false;
        boolean result = CalculeMontantSupplementaires.verifierLeCodeClient ( code, tab );
        assertEquals ( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of calculeMontantSupplementaire method, of class
     * CalculeMontantSupplementaires.
     */
    @Test
    public void testCalculeMontantSupplementaire () throws Exception {
        System.out.println ( "calculeMontantSupplementaire" );
        CalculeMontantSupplementaires[] expResult = { new CalculeMontantSupplementaires ( "C123", 1050.00 ), new CalculeMontantSupplementaires ( "C456", 225.00 ), new CalculeMontantSupplementaires ( "C789", 750.00 ) };
        CalculeMontantSupplementaires[] result = CalculeMontantSupplementaires.calculeMontantSupplementaire ();
        System.out.println ( "Tableau de résultats attendus:" );

        for ( CalculeMontantSupplementaires expResult1 : expResult ) {
            System.out.println ( "Code du client: " + expResult1.getCodeClient () + " Montant Supplementaires: " + expResult1.getMontantHeuresSupp () );
        }

        System.out.println ( "Tableau de résultats:" );
        for ( CalculeMontantSupplementaires result1 : result ) {
            System.out.println ( "Code du client: " + result1.getCodeClient () + " Montant Supplementaires " + result1.getMontantHeuresSupp () );
        }

        for ( int i = 0 ;i < result.length ;i++ ) {
            assertEquals ( expResult[ i ].getCodeClient (), result[ i ].getCodeClient () );
            assertEquals ( expResult[ i ].getMontantHeuresSupp (), result[ i ].getMontantHeuresSupp () );
        }

    }

    /**
     * Test of setCodeClient method, of class CalculeMontantSupplementaires.
     */
    @Test
    public void testSetCodeClient () {
        System.out.println ( "setCodeClient" );
        String codeClient = "";
        CalculeMontantSupplementaires instance = new CalculeMontantSupplementaires ();
        instance.setCodeClient ( codeClient );
    }

    /**
     * Test of setMontantHeuresSupp method, of class
     * CalculeMontantSupplementaires.
     */
    //@Test
    public void testSetMontantHeuresSupp () {
        System.out.println ( "setMontantHeuresSupp" );
        double MontantHeuresSupp = 0.0;
        CalculeMontantSupplementaires instance = new CalculeMontantSupplementaires ();
        instance.setMontantHeuresSupp ( MontantHeuresSupp );
    }

    /**
     * Test of getMontantHeuresSupp method, of class
     * CalculeMontantSupplementaires.
     */
    @Test
    public void testGetMontantHeuresSupp () {
        System.out.println ( "getMontantHeuresSupp" );
        CalculeMontantSupplementaires instance = new CalculeMontantSupplementaires ();
        double expResult = 0.0;
        double result = instance.getMontantHeuresSupp ();
        assertEquals ( expResult, result, 0 );
    }

}
