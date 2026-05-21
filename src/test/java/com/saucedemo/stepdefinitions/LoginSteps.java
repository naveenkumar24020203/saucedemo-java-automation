package com.saucedemo.stepdefinitions;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.utils.ConfigReader;
import com.saucedemo.utils.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {
    private LoginPage loginPage() {
        return new LoginPage(DriverManager.getDriver());
    }

    private ProductsPage productsPage() {
        return new ProductsPage(DriverManager.getDriver());
    }

    @Given("I open the SauceDemo login page")
    public void iOpenTheSauceDemoLoginPage() {
        loginPage().openUrl(ConfigReader.get("BASE_URL", "https://www.saucedemo.com"));
        loginPage().isLoginButtonDisplayed();
        loginPage().isLoginFormDisplayed();
    }

    @When("I login with valid credentials")
    public void iLoginWithValidCredentials() {
        loginPage().login(ConfigReader.get("USERNAME"), ConfigReader.get("PASSWORD"));
    }

    @Then("I should see the products page")
    public void iShouldSeeTheProductsPage() {
        Assert.assertEquals(productsPage().getPageTitle(), "Products", "Products page title should be displayed");
    }

    @Then("I should see an error message {string}")
    public void iShouldSeeAnErrorMessage(String expectedMessage) {
    Assert.assertTrue(loginPage().getErrorMessage().toLowerCase().contains(expectedMessage.toLowerCase()),"Error message mismatch");
}

    @When("I login with username {string} and password {string}")
    public void i_login_with_username_and_password(String string, String string2) {
            loginPage().login(string, string2);
}
    @When("I logout from the application")
    public void i_logout_from_the_application() {
        productsPage().openMenu();
        productsPage().clickLogOut();
        loginPage().isLoginButtonDisplayed();
        loginPage().isLoginFormDisplayed();
    }
    @Then("I should see the login page")
    public void i_should_see_the_login_page() {
        loginPage().isLoginButtonDisplayed();
        loginPage().isLoginFormDisplayed();
    }

}

