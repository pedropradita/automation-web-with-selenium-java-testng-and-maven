package sample;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import ObjectRepository.ForgotPasswordPage;
import ObjectRepository.LoginPage;

public class ForgotPasswordTest extends Base{

	@BeforeMethod
	public void BeforeReg() throws IOException {
		driver=initializeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://sso.sample.id/");
	}
	
	@AfterMethod
	public void AfMet() {
		driver.close();
	}

	@Test(groups = "Sample")
	public void ForgotPasswordAlert() throws InterruptedException, IOException {
		
		// reCaptcha must disable
		
		LoginPage rl = new LoginPage(driver);
		ForgotPasswordPage rf = new ForgotPasswordPage(driver);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('recaptcha-anchor')");

		System.out.println("Forgot Password Page");
		System.out.println("Case alert forgot password");
		
		rl.LupaPassword().click();
		rf.Submit().click();

		String alEmail = rf.EmailAlert().getText();
		Assert.assertEquals(alEmail, "* Kolom email harus diisi.");
		System.out.println(
				"Alert email is enable " + rf.EmailAlert().isEnabled());

		String alRecaptcha = rf.RecaptchaAlert().getText();
		Assert.assertEquals(alRecaptcha, "* Harap mencentang captcha.");
		System.out.println(
				"Alert recaptcha is enable " + rf.RecaptchaAlert().isEnabled());
		
		rf.Email().sendKeys("a@a.com");
		rf.Submit().click();
		
		String alEmailNotFound = rf.EmailAlert().getText();
		Assert.assertEquals(alEmailNotFound, "* Email yang anda masukkan tidak terdaftar");
		System.out.println(
				"Alert email not found is enable " + rf.EmailAlert().isEnabled());

	}
	
	@Test(groups = "Sample")
	public void ForgotPasswordAllUrl() throws MalformedURLException, IOException, InterruptedException {
		
		LoginPage rl = new LoginPage(driver);

		System.out.println("Case check every link in page is active");
		
		rl.LupaPassword().click();

		List<WebElement> links = driver.findElements(By.cssSelector("a[href*='http']"));

		System.out.println("Total links in register page is " + links.size());

		SoftAssert a = new SoftAssert();

		for (WebElement link : links) {
			String url = link.getAttribute("href");
			
			if ((url != null) && !url.startsWith("javascript")) {
				
				
				HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
				conn.setRequestMethod("HEAD");
				conn.connect();
				int respCode = conn.getResponseCode();
				System.out.println(respCode);

				a.assertTrue(respCode < 400, "The link with text " + link.getText() + " is broken with code " + respCode);
				
			}
			
		
		}

		a.assertAll(); 

		for (int i = 1; i < links.size(); i++) {
			
			String clickonlinktab = Keys.chord(Keys.CONTROL, Keys.ENTER);
			
			if(links.get(i).isDisplayed()) {

			links.get(i).sendKeys(clickonlinktab);
			
			Thread.sleep(5000);

			}else {
			
				System.out.println("The link is not displayed " +links.get(i).getText());
			}
			}


		Set<String> tab = driver.getWindowHandles();
		Iterator<String> ittab = tab.iterator();

		while (ittab.hasNext()) {

			driver.switchTo().window(ittab.next());

			System.out.println(driver.getTitle());
		}

	}
	
	@Test(groups = "Sample")
	public void SuccessForgotPassword(){
		
		// reCaptcha must disable

		LoginPage rl = new LoginPage(driver);
		ForgotPasswordPage rf = new ForgotPasswordPage(driver);

		String email = "sample@mailinator.com";

		System.out.println("Case forgot password");
		
		rl.LupaPassword().click();
		rf.Email().sendKeys(email);
		rf.Submit().click();
	}
}