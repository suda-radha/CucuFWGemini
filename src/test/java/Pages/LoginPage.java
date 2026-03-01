package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	WebDriver driver;

	// 1. constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// 2. Selectors for Login page elements

	@FindBy(id = "username")
	private WebElement usernameField;

	@FindBy(id = "password")
	private WebElement passwordField;

	@FindBy(xpath = "//input[@value='user']")
	private WebElement userRadioButton;

	@FindBy(id = "okayBtn")
	private WebElement popUpOkayBtn; // Pop-up appears when 'User' is selected

	@FindBy(css = "select.form-control")
	private WebElement occupationDropdown;

	@FindBy(id = "terms")
	private WebElement termsCheckbox;

	@FindBy(id = "signInBtn")
	private WebElement signInBtn;

	// 3. Methods - actions on Login page

	public void enterCredentials(String user, String pass) {
		usernameField.sendKeys(user);
		passwordField.sendKeys(pass);
	}

	public void selectUserRole() {
		userRadioButton.click();
		// Wait for the dynamic pop-up to appear and click 'Okay'
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(popUpOkayBtn)).click();
	}

	public void selectOccupation(String occupation) {
		Select select = new Select(occupationDropdown);
		select.selectByVisibleText(occupation);
	}

	public void acceptTerms() {
		termsCheckbox.click();
	}

	public void clickSignIn() {
		signInBtn.click();
	}

}
