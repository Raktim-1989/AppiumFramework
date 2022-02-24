package Appium.AppiumFramework;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.CheckOutPage;
import pageObjects.FormPage;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

import java.io.IOException;

public class GeneralStoreAppTest extends Base {

	/*
	 * This Test Case defines the following Fill up the form details Shop the item
	 * in the app by scrolling to specific product and add To Cart Validate if items
	 * selected in Page 2 are matching with items displayed in check out page
	 * Validate the total Amount displayed in the checkout page matches with sum of
	 * product amounts selected for shopping Validate mobile gestures working for
	 * Links (long Press) and navigate to WebView
	 */

	@BeforeTest
	public void killAllNodes() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
	}
	
	@Test
	public void totalValidation() throws IOException, InterruptedException {
		
		service = startServer();
		AndroidDriver<AndroidElement> driver = capabilities("GeneralStoreApp");
		System.out.println("step is causing issue");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		FormPage formPage = new FormPage(driver);
		formPage.getNameField().sendKeys("Hello");
		driver.hideKeyboard();
		formPage.femaleOption.click();
		// dropDown handling
		formPage.countrySelection.click();
		// scroll till a particular element inside dropDown
		
		Utilities utils = new Utilities(driver);
		utils.scrollToText("Argentina");

		driver.findElement(By.xpath("//*[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

		// clicking 'add To Cart' button for the first 2 products
		//create add to cart page in POM and fix the following code 
		driver.findElements(By.xpath("//*[@text= 'ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//*[@text= 'ADD TO CART']")).get(0).click(); 
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

		Thread.sleep(5000);
		// Validating the total amount
		
		CheckOutPage checkoutPage = new CheckOutPage(driver);
		
		double sumOfProducts = 0;
		int count = checkoutPage.getProductList().size();
		for (int i = 0; i < count; i++) {
			String amount = checkoutPage.getProductList().get(i)
					.getText();
			double amountValue = Utilities.getAmount(amount);
			sumOfProducts = sumOfProducts + amountValue;

		}
		System.out.println("sum of products ...." + sumOfProducts);

		String total = checkoutPage.totalAmount.getText();
		double totalValue = Utilities.getAmount(total);
		System.out.println("total Value is ....." + totalValue);
		Assert.assertEquals(sumOfProducts, totalValue);
		
		service.stop();
		
		

	}

}
