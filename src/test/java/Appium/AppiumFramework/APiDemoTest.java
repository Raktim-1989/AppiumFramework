package Appium.AppiumFramework;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.HomePage;

public class APiDemoTest extends Base {

	   @Test(dataProvider = "InputData" , dataProviderClass=TestData.class )
	   public void apiDemo(String input) throws IOException, InterruptedException
	   {
		   
		  service =  startServer();

		AndroidDriver<AndroidElement> driver = capabilities("apiDemo");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		HomePage homePage = new HomePage(driver);
		homePage.preferences.click();
		//driver.findElement(By.xpath("//android.widget.TextView[@text= 'Preference']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text= '3. Preference dependencies']")).click();
		driver.findElement(By.id("android:id/checkbox")).click();
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		driver.findElement(By.className("android.widget.EditText")).sendKeys(input);
		driver.findElements(By.className("android.widget.Button"));    //returning multiple buttons having same class
		driver.findElements(By.className("android.widget.Button")).get(1).click();
		
		service.stop();
		
	   }
	   
	   
		
	}
