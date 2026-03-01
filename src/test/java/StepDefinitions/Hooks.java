package StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

public class Hooks {
	public static WebDriver driver;// Static so it can be shared

	@Before
	public void setup() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		// Read the "browser" property from the Maven command line (-Dbrowser=headless)
		String browserMode = System.getProperty("browser", "gui");

		if (browserMode.equalsIgnoreCase("headless")) {
			options.addArguments("--headless=new"); // Runs without a UI
			options.addArguments("--no-sandbox"); // Required for Linux CI environments
			options.addArguments("--disable-dev-shm-usage"); // Overcomes limited resource problems
			options.addArguments("--window-size=1920,1080"); // Set a consistent screen size
		}

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();

	}

	@After
	public void teardown(Scenario scenario) {

		// I recommend keeping this enabled!
		// In GitHub Actions, you can't "see" the failure, so screenshots are vital.
		// 1. Existing logic: Embed screenshots in HTML report
		if (scenario.isFailed() && driver != null) {
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "Failed_Screenshot_" + scenario.getName());
		}
		// 2. Optional: Save as a physical file on your hard drive
		try {
			File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File destinationPath = new File("target/screenshots/" + scenario.getName() + ".png");
			FileUtils.copyFile(sourcePath, destinationPath);
			System.out.println("Screenshot saved to: " + destinationPath.getAbsolutePath());
		} catch (Exception e) {
			System.out.println("Failed to save physical screenshot: " + e.getMessage());
		}

		if (driver != null) {
			driver.quit();
		}
	}

	

}
