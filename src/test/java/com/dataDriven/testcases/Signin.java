package com.dataDriven.testcases;


import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.excel.utility.Xls_Reader;

import Resources.Base;

public class Signin extends Base {
	
	static Xls_Reader r= new Xls_Reader("C:\\eclipse-workspace\\DataDriven\\src\\test\\java\\com\\testData\\LetsVentureTestDta.xlsx");

@Test(priority=1)
public void getdata() throws IOException, InterruptedException {
	
	String email1= r.getCellData("loginTestData", "Email", 2);
	System.out.println(email1);
	String passowrd1= r.getCellData("loginTestData", "Password", 2);
	System.out.println(passowrd1);
	String email2= r.getCellData("loginTestData", "Email", 3);
	System.out.println(email2);
	String passowrd2= r.getCellData("loginTestData", "Password", 3);
	System.out.println(passowrd2);
}
	@Test(priority=2)
	public void signin() throws InterruptedException {
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--disable-notifications");
	System.setProperty("webdriver.chrome.driver", "C:\\eclipse-workspace\\Venture\\executablefiles\\chromedriver.exe");
	WebDriver driver =new ChromeDriver(options);
	driver.navigate().to(baseurl);
	driver.manage().window().maximize();
	PageObject.signin s= new PageObject.signin(driver);

	int t_rows= r.getRowCount("loginTestData");
	
	r.addColumn("loginTestData", "Status");
	for(int row_num=2; row_num<=t_rows; row_num++) {
		
		String Email=r.getCellData("loginTestData", "Email", row_num);
		String Password = r.getCellData("loginTestData", "Password", row_num);
		s.Email().sendKeys(Email);
		Thread.sleep(1000);
		s.Password().sendKeys(Password);
		Thread.sleep(1000);
		s.Submit().click();
		String actualurl=driver.getCurrentUrl();
		System.out.println(actualurl);
		String expectedurl="https://staging.letsventure.com/startups";
		if(actualurl.equals(expectedurl)) {
			r.setCellData("loginTestData", "Status", row_num, "Pass");
			driver.findElement(By.xpath("//*[@id=\"profileMore\"]")).click();
			driver.findElement(By.xpath("//*[@id=\"navbarCollapse\"]/ul[2]/li[9]/div/a[9]")).click();
			s.Email().clear();
			s.Password().clear();
			
	}
		else {
			
			r.setCellData("loginTestData", "Status", row_num, "Fail");
			String err= driver.findElement(By.xpath("/html/body/div[3]/div/div/div/div[1]/div")).getText();
			System.out.println(err);
			s.Email().clear();
			s.Password().clear();
		}
	}
}
}
