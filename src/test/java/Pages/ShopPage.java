package Pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShopPage {

	WebDriver driver;

	public ShopPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locators
	@FindBy(css = "app-card")
	private List<WebElement> productCards;

	@FindBy(css = "a.nav-link.btn-primary")
	private WebElement primaryCheckoutBtn;

	@FindBy(css = "button.btn-success")
	private WebElement finalCheckoutBtn;

	@FindBy(css = "h4.media-heading a")
	private List<WebElement> cartProductTitles;

	@FindBy(xpath = "//a[contains(text(),'ProtoCommerce Home')]")
	private WebElement navHomeHeader;

	// Methods

	/**
	 * Iterates through all product cards, finds the one with the matching title,
	 * and clicks its "Add" button.
	 */
	public void addProductToCart(String productName) {
		for (WebElement card : productCards) {
			String title = card.findElement(By.cssSelector("h4.card-title")).getText();
			if (title.equalsIgnoreCase(productName)) {
				card.findElement(By.cssSelector("button.btn-info")).click();
				break;
			}
		}
	}

	/**
	 * Extracts the number from the checkout button text: "Checkout ( 1 )"
	 */
	public String getCartCount() {
		String text = primaryCheckoutBtn.getText(); // e.g., "Checkout ( 1 )"
		return text.replaceAll("[^0-9]", ""); // Returns only "1"
	}

//	public void clickPrimaryCheckout() {
//		primaryCheckoutBtn.click();
//	}
	public void clickPrimaryCheckout() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    try {
	        // Wait for the button to be clickable
	        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(primaryCheckoutBtn));
	        
	        // Scroll it into view just in case it's at the very edge of the screen
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutBtn);
	        
	        checkoutBtn.click();
	    } catch (Exception e) {
	        // If standard click fails or times out, force it with JavaScript
	        System.out.println("Standard click failed, attempting JS click on Checkout button");
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", primaryCheckoutBtn);
	    }
	}

	public void clickFinalCheckout() {
		finalCheckoutBtn.click();
	}

	/**
	 * Returns a list of all product names currently in the checkout summary table
	 */
	public List<String> getCheckoutItemsList() {
		return cartProductTitles.stream().map(WebElement::getText).collect(Collectors.toList());
	}

	public String getNavHeaderText() {
		return navHomeHeader.getText();
	}

}
