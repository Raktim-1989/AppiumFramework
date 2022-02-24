package Appium.AppiumFramework;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Utilities {

	AndroidDriver<AndroidElement> driver;
	public Utilities(AndroidDriver<AndroidElement> driver)
	{
		this.driver = driver;
	}
	
	 public static double getAmount(String value)
	   {
		   value = value.substring(1);
		   double amountValue = Double.parseDouble(value);
		   return amountValue;
	   }
	 
	 
	public void scrollToText(String text)
	{
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"))");
			
	}
}
