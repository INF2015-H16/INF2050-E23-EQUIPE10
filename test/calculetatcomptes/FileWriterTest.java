/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author Luz Suarez
 */
public class FileWriterTest {
    
    public FileWriterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of saveStringIntoFile method, of class FileWriter.
     */
    @Test
    public void testSaveStringIntoFile() throws Exception {
        System.out.println("saveStringIntoFile");
        String filePath = "";
        String contentToSave = "";
        FileWriter.saveStringIntoFile(filePath, contentToSave);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
