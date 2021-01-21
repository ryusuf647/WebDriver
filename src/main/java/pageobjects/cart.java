package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class cart {
	WebDriver cd;
	
	public cart(WebDriver d)	{
		cd = d;
		PageFactory.initElements(cd,this);
	}
	
	//Login page header
	@FindBy(xpath="//div[@class=\"header-lined\"]/h1")
	private WebElement headertxt;
	
	//products
	@FindBy(xpath="//div[@class=\"view-cart-items\"]")
	private WebElement cart;
	
	//total
	@FindBy(id="totalDueToday")
	private WebElement ordertotal;
	
	public WebElement getheader()	{
		return headertxt;
	}
	
	public WebElement getcart()	{
		return cart;
	}
	
	public WebElement gettotal()	{
		return ordertotal;
	}
}