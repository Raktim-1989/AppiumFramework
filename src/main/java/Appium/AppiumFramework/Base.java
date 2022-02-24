package Appium.AppiumFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Base {
	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;
	     public AppiumDriverLocalService startServer()
	     {
	    	 boolean flag = checkIfServerIsRunning(4723);
	    	 System.out.println("flag is set to " + flag);
	    	 if(!flag)
	    	 {
	    		 service = AppiumDriverLocalService.buildDefaultService();
		    	 service.start();
		    	 System.out.println("service started");
	    		 
	    	 }
	    	
	    	 return service;
	    	
	     }

	public static AndroidDriver<AndroidElement> capabilities(String appName) throws IOException, InterruptedException
	{
		
		String userDir = System.getProperty("user.dir");
		
		FileInputStream fis = new FileInputStream(userDir + "\\src\\main\\java\\Appium\\AppiumFramework\\Global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String appValue = (String) prop.get(appName);
		String deviceName = System.getProperty("devicename"); //sending global properties from mvn command "mvn test -Ddevicename=Raktimemulator" into test
		//String deviceName = (String)prop.get("device");
		
		if(deviceName.contains("emulator"))
		{
			startEmulator();
			uninstallAppiumServerLog();
			System.out.println("emulator started ....");
		}
		
		
		File app = new File(new File("src"), appValue);
		System.out.println(app.getAbsolutePath());
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		
		//UI AUTOMATOR -> For automating Android apps
		//UIAutomator2 -> This is the core engine which utilizes to automate android app
		
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3000);
		System.out.println("raktim");
		driver = new AndroidDriver<AndroidElement>(new URL("http://localhost:4723/wd/hub"),cap);
		System.out.println("emulator is not passing ....");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;

	}

	public static boolean checkIfServerIsRunning(int port)
	{
		
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			// If control comes here, then it means that the port is in use
			isServerRunning = true;
			
		}
		finally
		{
			serverSocket = null;
		}
		
		return isServerRunning;
		
	}
	
	public static void startEmulator() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\startEmulator.bat");
		Thread.sleep(10000);
	}
	
	public static void uninstallAppiumServerLog() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\UninstallAppium.bat");
		Thread.sleep(10000);
	}
	
	public static void getScreenshot(String s) throws IOException
	{
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "\\Defects\\"+s+".png"));
		
	}
}
