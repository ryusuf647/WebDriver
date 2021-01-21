package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class services {
	WebDriver cd;
	
	public services(WebDriver d)	{
		cd = d;
		PageFactory.initElements(cd,this);
	}
	
	//Login page header
	@FindBy(xpath="//div[@class=\"header-lined\"]/h1")
	private WebElement headertxt;
	
	//products
	@FindBy(xpath="//div[@class=\"products\"]/div/div/div/header")
	private List<WebElement> products;
	
	//Continue button
	@FindBy(id="btnCompleteProductConfig")
	private WebElement continuebtn;
	
	@FindBy(xpath="//div[@menuitemname=\"Categories\"]/div[2]/a")
	private WebElement allservices;
	
	public WebElement getheader()	{
		return headertxt;
	}
	
	public List<WebElement> getproducts()	{
		return products;
	}
	
	public WebElement getsubmitbtn()	{
		return continuebtn;	
	}
	
	public WebElement getallservices()	{
		return allservices;
	}
}