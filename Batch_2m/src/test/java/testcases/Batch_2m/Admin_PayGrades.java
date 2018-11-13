package testcases.Batch_2m;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Admin_PayGrades {
	WebDriver d;
	String b ;
	@BeforeClass
	@Parameters({"browser"})
	public void Launch(String name) throws MalformedURLException
	{  b=name;
		if(name.equals("firefox"))
			{
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\Chaitu\\Desktop\\geckodriver.exe");
			 DesiredCapabilities capability = DesiredCapabilities.firefox();
			 d= new RemoteWebDriver(new URL("http://172.16.2.105:5555/wd/hub"),capability);
			 capability.setBrowserName("firefox");
			 capability.setPlatform(Platform.WINDOWS);

			}
		else
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Chaitu\\Desktop\\chromedriver.exe");
			d= new ChromeDriver() ;

		}
		d.manage().window().maximize();
		d.manage().deleteAllCookies();
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	    d.get("http://opensource.demo.orangehrmlive.com/");
	    WebElement e = d.findElement(By.xpath("html/body/div[1]/div/div[2]/form/div[2]/input"));
	    e.sendKeys("Admin");
	    d.findElement(By.xpath("html/body/div[1]/div/div[2]/form/div[3]/input")).sendKeys("admin");
	    WebElement e2= d.findElement(By.xpath("html/body/div[1]/div/div[2]/form/div[5]/input"));
	    e2.click();
	    WebElement e1= d.findElement(By.cssSelector("#menu_admin_viewAdminModule>b")); 
	    e1.click();
	}
	@Test(priority=14)
	public void tu_014()
	{
		Actions admin_job= new Actions(d);
		WebElement adminjob=d.findElement(By.xpath("html/body/div[1]/div[2]/ul/li[1]/ul/li[2]/a"));
		admin_job.moveToElement(adminjob).build().perform();
		WebElement paygrades=d.findElement(By.id("menu_admin_viewPayGrades"));
		paygrades.click();
		try
		{
			Assert.assertEquals(d.getCurrentUrl(),"http://opensource.demo.orangehrmlive.com/index.php/admin/viewPayGrades");
		}
		
		catch(AssertionError ar)
		{
			Assert.assertTrue(false);
		}
		
	}
	
   @Test(dependsOnMethods= {"tu_014"},alwaysRun=false,priority=15)
   public void tu_015()
   { //add button functionality
	   WebElement add= d.findElement(By.xpath("html/body/div[1]/div[3]/div[1]/div/div[2]/form/div[1]/input[1]"));
	   add.click();
	   try
	   {
		  Assert.assertEquals(d.getCurrentUrl(),"http://opensource.demo.orangehrmlive.com/index.php/admin/payGrade");
		  
	   }
	   catch(AssertionError ar)
	   {
		   Assert.assertTrue(false);
	   }
	      d.findElement(By.id("btnCancel")).click();

	}
  // -------------------------------------------------------------
   
   @DataProvider(name="TestData")
   public Object[][] readxl() throws BiffException, IOException
   {
	   File f= new File("C:\\Users\\Chaitu\\Desktop\\l1.xls");
	   Workbook w= Workbook.getWorkbook(f);
	   Sheet s=w.getSheet(1);
	   int rows=s.getRows();
	   int cols=s.getColumns();
	   String inputData[][]=new String[rows][cols];
	   for(int i=0;i<rows;i++)
		   for(int j=0;j<cols;j++)
		   {
			   Cell c=s.getCell(j,i);
			   inputData[i][j]=c.getContents();
			   
		   }
	   
	   return inputData;
   }
   //------------------------------------------------------------------------
   @Test(dataProvider="TestData",priority=2,dependsOnMethods= {"tu_015"},alwaysRun=false)
   public void tu_016(String name)
   {      name=b+name;
	      WebElement add= d.findElement(By.xpath("html/body/div[1]/div[3]/div[1]/div/div[2]/form/div[1]/input[1]"));
	      add.click();
          WebElement namefield= d.findElement(By.xpath("html/body/div[1]/div[3]/div/div[2]/form/fieldset/ol/li[1]/input[1]"));
	      namefield.sendKeys(name);
	      d.findElement(By.id("btnSave")).click();
	      d.findElement(By.id("btnCancel")).click();
	      WebElement htmltable=d.findElement(By.id("resultTable"));
	      List<WebElement>rows=htmltable.findElements(By.tagName("tr"));
	      
	      for(int i=1;i<rows.size();i++)
	      { 
	    	  
	    	  String s1="html/body/div[1]/div[3]/div[1]/div/div[2]/form/div[4]/table/tbody/tr["+i+"]/td[2]";
	    	  String s2=d.findElement(By.xpath(s1)).getText();
	    	  if(s2.equals(name))
	    	  {
	    		  Assert.assertTrue(true);
	    		  return;
	    	  }
	      }
	      
	     Assert.assertTrue(false); 
	     
	     

   }
   @Test(priority=17,dependsOnMethods= {"tu_016"},alwaysRun=false)
   public void tu_017()
   {//checking add  button functionality along with adding currency functionality
	   WebElement add= d.findElement(By.xpath("html/body/div[1]/div[3]/div[1]/div/div[2]/form/div[1]/input[1]"));
	      add.click();
	      WebElement namefield= d.findElement(By.xpath("html/body/div[1]/div[3]/div/div[2]/form/fieldset/ol/li[1]/input[1]"));
	      namefield.sendKeys(b+"agni");
  	      d.findElement(By.id("btnSave")).click();
          WebElement currencybutton= d.findElement(By.id("btnAddCurrency"));
	       currencybutton.click();
	       WebElement currencyField=d.findElement(By.id("payGradeCurrency_currencyName"));
	       currencyField.sendKeys("AWG - Aruban Florin");
	       d.findElement(By.id("btnSaveCurrency")).click();
	       WebElement cancelbutton=d.findElement(By.id("btnCancel"));
	       cancelbutton.click();
	       WebElement htmltable=d.findElement(By.id("resultTable"));
		   List<WebElement>rows=htmltable.findElements(By.tagName("tr"));
	       for(int i=1;i<rows.size();i++)
	       {	
		       String s1="html/body/div[1]/div[3]/div[1]/div/div[2]/form/div[4]/table/tbody/tr["+i+"]/td[2]";
		       String s2="html/body/div[1]/div[3]/div[1]/div/div[2]/form/div[4]/table/tbody/tr["+i+"]/td[3]";
		       WebElement name=d.findElement(By.xpath(s1));
		       WebElement currency=d.findElement(By.xpath(s2));
		       if(name.getText().equals(b+"agni")&& currency.getText().equals("Aruban Florin"))
		       {
		    	  Assert.assertTrue(true);
		    	  return;
		       }
		       
 
	       }
	       Assert.assertTrue(false);

	       
   }
   
   @Test(priority=18,dependsOnMethods= {"tu_017"})
   public void tu_018()
   {//delete functionality
	   d.findElement(By.xpath("html/body/div[1]/div[3]/div[1]/div/div[2]/form/div[4]/table/thead/tr/th[1]/input")).click();
	   WebElement dltbutton=d.findElement(By.id("btnDelete"));
	   dltbutton.click();
	   d.findElement(By.id("dialogDeleteBtn")).click();
	   d.navigate().refresh();
		WebElement htmlTable = d.findElement(By.id("resultTable"));
		List<WebElement> cols=htmlTable.findElements(By.tagName("td"));
		if(cols.get(0).getText().equals("No Records Found"))
		{
			Assert.assertTrue(true);
		}
		else
			Assert.assertTrue(false);
		
          
          
   }
   
   @AfterClass
   public void close()
   {//closing the launcher
	   d.close();
   }
	   
   }
   
   
   
   
   
   


