package Appium.AppiumFramework;

import org.testng.annotations.DataProvider;

public class TestData {
	
	 @DataProvider(name = "InputData")
	   public Object[][] getDataForEditField()
	   {
		   //2 sets of data , "hello" text , "!@#$"
		   Object[][] obj = new Object[][]
		   {
			   {"hello"} ,
			   {"!@#$"}
		   };
		   
		   return obj;
		   
	   }

}
