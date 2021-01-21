package stepDefinitions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.cart;
import pageobjects.clientarea;
import pageobjects.homepage;
import pageobjects.loginpage;
import pageobjects.services;
import resources.baseclass;

public class stepDefinitions extends baseclass {
	
    @Given("^My chrome driver is initialized$")
    public void my_chrome_driver_is_initialized() throws Throwable {
    	cd = initializeDriver();
    }
    
    @When("^I navigate to \"([^\"]*)\"$")
    public void i_navigate_to_something(String arg) throws Throwable {
    	cd.get(arg);
    }

    @Then("^I am able to see the header text when the page is loaded$")
    public void i_am_able_to_see_the_header_text_when_the_page_is_loaded() throws Throwable {
    	homepage hp = new homepage(cd);
    	Assert.assertEquals(hp.getheader().getText(), PROP.getProperty("headertxt"));
    }
    
    @And("^The login link is displayed$")
    public void the_login_link_is_displayed() throws Throwable {
    	homepage hp = new homepage(cd);
    	Assert.assertTrue(hp.getlogin().isDisplayed());
    }
    
    @Then("^the sign in page is displayed with the header \"([^\"]*)\"$")
    public void the_sign_in_page_is_displayed_with_the_header_something(String arg) throws Throwable {
    	loginpage lp = new loginpage(cd);
    	Assert.assertEquals(lp.getheader().getText(), arg);
    }

    @Then("^I am able to confirm that logout button is displayed if login is successful$")
    public void i_am_able_to_confirm_that_logout_button_is_displayed_if_login_is_successful() throws Throwable {
		//flag to check validation message present
		boolean valmsg = false;
		loginpage lp = new loginpage(cd);
		
		try	{
			valmsg = lp.getmsg().isDisplayed();
		}
		catch(Exception e)	{
			//login successful
			Assert.assertTrue(lp.getlogoutbtn().isDisplayed());
			lp.getlogoutbtn().click();
		}
		
		if (valmsg)	{
			Assert.assertEquals(lp.getmsg().getText(), PROP.getProperty("msg"));
		}
		homepage hp = new homepage(cd);
		hp.getlogin().click();
    }

    @When("^I click the login link$")
    public void i_click_the_login_link() throws Throwable {
    	homepage hp = new homepage(cd);
    	hp.getlogin().click();
    }

    @When("^I enter (.+) and (.+) for the fields and click log in$")
    public void i_enter_and_for_the_fields_and_click_log_in(String username, String password) throws Throwable {
    	loginpage lp = new loginpage(cd);
    	lp.getemail().sendKeys(username);
		lp.getpassword().sendKeys(password);
		lp.getloginbtn().click();
    }
    
    @When("^I enter \"([^\"]*)\" and \"([^\"]*)\" on the login page and click login$")
    public void i_enter_something_and_something_on_the_login_page_and_click_login(String arg1, String arg2) throws Throwable {
    	loginpage lp = new loginpage(cd);
    	lp.getemail().sendKeys(arg1);
		lp.getpassword().sendKeys(arg2);
		lp.getloginbtn().click();
    }
    
    @Then("^I am able to log in successfully and see the welcome message with my name$")
    public void i_am_able_to_log_in_successfully_and_see_the_welcome_message_with_my_name() throws Throwable {
    	clientarea ca = new clientarea(cd);
    	String name="";
		name=ca.getname().getText().split(" ")[0].trim();
		Assert.assertEquals(ca.getheader().getText(), PROP.get("camsg")+" "+name);
    }

    @And("^I am able to confirm the following are correct: no. of nodes, labels, and stats$")
    public void i_am_able_to_confirm_the_following_are_correct_no_of_nodes_labels_and_stats() throws Throwable {
    	clientarea ca = new clientarea(cd);
    	List<String> expnames = Stream.of((String)PROP.get("t1"),(String)PROP.get("t2"),(String)PROP.get("t3"),(String)PROP.get("t4")).map(n->n.toUpperCase()).collect(Collectors.toList());
		List<String> actnames = ca.gettitles().stream().map(n->n.getText()).collect(Collectors.toList());
		List<Integer> expstats = Stream.of((String)PROP.get("s1"),(String)PROP.get("s2"),(String)PROP.get("s3"),(String)PROP.get("s4")).map(n->Integer.parseInt(n)).collect(Collectors.toList());
		List<Integer> actstats = ca.gettitles().stream().map(n->Integer.parseInt(n.findElement(By.xpath(". //preceding-sibling::div[@class=\"stat\"]")).getText())).collect(Collectors.toList());
		Assert.assertEquals(actnames.size(),Integer.parseInt((String)PROP.get("titles")));
		Assert.assertEquals(actnames,expnames);
		Assert.assertEquals(actstats,expstats);
    }
    
