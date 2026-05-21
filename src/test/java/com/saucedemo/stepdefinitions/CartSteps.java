package com.saucedemo.stepdefinitions;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.utils.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CartSteps {
    private ProductsPage productsPage() {
        return new ProductsPage(DriverManager.getDriver());
    }

    private CartPage cartPage() {
        return new CartPage(DriverManager.getDriver());
    }

    @When("I add {string} to cart")
    public void iAddToCart(String productName) {
        productsPage().addProductToCart(productName);
    }

    @And("I open the cart")
    public void iOpenTheCart() {
        productsPage().openCart();
    }

    @Then("I should see {string} in the cart")
    public void iShouldSeeInTheCart(String productName) {
        Assert.assertTrue(cartPage().isProductInCart(productName), "Product should be present in cart");
    }

    @And("I remove {string} from cart")
    public void iRemoveFromCart(String productName) {
        cartPage().removeItemFromCart(productName);
    }

    @Then("I should not see {string} in the cart")
    public void iShouldNotSeeInTheCart(String productName) {
        Assert.assertFalse(cartPage().isProductInCart(productName), "Product should have been removed from cart");
    }

    @Then("cart badge count should be {string}")
    public void cartBadgeCountShouldBe(String expectedCount) {
        int actualCount = productsPage().getCartBadgeCount();
        int expected = Integer.parseInt(expectedCount);
        Assert.assertEquals(actualCount, expected, "Cart badge count mismatch");
    }
}
