package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class signin {

	public WebDriver driver;
	By Email= By.xpath("//*[@id=\"email\"]");
	By Pass= By.xpath("//*[@id=\"password\"]");
	By submit = By.xpath("//*[@id=\"sign_in_submit\"]");
	
	public signin(WebDriver driver) {
			
			this.driver= driver;
		}
	public WebElement Email() {
			
			return driver.findElement(Email);
		}
	public WebElement Password() {
		
		return driver.findElement(Pass);
	}
public WebElement Submit() {
		
		return driver.findElement(submit);
	}
}
