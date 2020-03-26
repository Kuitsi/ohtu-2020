package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";

    @Before
    public void setUp(){
        driver.get(baseUrl);
    }

    @Given("login is selected")
    public void loginIsSelected() {
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
    }

    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }

    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @When("incorrect username {string} and password {string} are given")
    public void incorrectUsernameAndAnyPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @Given("command new user is selected")
    public void newUserIsSelected() {
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void validUsernameAndPasswordPlusConfirmationAreGiven(String username, String password) {
        enterUserInformation(username, password, password);
    }

    @Then("a new user is created")
    public void newUserIsRedirectedToMainPage() {
        pageHasContent("Welcome to Ohtu Application!");
    }

    @When("an invalid username {string} and password {string} and matching password confirmation are entered")
    public void invalidUsernameAndValidPasswordPlusConfirmationsAreGiven(String username, String password) {
        enterUserInformation(username, password, password);
    }

    @Then("user is not created and error {string} is reported")
    public void retryNewUserWithErrorMessage(String errorMessage) {
        pageHasContent("Create username and give password");
        pageHasContent(errorMessage);
    }

    @When("a valid username {string} and invalid password {string} and matching password confirmation are entered")
    public void validUsernameAndInvalidPasswordPlusValidConfirmationsAreGiven(String username, String password) {
        enterUserInformation(username, password, password);
    }

    @When("a valid username {string} is entered but password confirmation {string} does not match valid password {string}")
    public void validUsernameAndMismatchingPasswordsAreGiven(String username, String passwordConfirmation, String password) {
        enterUserInformation(username, password, passwordConfirmation);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    /* helper methods */

    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void logInWith(String username, String password) {
        pageHasContent("Give your credentials to login");
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    private void enterUserInformation(String username, String password, String passwordConfirmation) {
        pageHasContent("Create username and give password");
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(passwordConfirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
}
