package testcases.Batch_2m;



import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class Admin_job {
	public WebDriver d;
	
	@BeforeClass
	@Parameters({"browser"})
	public void Jobpage(String name)
	{ 
	  
		if(name.equals("firefox"))
		 {
			 System.setProperty("webdriver.gecko.driver", "C:\\Users\\Chaitu\\Desktop\\geckodriver.exe");
             d= new FirefoxDriver();
		 }
		 else
		 {  
			 System.setProperty("webdriver.chrome.driver", "C:\\Users\\Chaitu\\Desktop\\chromedriver.exe");
			 d= new ChromeDriver();
			 
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
	
	@Test(priority=10)
	public void tu_010()
	{
		Actions a2 = new Actions(d);
		a2.moveToElement(d.findElement(By.id("menu_admin_Job"))).build().perform();
		WebElement e1=d.findElement(By.xpath("html/body/div[1]/div[2]/ul/li[1]/ul/li[2]/ul/li[1]/a"));
		e1.click();
		String s1="http://opensource.demo.orangehrmlive.com/index.php/admin/viewJobTitleList";
		try {
		Assert.assertEquals(s1,d.getCurrentUrl());
		}
		catch(AssertionError ar)
		{
			
			Assert.assertTrue(false);
		}
		Assert.assertTrue(true);
		
	}
	@Test(priority=11)
	public void tu_011()
	{//job titles add button
		WebElement e= d.findElement(By.xpath("html/body/div[1]/div[3]/div[1]/div/div[2]/form/div[1]/input[1]"));
		e.click();
		try {
		
			Assert.assertEquals("http://opensource.demo.orangehrmlive.com/index.php/admin/saveJobTitle",d.getCurrentUrl());
		}
		catch(AssertionError ar)
		{
			Assert.assertTrue(false);
		}
		
		WebElement e2=d.findElement(By.id("jobTitle_jobTitle"));
		e2.sendKeys("dailyType11");
		d.findElement(By.id("jobTitle_jobDescription")).sendKeys("Non perminant,daily wages work people");
		//uploading file using wedriver
		WebElement fileinput=d.findElement(By.xpath("html/body/div[1]/div[3]/div/div[2]/form/fieldset/ol/li[3]/input"));
		fileinput.sendKeys("C:\\Users\\Chaitu\\Desktop\\dummy_Test.txt");
		
		d.findElement(By.xpath("html/body/div[1]/div[3]/div/div[2]/form/fieldset/p/input[1]")).click();
			WebElement htmltable= d.findElement(By.id("resultTable"));
			List<WebElement> rows=htmltable.findElements(By.tagName("tr"));
			
		    String xpath;int f=0;
			for(int i=1;i<rows.size();i++)
			{
				xpath="html/body/div[1]/div[3]/div[1]/div/div[2]/form/div[4]/table/tbody/tr["+i+"]/td[2]";
				if(d.findElement(By.xpath(xpath)).getText().equals("dailyType11"))
					{
					  f=1;break;
					}
				
				
			}
			
			if(f==1)
				Assert.assertTrue(true);
			else
				Assert.assertTrue(false);
			
   }
	
	@Test(dependsOnMethods= {"tu_011"},priority=12)
	public void tu_012()
	{ 
		WebElement e= d.findElement(By.xpath("html/body/div[1]/div[3]/div[1]/div/div[2]/form/div[1]/input[1]"));
		e.click();
		try {
		
			Assert.assertEquals("http://opensource.demo.orangehrmlive.com/index.php/admin/saveJobTitle",d.getCurrentUrl());
		}
		catch(AssertionError ar)
		{
			Assert.assertTrue(false);
		}
		WebElement e2=d.findElement(By.id("jobTitle_jobTitle"));
		e2.sendKeys("dailyType11");
		boolean  b= d.findElement(By.xpath("html/body/div[1]/div[3]/div/div[2]/form/fieldset/ol/li[1]/span")).isDisplayed();
		if(b==true)
        {
       	 Assert.assertTrue(true);
       	 d.findElement(By.xpath("html/body/div[1]/div[3]/div/div[2]/form/fieldset/p/input[2]")).click();
        }
        else
        {d.findElement(By.xpath("html/body/div[1]/div[3]/div/div[2]/form/fieldset/p/input[2]")).click();
       	 Assert.assertTrue(false);
        }
        
         
	}
	
	
	
   @Test(priority=16,dependsOnMethods= {"tu_012"})
    public void tu_013()
    { //delete button functionality 
    	 d.findElement(By.xpath("html/body/div[1]/div[3]/div[1]/div/div[2]/form/div[4]/table/thead/tr/th[1]/input")).click();
         WebElement e= d.findElement(By.id("btnDelete"));
         e.click();
        WebElement alert= d.findElement(By.id("dialogDeleteBtn"));
		alert.click();
		d.navigate().refresh();
		WebElement htmlTable = d.findElement(By.id("resultTable"));
		List<WebElement> cols=htmlTable.findElements(By.tagName("td"));
		if(cols.get(0).getText().equals("No Records Found"))
		{
			Assert.assertTrue(true);
		}
		
			
			
    }
   
   @AfterClass
   public void close()
   {
   	d.close();
   	
   }
    
	
	
	}
	
	
	


