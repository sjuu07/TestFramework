package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
	//if you're running tests in parallel, as it ensures that each thread (test) has its own WebDriver instance.
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static void setDriver(String browser) {
		if (driver.get() == null) {
			switch (browser.toLowerCase()) {
			case "chrome":
				WebDriverManager.chromedriver().driverVersion("131.0.6778.86").setup();
				driver.set(new ChromeDriver());
				break;
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver.set(new org.openqa.selenium.firefox.FirefoxDriver());
				break;
			case "edge":
				WebDriverManager.edgedriver().setup();
				driver.set(new EdgeDriver());
				break;
			default:
				throw new IllegalArgumentException("Unsupported browser: " + browser);
			}
		}
	}

	public static WebDriver getDriver(String browser) {
		if (driver.get() == null) {
            setDriver(browser);  // Initialize WebDriver instance if it's null
        }
		return driver.get();
	}

	public static void quitDriver() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}
}
