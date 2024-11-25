package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Utils.DBManager;
import Utils.ElementActions;

public class LoginPage {
	WebDriver driver;
	public String menu = "//*[@id='menuUser']";
	public String username = "//input[@name='username']";
	public String password = "//input[@name='password']";
	public String signIn = "//button[@id='sign_in_btn']";
	public String title = "(//span[contains(@class,'hi-user')])[last()]";

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void setLogin() throws InterruptedException {
		

		ElementActions.dynamicClick(driver, By.xpath(menu));
		ElementActions.sendData(driver, By.xpath(username), DBManager.testData.get("username"));
		ElementActions.sendData(driver, By.xpath(password), DBManager.testData.get("password"));
		ElementActions.dynamicClick(driver, By.xpath(signIn));

	}

	public String verifyTitle(String username) {
		
		ElementActions.performVisibleWait(driver, By.xpath(title));
		Assert.assertEquals(driver.findElement(By.xpath(title)).getText(), username+"1");
		return username;

	}

}
