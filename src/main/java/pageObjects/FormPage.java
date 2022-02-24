package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage {
	   
	public FormPage(AndroidDriver<AndroidElement> driver)
    {
   	 PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
	
	//com.androidsample.generalstore:id/nameField
	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	@AndroidFindBy(xpath= "//*[@text='Female']")
	  public WebElement femaleOption;	
	@AndroidFindBy(id= "android:id/text1")
	  public WebElement countrySelection;

	
	public WebElement getNameField()
	{
		System.out.println("Trying to find out Name Field ...");
		return nameField;
	}
	
	public WebElement getFemaleOption()
	{
		System.out.println("Trying to find out Female Field ...");
		return femaleOption;
	}

	public WebElement getCountrySelection()
	{
		System.out.println("Trying to find out country Field ...");
		return countrySelection;
	}


}
