package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
WebDriver driver;

public LoginPage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver,this);
}
	
	@FindBy(name="email") 
	WebElement email;
	@FindBy(xpath="//*[@class='text-danger'][1]") 
	WebElement alertemail;
	@FindBy(id="password") 
	WebElement password;
	@FindBy(xpath="//*[@class='text-danger'][2]") 
	WebElement alertpassword;
	@FindBy(className="swal-text") 
	WebElement alertemailpassword;
	@FindBy(css=".btn-submit.add_top_30") 
	WebElement submit;
	@FindBy(linkText="Lupa password?") 
	WebElement lupapassword;
	
	public WebElement Email()
	{
		return email;
	}
	
	public WebElement AlertEmail()
	{
		return alertemail;
	}
	
	public WebElement Password()
	{
		return password;
	}
	
	public WebElement AlertPassword()
	{
		return alertpassword;
	}
	
	public WebElement AlertEmailPassword()
	{
		return alertemailpassword;
	}
	
	public WebElement Submit()
	{
		return submit;
	}
	
	public WebElement LupaPassword()
	{
		return lupapassword;
	}

}
