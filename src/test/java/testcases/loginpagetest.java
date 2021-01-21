package testcases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.loginpage;
import resources.baseclass;

public class loginpagetest extends baseclass {
	private static Logger log = LogManager.getLogger(loginpagetest.class.getName());
	private loginpage lp;
	
	@BeforeClass
	private void init()	{
		lp = new loginpage(cd);				
	}
	
	@Test(priority=1)
	private void loginpage()	{
		try	{
			if(lp.getheader().getText().equals(PROP.getProperty("lpheadertxt")))	{
				log.trace("Arrived on login page");
			}
			else	{
				log.error("Incorrect header found");
			}
		}
		catch(NoSuchElementException e)	{
			log.warn("Header or login page missing");
		}
		Assert.assertEquals(lp.getheader().getText(), PROP.getProperty("lpheadertxt"));
	}
	
	@Test(dataProvider="MySQL-provider", priority=2)
	private void logintest(String username, String password)	{
		//flag to check validation message present
		boolean valmsg = false;
		
		//data driven test
		cd.get(PROP.getProperty("loginurl"));
		
		try	{
			lp.getemail().sendKeys(username);
			lp.getpassword().sendKeys(password);
			lp.getloginbtn().click();
			valmsg = lp.getmsg().isDisplayed();
		}
		catch(Exception e)	{
			log.info("Checking if login successful for user: "+username);
		}
		
		if (valmsg)	{
			log.info("Error is displayed for incorrect credentials for user: "+username);
			Assert.assertEquals(lp.getmsg().getText(), PROP.getProperty("msg"));
		}
	}
	
	@DataProvider(name = "MySQL-provider")
	private String[][] mySQL_Data() throws SQLException, ClassNotFoundException	{
		int rowCount = 0;
		int columnCount = 0;
		String host = "localhost";
		String port = "3306";
		String myData [][] = null;
		
		try	{
			Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/webtest", "root", "*_r3@dyAP1!_*");
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);	
			ResultSet rs = s.executeQuery("select username, password from login");
			rs.last();
			rowCount = rs.getRow();
			ResultSetMetaData rs_md = rs.getMetaData();
			columnCount = rs_md.getColumnCount();			
			myData = new String[rowCount][columnCount];			
			int i = 0;
			rs.beforeFirst();
			while(rs.next())	{
				for(int j = 0; j < columnCount; j++)	{
					myData[i][j] = rs.getString(j+1);
				}
				i++;
			}			
			s.close();
			con.close();
		}
		catch(Exception e)	{}		
		return myData;
	}
}