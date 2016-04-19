package tests;
import code.EntropyCalculator;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class EntropyCalcTest {

	@Before
	public void CreateClass() {
		EntropyCalculator ec = new EntropyCalculator();
	}
	
	@Test
	public void testReadFile() {
		File shouldExist = new File("src/LensData.txt");
	    assertTrue(shouldExist.exists());
	}

}
