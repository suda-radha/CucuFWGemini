package StepDefinitions;

import java.io.IOException;
import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

	WebDriver driver = Hooks.driver;
	LoginPage loginPage = new LoginPage(driver);

//	// IMPORTANT CODE - REVISE AGAIN
//	@Given("I navigate to the login page")
//	public void i_navigate_to_the_login_page() {
//		WebDriverManager.chromedriver().setup();// IMPORTANT CODE - REVISE AGAIN
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("https://rahulshettyacademy.com/loginpagePractise/");
//		loginPage = new LoginPage(driver);
//	}

	@Given("I navigate to the login page")
	public void i_navigate_to_the_login_page() throws IOException {
		String url = Utils.ConfigReader.getGlobalValue("url");
		driver.get(url);
	}

	@When("I enter username {string} and password {string}")
	public void i_enter_username_and_password(String user, String pass) {
		loginPage.enterCredentials(user, pass);
	}

	@And("I select the {string} radio button")
	public void i_select_the_radio_button(String role) {
		if (role.equalsIgnoreCase("User")) {
			loginPage.selectUserRole();
		} else {
			// Logic for Admin (usually default or click admin radio)
			// loginPage.selectAdminRole(); // Add this to your POM if needed
		}
	}

	@And("I select {string} from the occupation dropdown")
	public void i_select_from_the_occupation_dropdown(String occupation) {
		loginPage.selectOccupation(occupation);
	}

	@And("I check the terms and conditions checkbox")
	public void i_check_the_checkbox() {
		loginPage.acceptTerms();
	}

	@And("I click the Sign In button")
	public void i_click_the_sign_in_button() {
		loginPage.clickSignIn();
	}

	@Then("I should be redirected to the product page")
	public void i_should_be_redirected_to_the_product_page() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlContains("shop"));
		Assert.assertTrue(driver.getCurrentUrl().contains("shop"));
	}

}
