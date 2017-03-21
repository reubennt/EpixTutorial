package Demo;

import java.util.Arrays;

import org.testng.annotations.*;
import org.testng.Assert;
import org.testng.Reporter;

public class reportersAndAssets {
	
		//This is a simple demonstration of Reporting and Assets

		@Test
		public void testStrings() {
			String actualString = "Test Reporters And Asserts";
			Reporter.log("The actual string is " + actualString, true);
			Assert.assertEquals(actualString, "Test Reporters And Asserts");
			Reporter.log("Verifying the actual string with expected value", true);
			Reporter.log("");
		}
		
		@Test
		public void testInt() {
			int actualInt = 10;
			Reporter.log("The actual integer is " + actualInt, true);
			Assert.assertEquals(actualInt, 10);
			Reporter.log("Verifying the actual integer with expected value", true);
			Reporter.log("");
		}
		
		@Test
		public void testArrays() {
			int[] actualArray = {1, 2, 3};
			int[] expectedArray = {1, 2, 3};
			Reporter.log("The actual array is " + Arrays.toString(actualArray) , true);
			Assert.assertEquals(actualArray, expectedArray);
			Reporter.log("Verifying the actual array with expected value", true);
			Reporter.log("");
		}

	}




