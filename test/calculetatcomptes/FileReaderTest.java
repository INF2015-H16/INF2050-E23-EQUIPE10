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
public class FileReaderTest {
    
    public FileReaderTest() {
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
     * Test of loadFileIntoString method, of class FileReader.
     * @throws java.lang.Exception
     */
    @Test
    public void testLoadFileIntoString() throws Exception {
        System.out.println("loadFileIntoString");
        String filePath = "";
        String fileEncoding = "";
        String expResult = "";
        String result = FileReader.loadFileIntoString(filePath, fileEncoding);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