    @When("^I click Services on the menu bar$")
    public void i_click_services_on_the_menu_bar() throws Throwable {
    	clientarea ca = new clientarea(cd);
		ca.getservices().click();
    }

    @Then("^I am able to see the Order New Services menu item$")
    public void i_am_able_to_see_the_order_new_services_menu_item() throws Throwable {
    	clientarea ca = new clientarea(cd);
    	WebElement orderservices = ca.getservices().findElement(By.xpath(". //ul/li[4]"));
    	Assert.assertTrue(orderservices.isDisplayed());
    }
    
    @When("^I click on Order New Services$")
    public void i_click_on_order_new_services() throws Throwable {
    	clientarea ca = new clientarea(cd);
    	WebElement orderservices = ca.getservices().findElement(By.xpath(". //ul/li[4]"));
    	orderservices.click();
    }

    @Then("^I am able to see the services page with the correct header$")
    public void i_am_able_to_see_the_services_page_with_the_correct_header() throws Throwable {
    	services sv = new services(cd);
    	Assert.assertEquals(sv.getheader().getText(), PROP.get("svheader"));
    }

    @And("^I am able to add services$")
    public void i_am_able_to_add_services() throws Throwable {
    	services sv = new services(cd);
		prodnames = new ArrayList<>();
		prodprices = new ArrayList<>();
		List<String> prods = Stream.of((String)PROP.getProperty("p1"),(String)PROP.getProperty("p2")).collect(Collectors.toList());
		int count = 1;
		for(int i=0;i<prods.size();i++)	{
			for(int j=0;j<sv.getproducts().size();j++)	{
				WebElement product = sv.getproducts().get(j).findElement(By.xpath(". //span"));
				if(product.getText().equals(prods.get(i)))	{
					Double price = Double.parseDouble(sv.getproducts().get(j).findElement(By.xpath(". //following-sibling::footer/div/span")).getText().split(" ")[0].substring(1));
					prodnames.add(product.getText());
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
    
    @And("^I am able to confirm the header text is correct on the cart page$")
    public void i_am_able_to_confirm_the_header_text_is_correct_on_the_cart_page() throws Throwable {
    	cart ct = new cart(cd);
    	Assert.assertEquals(ct.getheader().getText(), PROP.get("ctheader"));
    }

    @And("^I am able to confirm that the services I added are correct$")
    public void i_am_able_to_confirm_that_the_services_i_added_are_correct() throws Throwable {
    	cart ct = new cart(cd);
    	List<String> actprods = ct.getcart().findElements(By.xpath(". //div/div/div/span[@class=\"item-title\"]")).stream()
				.map(n->n.getText().split("Edit")[0].trim()).collect(Collectors.toList());
		Assert.assertEquals(actprods,prodnames);
    }

    @And("^The corresponding prices are correct$")
    public void the_corresponding_prices_are_correct() throws Throwable {
    	cart ct = new cart(cd);
		//convert original list of prices to string
		List<String> expPrices = prodprices.stream().map(n->	{
			String temp=String.format("%.2f", n);
			temp=PROP.get("cursym")+temp+PROP.get("cur");
			return temp;
			})
			.collect(Collectors.toList());
		List<String> actprices = ct.getcart().findElements(By.xpath(". //div/div/div[2]/span[1]")).stream()
				.map(n->n.getText().split("Edit")[0].trim()).collect(Collectors.toList());
		Assert.assertEquals(actprices,expPrices);
    }

    @And("^The order total is correct$")
    public void the_order_total_is_correct() throws Throwable {
    	cart ct = new cart(cd);
    	String expsum = PROP.get("cursym")+String.format("%.2f",(prodprices.stream().collect(Collectors.summingDouble(Double::doubleValue))))+PROP.get("cur");
		Assert.assertEquals(ct.gettotal().getText(),expsum);
    }
}