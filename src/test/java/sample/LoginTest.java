package sample;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;

public class LoginTest extends Base{

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
	public void LoginAlert() throws InterruptedException, IOException {
		
		LoginPage rl = new LoginPage(driver);

		System.out.println("Login Page");

		System.out.println("Case alert login");

		rl.Submit().click();

		String alEmail = rl.AlertEmail().getText();
		Assert.assertEquals(alEmail, "Field Email wajib diisi");
		System.out.println(
				"Alert email is enable " + rl.AlertEmail().isEnabled());

		String alPassword = rl.AlertPassword().getText();
		Assert.assertEquals(alPassword, "Field Password wajib diisi");
		System.out.println(
				"Alert password is enable " + rl.AlertPassword().isEnabled());

		rl.Email().sendKeys("a1!");
		rl.Password().sendKeys("a1!");
		rl.Submit().click();

		String alEmailPassword = rl.AlertEmailPassword().getText();
		Assert.assertEquals(alEmailPassword, "Mohon cek email dan password Anda kembali.");
		System.out.println("Alert wrong email and password is enable "
				+ rl.AlertEmailPassword().isEnabled());

	}
	
	@Test(groups = "Sample")
	public void LoginAllUrl() throws MalformedURLException, IOException, InterruptedException {

		System.out.println("Case check every link in page is active");

		List<WebElement> links = driver.findElements(By.tagName("a"));

		System.out.println("Total links in register page is " + links.size());

		SoftAssert a = new SoftAssert();

		for (WebElement link : links) {
			String url = link.getAttribute("href");
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("HEAD");
			conn.connect();
			int respCode = conn.getResponseCode();
			System.out.println(respCode);

			a.assertTrue(respCode < 400, "The link with text " + link.getText() + " is broken with code " + respCode);
		}

		a.assertAll();

		for (int i = 1; i < links.size(); i++) {

			String clickonlinktab = Keys.chord(Keys.CONTROL, Keys.ENTER);

			links.get(i).sendKeys(clickonlinktab);

			Thread.sleep(5000);
		}

		Set<String> tab = driver.getWindowHandles();
		Iterator<String> ittab = tab.iterator();

		while (ittab.hasNext()) {

			driver.switchTo().window(ittab.next());

			System.out.println(driver.getTitle());
		}

	}

	@SuppressWarnings("deprecation")
	@Test(groups = "Sample")
	public void SuccessLogin() throws InterruptedException, IOException {

		LoginPage rl = new LoginPage(driver);
		HomePage rh = new HomePage(driver);

		WebDriverWait w = new WebDriverWait(driver, 100);

		String email = "sample@mailinator.com";
		String pass = "123456";

		System.out.println("Case success login");

		driver.navigate().refresh();
		rl.Email().sendKeys(email);
		rl.Password().sendKeys(pass);
		rl.Submit().click();

		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_picture")));

		rh.Userpict().click();
		String keluar = rh.Keluar().getText();
		Assert.assertEquals(keluar, "Keluar");

	}
	
}