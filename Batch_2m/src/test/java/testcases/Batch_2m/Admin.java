package testcases.Batch_2m;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Admin {
	
	public WebDriver d;
	@BeforeClass
	@Parameters({"browser"})
	public void launch(String name)
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
		d.manage().deleteAllCookies();
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	    d.get("http://opensource.demo.orangehrmlive.com/");
	    
	    try {
	    WebElement e = d.findElement(By.xpath("html/body/div[1]/div/div[2]/form/div[2]/input"));
	    e.sendKeys("Admin");
	    }
	    catch(org.openqa.selenium.StaleElementReferenceException ex)
	    {
		    WebElement e = d.findElement(By.xpath("html/body/div[1]/div/div[2]/form/div[2]/input"));
		    e.sendKeys("Admin");
		    }
	   
	    try {
	    	 d.findElement(By.xpath("html/body/div[1]/div/div[2]/form/div[3]/input")).sendKeys("admin");
		    }
		    catch(org.openqa.selenium.StaleElementReferenceException ex)
		    {
		    	 d.findElement(By.xpath("html/body/div[1]/div/div[2]/form/div[3]/input")).sendKeys("admin");
			    }
	    
	    try {
	    	WebElement e2= d.findElement(By.xpath("html/body/div[1]/div/div[2]/form/div[5]/input"));
		    e2.click();
		    }
		    catch(org.openqa.selenium.StaleElementReferenceException ex)
		    {
		    	WebElement e2= d.findElement(By.xpath("html/body/div[1]/div/div[2]/form/div[5]/input"));
			    e2.click();
			    }
	    
	   try
	   {
	    WebElement e1= d.findElement(By.cssSelector("#menu_admin_viewAdminModule>b")); 
	    e1.click();
	    e1.click();
	   }
	    catch(org.openqa.selenium.StaleElementReferenceException ex)
	   {
	    	WebElement e1= d.findElement(By.cssSelector("#menu_admin_viewAdminModule>b")); 
		   e1.click();
	      
	   }
	   

    
	}
	
	
	@Test(priority=5)
	public void tu_005()
	{
		
		Actions a= new Actions(d);
		WebElement e= d.findElement(By.cssSelector("#menu_admin_UserManagement"));
		a.moveToElement(e).build().perform();
		WebElement e2=d.findElement(By.cssSelector("#menu_admin_viewSystemUsers"));
		e2.click();
		try {
		Assert.assertEquals(d.getCurrentUrl(),"http://opensource.demo.orangehrmlive.com/index.php/admin/viewSystemUsers");
		}
		catch(AssertionError ar)
		{
			Assert.assertTrue(false);
		}
		
	}
	
	@Test(dependsOnMethods= {"tu_005"},alwaysRun=false,priority=6)
	public void tu_006()
	{
		WebElement e= d.findElement(By.name("btnAdd"));
		e.click();
		try
		{
			Assert.assertEquals("http://opensource.demo.orangehrmlive.com/index.php/admin/saveSystemUser", d.getCurrentUrl());
		}
		catch(AssertionError  ar)
		{
			Assert.assertTrue(false);
		}
	
	}
	
	@Test(dependsOnMethods= {"tu_006"},priority=7)
	@Parameters({"c"})
	public void tu_007(String c)
	{ 
		Select dp=new 	Select(d.findElement(By.id("systemUser_userType")));
		dp.selectByIndex(0);
		d.findElement(By.xpath("html/body/div[1]/div[3]/div/div[2]/form/fieldset/ol/li[2]/input[1]")).sendKeys("Russel Hamilton");
		d.findElement(By.xpath("html/body/div[1]/div[3]/div/div[2]/form/fieldset/ol/li[3]/input")).sendKeys(c);
		Select dp2=new Select(d.findElement(By.id("systemUser_status")));
		dp2.selectByIndex(0);
		d.findElement(By.xpath("html/body/div[1]/div[3]/div/div[2]/form/fieldset/ol/li[6]/input")).sendKeys("admin");
		d.findElement(By.xpath("html/body/div[1]/div[3]/div/div[2]/form/fieldset/ol/li[7]/input[1]")).sendKeys("admin");
		WebElement e1= d.findElement(By.name("btnSave"));
		e1.click();
		WebElement table=d.findElement(By.id("resultTable"));
		
		List<WebElement> cells=table.findElements(By.tagName("td"));
		int i=0;
		 
		for(WebElement a:cells)
		{
			if(a.getText().equals(c))
				{
				  Assert.assertTrue(true);
				   i=1;
				}
		}
		
		if(i==0)
			Assert.assertTrue(false);
	}
	
	 @Test(priority=8,dependsOnMethods= {"tu_007"})
	 @Parameters({"c"})
	  public void tu_008(String c)
	  { //when some data is already stored and deletion is done
	        
			WebElement e1=d.findElement(By.xpath("html/body/div[1]/div[3]/div[2]/div/div/form/div[4]/table/thead/tr/th[1]/input"));
			e1.click();
			d.findElement(By.name("btnDelete")).click();
			d.findElement(By.id("dialogDeleteBtn")).click();
			
			d.navigate().refresh();
			WebElement table= d.findElement(By.id("resultTable"));
			List<WebElement> cols=table.findElements(By.tagName("td"));
			
			for(WebElement e:cols)
			{
				if(e.getText().equals(c))
				{
					Assert.assertTrue(false);
				}

			}
			Assert.assertTrue(true);
			
}
 
    @Test(priority=9)
    public void tu_009()
    { //search functionality in Admin  page
    	
    	WebElement e5= d.findElement(By.xpath("html/body/div[1]/div[3]/div[1]/div[2]/form/fieldset/ol/li[1]/input"));
    	e5.sendKeys("maggi1");
    	WebElement e6= d.findElement(By.xpath("html/body/div[1]/div[3]/div[1]/div[2]/form/fieldset/ol/li[3]/input[1]"));
    	e6.sendKeys("John Smith1");
    	d.findElement(By.id("searchBtn")).click();
    	WebElement table =d.findElement(By.id("resultTable"));
    	  List<WebElement> cols=table.findElements(By.tagName("td"));
    	   for(WebElement e:cols)
    	   {
    		   if(e.getText().equals("No Records Found"))
    		   {
    			   Assert.assertTrue(true);
    			   return;
    			   
    		   }
    	   }
    	   Assert.assertTrue(false);
    		  
     }
    
    @AfterClass
    public void close()
    {
    	d.close();
    	
    }
    
    
    

	
	
	
	

}