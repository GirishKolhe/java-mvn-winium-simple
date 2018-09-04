package winium.flight_winium;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class test01 {
	WiniumDriver driver = null;
	WebElement loginDialog;
	WebElement landingPage;
	
	@BeforeClass
	public void setup() throws MalformedURLException, InterruptedException {
		String appPath = "C:/drive/apps/flight-app/flight4b.exe";
		DesktopOptions option = new DesktopOptions();
		System.setProperty("webdriver.winium.driver.desktop", appPath);
		option.setApplicationPath(appPath);
		option.setDebugConnectToRunningApp(false);
		//option.setLaunchDelay(2);
		driver = new WiniumDriver(new URL("http://localhost:9999"), option);	
		Thread.sleep(2000);
	}
	
	@Test
	public void test01_launch(){
		loginDialog = driver.findElementByName("Login");
		assertEquals(true, loginDialog.isDisplayed());
	}
	
	@Test
	public void test02_login() throws InterruptedException {		
		
		loginDialog.findElement(By.xpath("//*[@AutomationId='3001']")).sendKeys("admin");
		loginDialog.findElement(By.xpath("//*[@AutomationId='2000']")).sendKeys("mercury");
		loginDialog.findElement(By.name("OK")).click();

		Thread.sleep(2000);
		landingPage = driver.findElementByName("Flight Reservation");
		assertTrue(landingPage.isDisplayed());
	}
	
	@Test
	public void test03_logout(){
		landingPage.findElement(By.name("File")).click();
		landingPage.findElement(By.name("Exit")).click();
		assertFalse(landingPage.isDisplayed());
	}

	@AfterClass
	public void cleanUp(){
		if (landingPage.isDisplayed()) {
			driver.close();
		}
	}
}
