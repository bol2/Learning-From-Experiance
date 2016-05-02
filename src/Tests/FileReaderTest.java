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
 * CS39440 Major Project: Learning From Experience FileReaderTest.java Purpose:
 * A class to test the functionality of the methods belonging to FileReader.java
 * 
 * @author Ben Larking
 * @version 2.0 29/04/16
 */
public class FileReaderTest {

	private String realFileName;
	private String testFileName;
	private FileReader fileReader;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	/**
	 * Define two files, one contains the real data, another contains data
	 * designed to cause an exception to be thrown.
	 */
	@Before
	public void setUp() {
		realFileName = "src/VoteData.txt";
		testFileName = "src/VoteDataTest.txt";
		fileReader = new FileReader();
	}

	/**
	 * Test that the correct file exists and that when it is read in, all 435
	 * instances are read.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testReadFile() throws IOException {
		File shouldExist = new File(realFileName);
		assertTrue(shouldExist.exists());

		fileReader.readFile(realFileName);
		int totalInstancesReadIn = fileReader.getTestInput().size() + fileReader.getTrainingInput().size();
		int totalInstancesExpected = 435;
		assertEquals(totalInstancesReadIn, totalInstancesExpected);
	}

	/**
	 * Test that when the wrong file is read with an input format that is not
	 * expected an exception is thrown.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testReadFileValues() throws IOException {
		exception.expect(IOException.class);
		fileReader.readFile(testFileName);
	}

	/**
	 * Test that the correct amount of democrat and republicans are read in from
	 * the real data file.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testReadFileQuantity() throws IOException {
		fileReader.readFile(realFileName);

		int expectedDemocrats = 267;
		int expectedRepublicans = 168;
		int democratCounter = 0;
		int republicanCounter = 0;

		for (Instance instance : fileReader.getTrainingInput()) {
			if (instance.getClassification() == 2)
				democratCounter++;
			else if (instance.getClassification() == 1)
				republicanCounter++;
		}
		for (Instance instance : fileReader.getTestInput()) {
			if (instance.getClassification() == 2)
				democratCounter++;
			else if (instance.getClassification() == 1)
				republicanCounter++;
		}

		assertEquals(democratCounter, expectedDemocrats);
		assertEquals(republicanCounter, expectedRepublicans);
	}
}
