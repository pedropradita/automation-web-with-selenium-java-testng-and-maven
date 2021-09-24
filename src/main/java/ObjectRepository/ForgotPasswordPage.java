package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage {
	
WebDriver driver;

public ForgotPasswordPage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver,this);
}
	
	@FindBy(name="email") 
	WebElement email;
	@FindBy(css=".text-danger.error.error-email") 
	WebElement emailalert;
	@FindBy(className="recaptcha-checkbox-border") 
	WebElement recaptchacheckbox;
	@FindBy(css=".text-danger.error.error-g-recaptcha-response") 
	WebElement recaptchaalert;
		@FindBy(css=".btn.btn-lg.c-theme-btn.c-btn-square.c-btn-uppercase.c-btn-bold.btn-forgot-password.btn_1.rounded.background-pink.btn-below-recaptcha.margin-auto") 
	WebElement submit;

	
	public WebElement Email()
	{
		return email;
	}
	
	public WebElement EmailAlert()
	{
		return emailalert;
	}
	
	public WebElement RecaptchaCheckbox()
	{
		return recaptchacheckbox;
	}
	
	public WebElement RecaptchaAlert()
	{
		return recaptchaalert;
	}
	
	public WebElement Submit()
	{
		return submit;
	}

}
