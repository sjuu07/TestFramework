package Utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementActions {

	public static void performClickableWait(WebDriver driver,By path) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(path));
	}
	
	public static void performVisibleWait(WebDriver driver,By path) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(path));
	}
	
	
	public static void dynamicClick(WebDriver driver,By path) {
		boolean isClicked=false;
		int i=-1;;
		try {
			for(i=0;i<2;i++) {
				driver.findElement(path).click();
				isClicked=true;
			}
		}catch(Exception e){
			System.out.println("Counter :"+i);
		}
		
		if(isClicked!=true) {
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(path));
		}
		
	}
	
	public static void sendData(WebDriver driver,By path,String data) {
		driver.findElement(path).sendKeys(data);
	}
}
