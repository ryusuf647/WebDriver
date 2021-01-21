package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginpage {
	WebDriver cd;
	
	public loginpage(WebDriver d)	{
		cd = d;
		PageFactory.initElements(cd,this);
	}
	
	//Login page header
	@FindBy(xpath="//div[@class=\"header-lined\"]/h1")
	private WebElement headertxt;
	
	//Email field
	@FindBy(id="inputEmail")
	private WebElement email;
	
	//Password field
	@FindBy(id="inputPassword")
	private WebElement password;
	
	//Login button
	@FindBy(id="login")
	private WebElement loginbtn;
	
	//Logout button
	@FindBy(xpath="//li[@class=\"primary-action\"]/a")
	private WebElement logoutbtn;
	
	//Logout message
	@FindBy(xpath="//div[@class=\"alert alert-success text-center\"]")
	private WebElement logoutmsg;
	
	//Validation message
	@FindBy(xpath="//div[@class=\"alert alert-danger text-center\"]")
	private WebElement msg;
	
	//getters
	public WebElement getheader()	{
		return headertxt;
	}
	
	public WebElement getemail()	{
		return email;
	}
	
	public WebElement getpassword()	{
		return password;
	}
	
	public WebElement getloginbtn()	{
		return loginbtn;
	}
	
	public WebElement getlogoutbtn()	{
		return logoutbtn;
	}
	
	public WebElement getlogoutmsg()	{
		return logoutmsg;
	}
	
	public WebElement getmsg()	{
		return msg;
	}
}