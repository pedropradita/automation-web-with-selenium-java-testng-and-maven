package sample;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import ObjectRepository.RegisterPage;

public class RegisterTest extends Base{

	private static boolean done;

	@BeforeMethod
	public void BefMet() throws IOException, InterruptedException {
		
		driver=initializeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://sso.pintaria.id/daftar?domain=aHR0cHM6Ly9waW50YXJpYS5pZA==&previous_url=aHR0cHM6Ly9waW50YXJpYS5pZC8=");
		
	}

	@AfterMethod
	public void AfMet() {
		
		driver.close();
	}
	
	@Test(groups = "Pintaria")
	public void RegistAlertWithoutSubmit() throws InterruptedException, IOException {

		RegisterPage rr = new RegisterPage(driver);

		System.out.println("Register Page");
		System.out.println("Case alert register");

		rr.EmailConfirm().sendKeys(Keys.CONTROL + "v");
		String alConPasEmail = rr.EmailConfirmPasteAlert().getText();
		Assert.assertEquals(alConPasEmail, "Tidak dapat menyalin data");
		System.out.println("Alert confirm email is enable " + rr.EmailConfirmPasteAlert().isEnabled());

		rr.PasswordConfirm().sendKeys(Keys.CONTROL + "v");
		String alConPasPassword = rr.PasswordConfirmPasteAlert().getText();
		Assert.assertEquals(alConPasPassword, "Tidak dapat menyalin data");
		System.out.println("Alert confirm email is enable " + rr.PasswordConfirmPasteAlert().isEnabled());

		rr.Password().sendKeys("a");
		String strength1 = rr.PasswordStrength().getText();
		Assert.assertEquals(strength1, "Terlalu Pendek");
		System.out.println(strength1);

		rr.Password().sendKeys("aaaaaaa");
		String strength2 = rr.PasswordStrength().getText();
		Assert.assertEquals(strength2, "Lemah");
		System.out.println(strength2);

		rr.Password().sendKeys("1A");
		String strength3 = rr.PasswordStrength().getText();
		Assert.assertEquals(strength3, "Kuat");
		System.out.println(strength3);

	}

	@Test(groups = "Pintaria")
	public void RegistAlert() throws InterruptedException, IOException {

		// reCaptcha must disable

		RegisterPage rr = new RegisterPage(driver);

		System.out.println("Register Page");
		System.out.println("Case alert register");

		rr.EmailConfirm().sendKeys(Keys.CONTROL + "v");
		String alConPasEmail = rr.EmailConfirmPasteAlert().getText();
		Assert.assertEquals(alConPasEmail, "Tidak dapat menyalin data");
		System.out.println("Alert confirm email is enable " + rr.EmailConfirmPasteAlert().isEnabled());

		rr.PasswordConfirm().sendKeys(Keys.CONTROL + "v");
		String alConPasPassword = rr.PasswordConfirmPasteAlert().getText();
		Assert.assertEquals(alConPasPassword, "Tidak dapat menyalin data");
		System.out.println("Alert confirm email is enable " + rr.PasswordConfirmPasteAlert().isEnabled());

		rr.Password().sendKeys("a");
		String strength1 = rr.PasswordStrength().getText();
		Assert.assertEquals(strength1, "Terlalu Pendek");
		System.out.println(strength1);

		rr.Password().sendKeys("aaaaaaa");
		String strength2 = rr.PasswordStrength().getText();
		Assert.assertEquals(strength2, "Lemah");
		System.out.println(strength2);

		rr.Password().sendKeys("1A");
		String strength3 = rr.PasswordStrength().getText();
		Assert.assertEquals(strength3, "Kuat");
		System.out.println(strength3);

		driver.navigate().refresh();

		rr.UserAgreement().click();
		rr.Submit().click();

		String alName = rr.NameAlert().getText();
		Assert.assertEquals(alName, "Kolom nama wajib diisi.");
		System.out.println("Alert name is enable " + rr.NameAlert().isEnabled());

		String alEmail = rr.EmailAlert().getText();
		Assert.assertEquals(alEmail, "Kolom email wajib diisi.");
		System.out.println("Alert email is enable " + rr.EmailAlert().isEnabled());

		String alConEmail = rr.EmailConfirmAlert().getText();
		Assert.assertEquals(alConEmail, "Kolom Konfirmasi Email wajib diisi.");
		System.out.println("Alert confirm email is enable " + rr.EmailConfirmAlert().isEnabled());

		String alPass = rr.PasswordAlert().getText();
		Assert.assertEquals(alPass, "Kolom password wajib diisi.");
		System.out.println("Alert password is enable " + rr.PasswordAlert().isEnabled());

		String alConPass = rr.PasswordConfirmAlert().getText();
		Assert.assertEquals(alConPass, "Kolom konfirmasi password wajib diisi.");
		System.out.println("Alert confirm password is enable " + rr.PasswordConfirmAlert().isEnabled());

	}

