package StepDefinition;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.reporter.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import CucumberExecutor.TestRunner;
import PageObjects.LoginPage;
import Utils.DBManager;

public class StepDefs {
	public Scenario scenario;
	public static WebDriver driver ;
	
	LoginPage lp;

	@Before
	public void initailSetUp(Scenario scenario) throws Exception {
		driver = TestRunner.driver;
		this.scenario = scenario;
		DBManager dbManager = new DBManager();
		dbManager.getExcelData("src\\test\\resources\\testData\\testdata.xlsx", "testdata", scenario.getName());
		System.out.println(DBManager.testData);

		lp = new LoginPage(driver);
	}
	
	@After
	public void tearDown() throws Exception {
		if(scenario.isFailed()) {
			scenario.attach(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES), "image/png", "Error Screenshot");
			
			driver.quit();
			driver=null;
		}
	}

	@Given("user opens the browser")
	public void userOpensTheBrowser() {
		driver.manage().window().maximize();
	}

	@When("user navigates to url")
	public void userNavigatesToURL() {
		driver.get(DBManager.testData.get("url"));
	}

	@When("click menu to provide login credentials")
	public void click_menu_to_provide_login_credentials() throws InterruptedException {
		lp.setLogin();
	}

	@Then("verify the page title")
	public void thePageTitleShouldContain(){
		scenario.log("Title : "+lp.verifyTitle(DBManager.testData.get("username")));
	}

}
