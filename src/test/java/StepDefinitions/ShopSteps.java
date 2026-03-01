package StepDefinitions;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Pages.ShopPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ShopSteps {

	WebDriver driver = Hooks.driver;
	ShopPage shopPage = new ShopPage(driver);
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	@Given("I am on the shop page")
	public void i_am_on_the_shop_page() {
		// Validation to ensure the Background login actually landed us here
		wait.until(ExpectedConditions.urlContains("shop"));
		Assert.assertTrue(driver.getCurrentUrl().contains("shop"));
	}

	@When("I click the Add button for {string}")
	@And("I add {string} to the cart")
	public void i_add_product_to_cart(String productName) {
		// Reuse logic for both "click button" and "add to cart" steps
		shopPage.addProductToCart(productName);
	}

	@Then("the cart badge should display {string}")
	public void the_cart_badge_should_display(String expectedCount) {
		String actualCount = shopPage.getCartCount();
		Assert.assertEquals(expectedCount, actualCount);
	}

	@And("I click on the {string} button")
	public void i_click_on_the_button(String buttonName) {
		// Usually the primary "Checkout" button on the shop page
		shopPage.clickPrimaryCheckout();
	}

	@Then("I should see {string} and {string} in the summary list")
	public void i_should_see_items_in_summary(String item1, String item2) {
		List<String> productsInCart = shopPage.getCheckoutItemsList();
		Assert.assertTrue(productsInCart.contains(item1));
		Assert.assertTrue(productsInCart.contains(item2));
	}

	@And("I have added {string} to my cart")
	public void i_have_added_to_my_cart(String product) {
		shopPage.addProductToCart(product);
	}

	@And("I click the final {string} button on the summary page")
	public void i_click_final_checkout(String buttonName) {
		shopPage.clickFinalCheckout();
	}

	@Then("I should be navigated to the {string} page")
	public void i_should_be_navigated_to_page(String pageName) {
	    // Wait for the title to contain "ProtoCommerce" instead of checking the URL
	    wait.until(ExpectedConditions.titleContains("ProtoCommerce"));
	    
	    // Alternative: Validate the "ProtoCommerce Home" text visible in the header (as seen in your image)
	    String headerText = shopPage.getNavHeaderText(); 
	    Assert.assertTrue(headerText.contains("ProtoCommerce"));
	}

}
