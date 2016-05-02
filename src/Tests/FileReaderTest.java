package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import code.FileReader;
import code.Instance;

/**
 * @author benlarking
 *
 */
public class FileReaderTest {

	private String realFileName;
	private String testFileName;
	private FileReader fr;
	
	 @Rule public ExpectedException exception = ExpectedException.none();
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		realFileName = "src/VoteData.txt";
		testFileName = "src/VoteDataTest.txt";
		fr = new FileReader();
	}

	/**
	 * Test method for readFile. Test method will ensure the correct file exists.
	 * @throws IOException 
	 */
	@Test
	public void testReadFile() throws IOException {
		File shouldExist = new File(realFileName);
	    assertTrue(shouldExist.exists());
	    
	    fr.readFile(realFileName);
	    int totalInstancesReadIn = fr.getTestInput().size() + fr.getTrainingInput().size();
	    int totalInstancesExpected = 435;
	    assertEquals(totalInstancesReadIn, totalInstancesExpected);
	}
	
	/**
	 * Test method for readFile. Test method will ensure the correct file exists.
	 * @throws IOException 
	 */
	@Test
	public void testReadFileValues() throws IOException{
		exception.expect(IOException.class);
		fr.readFile(testFileName);	
	}
		
	/**
	 * Test method for readFile. Test method will ensure the correct file exists.
	 * @throws IOException 
	 */
	@Test
	public void testReadFileQuantity() throws IOException{
		fr.readFile(realFileName);	
		
		int expectedDemocrats = 267;
		int expectedRepublicans = 168;
		int democratCounter = 0;
		int republicanCounter = 0;
		
		for (Instance instance : fr.getTrainingInput()){
			if (instance.getClassification() == 2) democratCounter++;
			else if (instance.getClassification() == 1) republicanCounter++;
		}
		for (Instance instance : fr.getTestInput()){
			if (instance.getClassification() == 2) democratCounter++;
			else if (instance.getClassification() == 1) republicanCounter++;
		}
		
		assertEquals(democratCounter, expectedDemocrats);
		assertEquals(republicanCounter, expectedRepublicans);	
	}
}
