package testcases.Batch_2m;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class Login {
	 public String b;
	
	@Test(priority=1)
	@Parameters({"browser"})
public void tu_001(String name) 
{      
        WebDriver d;
        b=name;//="firefox";
      if(b.equals("firefox"))
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
	if(d.getCurrentUrl().equals("http://opensource.demo.orangehrmlive.com/"))
	{
		Assert.assertTrue(true);
		
	}
	else {
		d.close();
		Assert.assertTrue(false);
	}
	    d.close();
	   
}
	
	@Test(dependsOnMethods={"tu_001"},alwaysRun=true,priority=2)
	
	public void tu_002() 

	{
         WebDriver d;
        
		if(b.equals("firefox"))
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
		List<WebElement> l= d.findElements(By.tagName("input"));
		int c1=0,c2=0,c3=0,c4=0;
		for(WebElement s1:l)
		{
			if(s1.getAttribute("type").equals("hidden"))
				c1++;
			 if(s1.getAttribute("type").equals("text"))
				c2++;
			 if(s1.getAttribute("type").equals("password"))
					c3++;
			 if(s1.getAttribute("type").equals("submit"))
					c4++;
				
		}
		if(c1==3&&c2==1&&c3==1&&c4==1);
			Assert.assertTrue(true);
		
		
		 d.close();
		
	}
	
	@Test(priority=3)
	public void tu_003() 
	{//acceptance login
         WebDriver d;
        
		if(b.equals("firefox"))
		 {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\Chaitu\\Desktop\\geckodriver.exe");
            d=new FirefoxDriver();
		 }
		 else
		 {  
			 System.setProperty("webdriver.chrome.driver", "C:\\Users\\Chaitu\\Desktop\\chromedriver.exe");
			 d= new ChromeDriver();
			 
		 }
		d.manage().window().maximize();
		d.manage().deleteAllCookies();
		d.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		d.get("http://opensource.demo.orangehrmlive.com/");
	    WebElement e = d.findElement(By.xpath("html/body/div[1]/div/div[2]/form/div[2]/input"));
	    e.sendKeys("Admin");
	    d.findElement(By.xpath("html/body/div[1]/div/div[2]/form/div[3]/input")).sendKeys("admin");
	    WebElement e2= d.findElement(By.xpath("html/body/div[1]/div/div[2]/form/div[5]/input"));
	    e2.click();
	    try {
	    Assert.assertEquals(d.getCurrentUrl(), "http://opensource.demo.orangehrmlive.com/index.php/dashboard");
	    }
	    catch(Exception e1)
	    {
	    	Assert.assertTrue(false);
	    }
	    d.close();
	    
	}
	
	//Data providing through Excel sheet

	@DataProvider(name="TestData")
	public Object[][] read() throws BiffException, IOException
	{
		File f= new File("C:\\Users\\Chaitu\\Desktop\\l1.xls");
		Workbook w=  Workbook.getWorkbook(f);
		Sheet s= w.getSheet(0);
		int rows = s.getRows();
		int cols= s.getColumns();
		String inputData[][]=new String[rows][cols];
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				Cell c= s.getCell(j,i);
				inputData[i][j]=c.getContents();
				
			}
			
			
		}
		return inputData;
	
	}

	@Test(dataProvider="TestData",priority=4)
	public void tu_004(String s1, String s2) 
	{
           WebDriver d;
        
		if(b.equals("firefox"))
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
		d.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		d.get("http://opensource.demo.orangehrmlive.com/");
	    WebElement e = d.findElement(By.xpath("html/body/div[1]/div/div[2]/form/div[2]/input"));
	    e.sendKeys(s1);
	    d.findElement(By.xpath("html/body/div[1]/div/div[2]/form/div[3]/input")).sendKeys(s2);
	    WebElement e2= d.findElement(By.xpath("html/body/div[1]/div/div[2]/form/div[5]/input"));
	    e2.click();
	    if(d.findElement(By.xpath("html/body/div[1]/div/div[2]/form/div[5]/span")).isDisplayed())
	    {
	    	Assert.assertTrue(true);
	    }
	    else
	    	Assert.assertTrue(false);
	    d.close();
	   
	}	
	
}
