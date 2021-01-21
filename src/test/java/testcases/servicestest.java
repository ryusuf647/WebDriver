package testcases;

import java.util.ArrayList;
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

import pageobjects.services;
import resources.baseclass;

public class servicestest extends baseclass {
	private services sv;
	private static Logger log = LogManager.getLogger(servicestest.class.getName());
	
	@BeforeClass
	private void init()	{
		sv = new services(cd);
		prodnames = new ArrayList<>();
		prodprices = new ArrayList<>();
	}
	
	@Test(priority=1)
	private void allservices()	{
		try	{
			if(sv.getheader().getText().equals(PROP.get("svheader")))	{
				log.trace("Arrived at all products");
			}
			else	{
				log.error("Incorrect header found");
			}
		}
		catch(NoSuchElementException e)	{
			log.warn("Header or page missing");
		}
		Assert.assertEquals(sv.getheader().getText(), PROP.get("svheader"));
	}
	
	@Test(priority=2)
	private void addproducts() throws InterruptedException	{
		List<String> prods = Stream.of((String)PROP.getProperty("p1"),(String)PROP.getProperty("p2")).collect(Collectors.toList());
		int count = 1;
		log.info("Attempting to add products to cart");
		for(int i=0;i<prods.size();i++)	{
			for(int j=0;j<sv.getproducts().size();j++)	{
				WebElement product = sv.getproducts().get(j).findElement(By.xpath(". //span"));
				if(product.getText().equals(prods.get(i)))	{
					Double price = Double.parseDouble(sv.getproducts().get(j).findElement(By.xpath(". //following-sibling::footer/div/span")).getText().split(" ")[0].substring(1));
					prodnames.add(product.getText());
					log.info("Added: "+product.getText());
					prodprices.add(price);
					sv.getproducts().get(j).findElement(By.xpath(". //following-sibling::footer/a")).click();
					sv.getsubmitbtn().click();
					if(count<prods.size())	{
						Thread.sleep(3000);
						sv.getallservices().click();
					}
					break;
				}
			}
			count++;
		}
		Thread.sleep(5000);
	}
}