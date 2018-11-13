package testcases.Batch_2m;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Nationality {
	
	WebDriver d;
	int flag,j;
	int flag2;
	String actual,xpath;
	@BeforeClass
	@Parameters({"browser"})
	public void Launch(String name)
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
	    flag=flag2=0;
	    actual=xpath=null;
	    
	}
	
	@Test(priority=1)
	public void tu_027()
	{
		WebElement nationality=d.findElement(By.id("menu_admin_nationality"));
		 nationality.click();
		 try
		 {
			 Assert.assertEquals(d.getCurrentUrl(),"http://opensource.demo.orangehrmlive.com/index.php/admin/nationality");
		      Assert.assertTrue(true);
		     
		 }
		 catch(AssertionError ae)
		 {
			 flag=1;
			 Assert.assertTrue(false);
			 
		 }
		 
	}
	
	@Test(priority=2,dependsOnMethods= {"tu_027"})
	public void tu_028()
	{// add button  functionality checking
		if(flag==1)
		{
			throw new SkipException("unable to carry this step");
			
		}
		WebElement add= d.findElement(By.xpath("html/body/div[1]/div[3]/div[2]/div/div[2]/form/div[1]/input[1]"));
		add.click();
		WebElement text=d.findElement(By.xpath("html/body/div[1]/div[3]/div[1]/div[2]/form/fieldset/ol/li[1]/input"));
		  text.clear();
		  actual="Aakash"+System.currentTimeMillis();
		  text.sendKeys(actual);
		  text.sendKeys(Keys.ENTER);
		  WebElement html=d.findElement(By.id("resultTable"));
		  List<WebElement>rows=html.findElements(By.tagName("tr"));
		  for(int i=1;i<=rows.size();i++)
		  {
			  xpath="html/body/div[1]/div[3]/div[2]/div/div[2]/form/div[4]/table/tbody/tr["+i+"]/td[2]";
			  String s1=  d.findElement(By.xpath(xpath)).getText();
			  if(s1.equals(actual))
			  {
				 flag2=i;break;
			  }
			  
			}

		  if(flag2==0)
			  Assert.assertTrue(false);
		  else
			  Assert.assertTrue(true);
	}
	
	@Test(priority=3,dependsOnMethods= {"tu_028"})
	public void tu_029()
	{//delete button functionality  
		
		if(flag2==0)
		{
			throw new SkipException("unable to carry this test");
		}
		String xpath="html/body/div[1]/div[3]/div[2]/div/div[2]/form/div[4]/table/tbody/tr["+flag2+"]/td[1]/input";
		d.findElement(By.xpath(xpath)).click();
		d.findElement(By.xpath("html/body/div[1]/div[3]/div[2]/div/div[2]/form/div[1]/input[2]")).click();
		d.findElement(By.xpath("html/body/div[1]/div[3]/div[3]/div[3]/input[1]")).click();
		String s=d.findElement(By.xpath(xpath)).getText();
		try
		{
			Assert.assertEquals(s, actual);
			Assert.assertTrue(false);
		}
		catch(AssertionError ae)
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