	@Test(groups = "Pintaria")
	public void RegistDaftarButton() throws IOException {

		RegisterPage rr = new RegisterPage(driver);

		System.out.println("Case daftar button disable before checklist perjanjian pengguna");

		Assert.assertTrue(rr.Submit().isEnabled());
		System.out.println("Daftar button is disable " + rr.Submit().isEnabled());

		rr.UserAgreement().click();
		Assert.assertTrue(rr.Submit().isDisplayed());
		System.out.println("Daftar button is enable after click perjanjian pengguna " + rr.Submit().isDisplayed());
		
	}

	@Test(groups = "Pintaria")
	public void RegistAllUrl() throws MalformedURLException, IOException, InterruptedException {

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
	@Test(groups = "Pintaria")
	public void SuccessRegist() throws InterruptedException, IOException {

		// reCaptcha must disable

		RegisterPage rr = new RegisterPage(driver);

		WebDriverWait w = new WebDriverWait(driver, 100);

		Date thisDate = new Date();
		SimpleDateFormat dateForm = new SimpleDateFormat("ddMMYYHHmmss");
		String dtmNow = dateForm.format(thisDate);

		String user = "Automation User " + dtmNow;
		String email = "autouserpin" + dtmNow + "@mailinator.com";
		String pass = "Haruka123!";

		System.out.println("Case success register");
		while (!done) {

			driver.navigate().refresh();
			rr.Name().sendKeys(user);
			rr.Email().sendKeys(email);
			rr.EmailConfirm().sendKeys(email);
			rr.Phone().sendKeys("082123456789");
			rr.Password().sendKeys(pass);
			rr.PasswordConfirm().sendKeys(pass);
			rr.UserAgreement().click();
			rr.Submit().click();

			JavascriptExecutor js = (JavascriptExecutor) driver;

			Thread.sleep(1000);

			if (rr.SubmitVerificationEmail().isDisplayed()) {

				WebElement VerifiedEmail = rr.VerifiedEmail();
				WebElement register = rr.SubmitVerificationEmail();

				js.executeScript("document.getElementById('verified-email')");

				boolean a = VerifiedEmail.isDisplayed();

				String verifiedemail = VerifiedEmail.getText();

				if (a) {
					Assert.assertEquals(verifiedemail, email);
					System.out.println("Verified Email is " + verifiedemail);
				} else {
					System.out.println("Email is wrong");
				}

				js.executeScript("arguments[0].click()", register);

				done = true;
			}

		}

		// class="text-center font-dark-blue mb-5"class="text-center font-dark-blue
		// mb-5"

		w.until(ExpectedConditions.visibilityOfElementLocated(By.className("text-center.font-dark-blue.mb-5")));

		String emailVer = driver.findElement(By.className("text-center.font-dark-blue.mb-5")).getText().split("ke")[1]
				.split("com")[0].trim();

		Assert.assertEquals(emailVer, email);

	}

}
