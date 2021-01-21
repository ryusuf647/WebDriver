package testcases;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageobjects.clientarea;
import resources.baseclass;

public class clientareatest extends baseclass {
	private clientarea ca;
	private static Logger log = LogManager.getLogger(clientareatest.class.getName());
	
	@BeforeClass
	private void init()	{
		ca = new clientarea(cd);				
	}
	
	@Test(priority=1)
	private void clientarea()	{
		String name="";
		name=ca.getname().getText().split(" ")[0].trim();
		try	{
			if(ca.getheader().getText().equals(PROP.get("camsg")+" "+name))	{
				log.trace("Successfully logged in");
			}
			else	{
				log.error("Incorrect header found");
			}
		}
		catch(NoSuchElementException e)	{
			log.warn("Missing header or login unsuccessful");
		}
		Assert.assertEquals(ca.getheader().getText(), PROP.get("camsg")+" "+name);
	}
	
	@Test(priority=2)
	private void titlestest()	{
		List<String> expnames = Stream.of((String)PROP.get("t1"),(String)PROP.get("t2"),(String)PROP.get("t3"),(String)PROP.get("t4")).map(n->n.toUpperCase()).collect(Collectors.toList());
		List<String> actnames = ca.gettitles().stream().map(n->n.getText()).collect(Collectors.toList());
		List<Integer> expstats = Stream.of((String)PROP.get("s1"),(String)PROP.get("s2"),(String)PROP.get("s3"),(String)PROP.get("s4")).map(n->Integer.parseInt(n)).collect(Collectors.toList());
		List<Integer> actstats = ca.gettitles().stream().map(n->Integer.parseInt(n.findElement(By.xpath(". //preceding-sibling::div[@class=\"stat\"]")).getText())).collect(Collectors.toList());
		//Confirm number of nodes is correct
		try	{
			//if(actnames.size() == expnames.size())	{
			if(actnames.size() == 3)	{
				log.info("Confirmed correct number of nodes displayed");
			}
			else	{
				log.error("Incorrect number of nodes displayed");
			}
		}
		catch(NoSuchElementException e)	{
			log.warn("Check for missing nodes");
		}
		Assert.assertEquals(actnames.size(),3);
		//Confirm all titles displayed are correct
		try	{
			if(actnames.equals(expnames))	{
				log.info("All node titles are correct");
			}
			else	{
				log.error("One or more titles may be incorrect");
			}
		}
		catch(NoSuchElementException e)	{
			log.warn("Check for missing titles");
		}
		Assert.assertEquals(actnames,expnames);
		//Confirm all stats displayed are correct
		try	{
			if(actstats.equals(expstats))	{
				log.info("All node stats are correct");
			}
		}
		catch(NoSuchElementException e)	{
			log.warn("Check for missing node stats");
		}
		Assert.assertEquals(actstats,expstats);
	}
	
	@Test(priority=3)
	private void servicesnavtest()	{
		WebElement orderservices = ca.getservices().findElement(By.xpath(". //ul/li[4]"));
		ca.getservices().click();
		log.info("Trying to click services menu item");
		try	{
			if(orderservices.isDisplayed())	{
				log.info("Click successful");
			}
		}
		catch(NoSuchElementException e)	{
			log.warn("Unable to locate menu item");
		}
		Assert.assertTrue(orderservices.isDisplayed());
		log.info("Trying to click menu item");
		orderservices.click();		
	}
}