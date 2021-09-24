package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;

	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
		
		@FindBy(name="name") 
		WebElement name;
		@FindBy(css=".text-danger.error.error-name") 
		WebElement namealert;
		@FindBy(id="email1") 
		WebElement email;
		@FindBy(css=".text-danger.error.error-email") 
		WebElement emailalert;
		@FindBy(id="email2") 
		WebElement emailconfirm;
		@FindBy(css=".text-danger.error.error-confirm_email") 
		WebElement emailconfirmalert;	
		@FindBy(id="alert-paste-email") 
		WebElement emailconfirmpastealert;
		@FindBy(id="phone_number") 
		WebElement phone;
		@FindBy(css=".text-danger.error.error-password") 
		WebElement phonealert;
		@FindBy(id="password") 
		WebElement password;
		@FindBy(id="strength_status") 
		WebElement passwordstrength;
		@FindBy(css=".text-danger.error.error-password") 
		WebElement passwordalert;
		@FindBy(id="password2") 
		WebElement passwordconfirm;
		@FindBy(css=".text-danger.error.error-confirm_password") 
		WebElement passwordconfirmalert;
		@FindBy(id="alert-paste-password") 
		WebElement passwordconfirmpastealert;
		@FindBy(id="agree") 
		WebElement useragreement;
		@FindBy(id="submit_button") 
		WebElement submit;
		@FindBy(id="continue-register") 
		WebElement submitverificationemail;
		@FindBy(id="verified-email") 
		WebElement verifiedemail;
		
		
		public WebElement Name()
		{
			return name;
		}
		public WebElement NameAlert()
		{
			return namealert;
		}
		public WebElement Email()
		{
			return email;
		}
		public WebElement EmailAlert()
		{
			return emailalert;
		}
		public WebElement EmailConfirm()
		{
			return emailconfirm;
		}
		public WebElement EmailConfirmAlert()
		{
			return emailconfirmalert;
		}
		public WebElement EmailConfirmPasteAlert()
		{
			return emailconfirmpastealert;
		}
		public WebElement Phone()
		{
			return phone;
		}
		public WebElement PhoneAlert()
		{
			return phonealert;
		}
		public WebElement Password()
		{
			return password;
		}
		public WebElement PasswordStrength()
		{
			return passwordstrength;
		}
		public WebElement PasswordAlert()
		{
			return passwordalert;
		}	
		public WebElement PasswordConfirm()
		{
			return passwordconfirm;
		}
		public WebElement PasswordConfirmAlert()
		{
			return passwordconfirmalert;
		}
		public WebElement PasswordConfirmPasteAlert()
		{
			return passwordconfirmpastealert;
		}
		public WebElement UserAgreement()
		{
			return useragreement;
		}
		public WebElement Submit()
		{
			return submit;
		}
		public WebElement SubmitVerificationEmail()
		{
			return submitverificationemail;
		}
		public WebElement VerifiedEmail()
		{
			return verifiedemail;
		}

}
