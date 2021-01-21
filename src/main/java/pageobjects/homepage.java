package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homepage {
	WebDriver cd;
	
	public homepage(WebDriver d)	{
		cd = d;
		PageFactory.initElements(cd,this);
	}
	
	//Homepage header
	@FindBy(xpath="//div[@class=\"container text-center\"]")
	private WebElement headertxt;
	
	//Login link
	@FindBy(xpath="//ul[@class=\"top-nav\"]/li[2]/a")
	private WebElement login;
	
	public WebElement getheader()	{
		return headertxt;
	}
	
	public WebElement getlogin()	{
		return login;
	}
}