package testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pageobjects.homepage;
import resources.baseclass;

public class homepagetest extends baseclass {
	private static Logger log = LogManager.getLogger(homepagetest.class.getName());
	private homepage hp;

	@BeforeSuite
	private void initMethod()	{
		cd = initializeDriver();
		hp = new homepage(cd);
	}
	
	@Test(priority=1)
	private void homepage()	{
		cd.get(PROP.getProperty("homepageurl"));

		try	{
			if(hp.getheader().getText().equals(PROP.getProperty("headertxt")))	{
				log.trace("Arrived at home page");
			}
			else	{
				log.error("Incorrect header found");
			}
		}
		catch(NoSuchElementException e)	{
			log.warn("Header or home page missing");
		}
		Assert.assertEquals(hp.getheader().getText(), PROP.getProperty("headertxt"));
	}
	
	@Test(priority=2)
	private void login()	{
		try	{
			if(hp.getlogin().isDisplayed())	{
				log.info("Login link is displayed");
			}
		}
		catch(NoSuchElementException e)	{
			log.warn("Cannot locate login link");
		}
		Assert.assertTrue(hp.getlogin().isDisplayed());
	}
	
	@Test(priority=3)
	private void login_nav()	{
		log.info("Trying to click login link");
		hp.getlogin().click();
	}
}