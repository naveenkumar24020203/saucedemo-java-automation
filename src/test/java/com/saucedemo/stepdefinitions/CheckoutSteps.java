package com.saucedemo.stepdefinitions;

import com.saucedemo.pages.CheckoutOverviewPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.OrderConfirmationPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.utils.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CheckoutSteps {
    private ProductsPage productsPage() {
        return new ProductsPage(DriverManager.getDriver());
    }

    private CheckoutPage checkoutPage() {
        return new CheckoutPage(DriverManager.getDriver());
    }

    private CheckoutOverviewPage overviewPage() {
        return new CheckoutOverviewPage(DriverManager.getDriver());
    }

    private OrderConfirmationPage orderConfirmationPage() {
        return new OrderConfirmationPage(DriverManager.getDriver());
    }

    private com.saucedemo.pages.CartPage cartPage() {
        return new com.saucedemo.pages.CartPage(DriverManager.getDriver());
    }

    @When("I checkout with {string} {string} {string}")
    public void iCheckoutWith(String firstName, String lastName, String postalCode) {
        productsPage().openCart();
        cartPage().clickCheckout();
        checkoutPage().enterCheckoutInformation(firstName, lastName, postalCode);
    }

    @And("I finish the purchase")
    public void iFinishThePurchase() {
        overviewPage().clickFinish();
    }

    @Then("I should see the order confirmation")
    public void iShouldSeeTheOrderConfirmation() {
    Assert.assertTrue(orderConfirmationPage().getConfirmationHeader().equalsIgnoreCase("THANK YOU FOR YOUR ORDER!"),"Order confirmation header mismatch");
    }


    @Then("I should see checkout error {string}")
    public void iShouldSeeCheckoutError(String expectedMessage) {
        String actual = checkoutPage().getErrorMessage();
        Assert.assertTrue(actual != null && actual.toLowerCase().contains(expectedMessage.toLowerCase()),
                "Expected checkout error to contain: '" + expectedMessage + "' but was: '" + actual + "'");
    }
}
