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
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Org_struct {
	WebDriver d;
	  int flag;
	  String s2;
	
	@BeforeClass
	@Parameters("browser")
	public void launch(String name) throws MalformedURLException
	{ 

		if(name.equals("firefox"))
		{
		 System.setProperty("webdriver.gecko.driver", "C:\\Users\\Chaitu\\Desktop\\geckodriver.exe");
		 DesiredCapabilities capability = DesiredCapabilities.firefox();
		 d= new RemoteWebDriver(new URL("http://172.16.2.105:5555/wd/hub"), capability);
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
	public void tu_024()
	{
		Actions a = new Actions(d);
		 a.moveToElement(d.findElement(By.cssSelector("#menu_admin_Organization"))).build().perform();
		 WebElement e1=d.findElement(By.cssSelector("#menu_admin_viewCompanyStructure"));
		 e1.click();
		 try
		 {
			 Assert.assertEquals("http://opensource.demo.orangehrmlive.com/index.php/admin/viewCompanyStructure", d.getCurrentUrl());
			  Assert.assertTrue(true);
			  flag=0;  
			  
		 }
		 catch(AssertionError e2)
		 {
			  flag=1;
			 Assert.assertTrue(false);
		 }
	}
	
	@Test(priority=2,dependsOnMethods={"tu_024"})
	public void tu_025()
	{ int flag2=0;
		if(flag==1)
		{   
			 throw  new SkipException("not possible to test this case");
			
		}
		WebElement edit=d.findElement(By.xpath("html/body/div[1]/div[3]/div[1]/div[2]/p/input"));
		   edit.click();
		WebElement plus=d.findElement(By.xpath("html/body/div[1]/div[3]/div[1]/div[2]/ol/li/a[2]"));
		 plus.click();
		 WebElement name=d.findElement(By.xpath("html/body/div[1]/div[3]/div[2]/div[2]/form/fieldset/ol/li[2]/input"));
		  s2=""+System.currentTimeMillis();
		 name.sendKeys(s2);
		 WebElement save=d.findElement(By.xpath("html/body/div[1]/div[3]/div[2]/div[3]/input[1]"));
		 save.click();
		 WebElement li= d.findElement(By.id("ohrmTreeViewComponent_Tree"));
		 List<WebElement>l2=li.findElements(By.tagName("li"));
		 for(WebElement e:l2)
		 {
			 List<WebElement>an=e.findElements(By.tagName("a"));
            for(WebElement link:an)
            {
          	  if(link.getText().equals(s2))
          	  { 
          		flag2=1;  
          		break;
          	  }
            }

		 }
		  
		 if(flag2==0)
			 Assert.assertTrue(false);
	}
	
	@Test(priority=3,dependsOnMethods= {"tu_025"},alwaysRun=false)
	public void tu_026()
	{
		
	   WebElement edit=d.findElement(By.xpath("html/body/div[1]/div[3]/div[1]/div[2]/p/input"));
	    edit.click();
	    edit.click();
	    WebElement orhrm= d.findElement(By.id("ohrmTreeViewComponent_Tree"));
	    List<WebElement>an=orhrm.findElements(By.tagName("a"));
	    int index=0;
	    for(WebElement a:an)
	    { 
	    	if(a.getText().equals(s2))
	    	index=an.indexOf(a);
	    }
	    
	    an.get(index+2).click();
	    d.findElement(By.id("dialogYes")).click();
	    Actions a = new Actions(d);
		 a.moveToElement(d.findElement(By.cssSelector("#menu_admin_Organization"))).build().perform();
		 WebElement e1=d.findElement(By.cssSelector("#menu_admin_viewCompanyStructure"));
		 e1.click();
		 edit=d.findElement(By.xpath("html/body/div[1]/div[3]/div[1]/div[2]/p/input"));
		 edit.click();
		 orhrm= d.findElement(By.id("ohrmTreeViewComponent_Tree"));
		   an=orhrm.findElements(By.tagName("a"));
		     index=-1;
		    for(WebElement a1:an)
		    { 
		    	if(a1.getText().equals(s2))
		    	index=an.indexOf(a1);
		    }
		 if(index!=-1)
			 Assert.assertTrue(false);
		 else
			 Assert.assertTrue(true);
		 
	    
	    	
	    
	}
	
	
	@AfterClass
	public void close()
	{
	     d.close();
	}

}
