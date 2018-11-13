package testcases.Batch_2m;

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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Employee_Class {
	WebDriver d;
	public int flag;
	String s3;
	@BeforeClass
	@Parameters("browser")
	public void launch(String name) throws MalformedURLException
	{ s3="sirish"+System.currentTimeMillis();
		if(name.equals("firefox"))
		{
		 System.setProperty("webdriver.gecko.driver", "C:\\Users\\Chaitu\\Desktop\\geckodriver.exe");
		 DesiredCapabilities capability = DesiredCapabilities.firefox();
		 d=new RemoteWebDriver(new URL("http://172.16.2.107:5555/wd/hub"),capability);
		 capability.setBrowserName("firefox");
		 capability.setPlatform(Platform.WINDOWS);
		}
	else
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Chaitu\\Desktop\\chromedriver.exe");
		d=new ChromeDriver();
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

	 @Test(priority=1)
	 public void tu_019()
	 {
		 Actions a = new Actions(d);
		 a.moveToElement(d.findElement(By.cssSelector("#menu_admin_Job"))).build().perform();
		 WebElement e1=d.findElement(By.id("menu_admin_employmentStatus"));
		 e1.click();
		 try
		 {
			 Assert.assertEquals("http://opensource.demo.orangehrmlive.com/index.php/admin/employmentStatus", d.getCurrentUrl());
			  Assert.assertTrue(true);
			  flag=0;  
			  
	 }
		 catch(AssertionError e2)
		 {
			  flag=1;
			 Assert.assertTrue(false);
		 }
		 
	 }
	 
	 @Test(priority=2,dependsOnMethods= {"tu_019"})
	 public void tu_020()
	 {//add button functionality
		 int f;
		 d.findElement(By.cssSelector("#btnAdd")).click();
		 WebElement name=d.findElement(By.xpath("html/body/div[1]/div[3]/div[1]/div[2]/form/fieldset/ol/li[1]/input"));
		 name.sendKeys(s3);
		  WebElement e2=d.findElement(By.xpath("html/body/div[1]/div[3]/div[1]/div[2]/form/fieldset/p/input[1]"));
		  e2.click();
		  WebElement Htmltable=d.findElement(By.id("resultTable"));
		  List<WebElement> l1=Htmltable.findElements(By.tagName("tr"));
		   int count=l1.size();
		   f=1;
		   for(int i=1;i<=count;i++)
		   {
			   String s1="html/body/div[1]/div[3]/div[2]/div/div[2]/form/div[4]/table/tbody/tr["+i+"]/td[2]";
			    String s2=d.findElement(By.xpath(s1)).getText();
			    try {
			     Assert.assertEquals(s2,s3);
			     f=0;
			     break;
			      
			    }
			    catch(AssertionError e3)
			    {
			        f=1;
			    }
			    
		 }
		   if(f==1)
			   Assert.assertTrue(false);
	 }
	 
	 @Test(priority=3,dependsOnMethods= {"tu_020"})
	 public void tu_021()
	 {//delete button functionality
		 
		 WebElement Htmltable=d.findElement(By.id("resultTable"));
		  List<WebElement> l1=Htmltable.findElements(By.tagName("tr"));
		   int count=l1.size();
		   int i;
		   for(i=1;i<=count;i++)
		   {
			   String s1="html/body/div[1]/div[3]/div[2]/div/div[2]/form/div[4]/table/tbody/tr["+i+"]/td[2]";
			   String s7=d.findElement(By.xpath(s1)).getText();
			   if(s7.equals(s3))
		    {
			    break;
		   }
		 }
		  String  s1="html/body/div[1]/div[3]/div[2]/div/div[2]/form/div[4]/table/tbody/tr["+i+"]/td[1]/input";
		  d.findElement(By.xpath(s1)).click();
		  d.findElement(By.xpath("html/body/div[1]/div[3]/div[2]/div/div[2]/form/div[1]/input[2]")).click();
		  d.findElement(By.cssSelector("#dialogDeleteBtn")).click();
		  //checking after deletion
		  d.navigate().refresh();
		  
		  Htmltable=d.findElement(By.id("resultTable"));
		  l1=Htmltable.findElements(By.tagName("td"));
		  
		  for(WebElement e:l1)
		  {
			  
			  try {
				  Assert.assertEquals(e.getText(),s3);
				   Assert.assertTrue(false);
			  }
			  catch(AssertionError ae)
			  {
				  
			  }
		  }
		  Assert.assertTrue(true);
		  
	 }
	 
	 @AfterClass
	 public void close()
	 {
		 d.close();
	 }
	 

}
