package testcases.Batch_2m;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Pim_Config_Optnlfld {
	WebDriver d;
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
		d.findElement(By.id("menu_pim_viewPimModule")).click();
	}
	
	@Test
	public void tu_027()
	{
		
	}
	
	

}
