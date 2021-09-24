package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "register")
	WebElement register;
	@FindBy(id = "user_picture")
	WebElement userpict;
	@FindBy(id = "logout")
	WebElement keluar;
	
	public WebElement Register() {
		return register;
	}

	public WebElement Userpict() {
		return userpict;
	}

	public WebElement Keluar() {
		return keluar;
	}

}
