package testcases.Batch_2m;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Org_Gen_Info {
	 
	    WebDriver d;
	    int flag;
	
	@BeforeClass
	@Parameters("browser")
	public void launch(String name)
	{
		
		if(name.equals("firefox"))
		{
		 System.setProperty("webdriver.gecko.driver", "C:\\Users\\Chaitu\\Desktop\\geckodriver.exe");
          d=new FirefoxDriver();
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
	public void tu_022()
	{
		Actions a = new Actions(d);
		 a.moveToElement(d.findElement(By.cssSelector("#menu_admin_Organization"))).build().perform();
		 WebElement e1=d.findElement(By.cssSelector("#menu_admin_viewOrganizationGeneralInformation"));
		 e1.click();
		 try
		 {
			 Assert.assertEquals("http://opensource.demo.orangehrmlive.com/index.php/admin/viewOrganizationGeneralInformation", d.getCurrentUrl());
			  Assert.assertTrue(true);
			  flag=0;  
			  
		 }
		 catch(AssertionError e2)
		 {
			  flag=1;
			 Assert.assertTrue(false);
		 }
	}

	@Test(priority=2,dependsOnMethods= {"tu_022"})
	public void tu_023()
	{
		if(flag==1)
		{
			throw new SkipException("not able to execute");
		}
		WebElement e= d.findElement(By.cssSelector("#btnSaveGenInfo"));
		e.click();
		String s1=System.currentTimeMillis()+"harish";
		d.findElement(By.xpath("html/body/div[1]/div[3]/div/div[2]/form/fieldset/ol[3]/li[5]/input")).clear();
		d.findElement(By.xpath("html/body/div[1]/div[3]/div/div[2]/form/fieldset/ol[3]/li[5]/input")).sendKeys(s1);
		d.findElement(By.xpath("html/body/div[1]/div[3]/div/div[2]/form/fieldset/p/input")).click();
		String s2=d.findElement(By.cssSelector("#organization_zipCode")).getAttribute("value");
		try
		{
			 
			Assert.assertEquals(s1,s2);
			Assert.assertTrue(true);
		}
		catch(AssertionError ae)
		{
			Assert.assertTrue(false);
		}
	}
	@AfterClass
	public void close()
	{
		d.close();
	}
	
}
