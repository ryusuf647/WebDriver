package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class clientarea {
	WebDriver cd;
	
	public clientarea(WebDriver d)	{
		cd = d;
		PageFactory.initElements(cd,this);
	}
	
	//name
	@FindBy(xpath="//div[@class=\"panel-body\"]/strong")
	private WebElement name;
	
	//header
	@FindBy(xpath="//div[@class=\"header-lined\"]/h1")
	private WebElement header;
	
	//titles
	@FindBy(xpath="//div[@class=\"title\"]")
	private List<WebElement> titles;
	
	//Services menu item
	@FindBy(id="Primary_Navbar-Services")
	private WebElement services;
	
	public WebElement getname()	{
		return name;
	}
	
	public WebElement getheader()	{
		return header;
	}
	
	public List<WebElement> gettitles() {
		return titles;
	}
	
	public WebElement getservices()	{
		return services;
	}
}