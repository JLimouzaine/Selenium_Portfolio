package stepdefinitions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utils.DriverFactory;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.testng.AssertJUnit.assertTrue;


public class NavigationSteps {

    static WebDriver driver;

    @Given("user is on {string} website")
    public void userIsOnWebsite(String site) {
        driver = DriverFactory.getDriver();
        switch(site.toLowerCase()) {
            case "swag labs":
                driver.get("https://www.saucedemo.com");
                break;
            case "the internet":
                driver.get("https://the-internet.herokuapp.com");
                break;
            default:
                break;
        }
    }

    @And("Website finishes loading")
    public void websiteFinishesLoading() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login_logo")));
    }


    @Then("user logs in as {string}")
    public void userLogsInAsUser(String user) {
        String pwd = "secret_sauce";
        driver.findElement(By.id("user-name")).sendKeys(user);
        driver.findElement(By.id("password")).sendKeys(pwd);
        driver.findElement(By.xpath("//input[@id='login-button']")).click();

    }

    @Then("user is redirected to products page")
    public void userIsRedirectedToProductsPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[contains(@class, 'shopping_cart_link')]")));
    }

    @Then("check if user is locked")
    public void checkIfUserIsLocked() {
        WebElement errorMessage = driver.findElement(By.xpath("//h3[contains(text(), 'Sorry, this user has been locked out')]"));
        assertTrue("The error message is displayed", errorMessage.isDisplayed());
    }

    @And("user adds {string} items to cart")
    public void userAddsItemsToCart(String items) {
        switch(items.toLowerCase()) {
            case "miscellaneous":
                driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
                driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bike-light']")).click();
                break;
            case "tops":
                driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']")).click();
                driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-fleece-jacket']")).click();
                driver.findElement(By.linkText("Test.allTheThings() T-Shirt (Red)")).click();
                driver.findElement(By.id("add-to-cart")).click();
                break;
            case "baby":
                driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-onesie']")).click();
                break;

        }
    }

    @Then("check if {string} items are on cart")
    public void checkIfItemsAreOnCart(String count) {
        int item = Integer.parseInt(count);
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

        switch(item) {
            case 2:
                WebElement backpack = driver.findElement(By.xpath("//div[contains(text(),'Sauce Labs Backpack')]"));
                WebElement bikeLight = driver.findElement(By.xpath("//div[contains(text(),'Sauce Labs Bike Light')]"));
                Assert.assertTrue(backpack.isDisplayed());
                Assert.assertTrue(bikeLight.isDisplayed());
                break;

            case 3:
                WebElement tshirt = driver.findElement(By.xpath("//div[contains(text(),'Sauce Labs Bolt T-Shirt')]"));
                WebElement fleece = driver.findElement(By.xpath("//div[contains(text(),'Sauce Labs Fleece Jacket')]"));
                WebElement red = driver.findElement(By.xpath("//div[contains(text(),'Test.allTheThings')]"));
                Assert.assertTrue(tshirt.isDisplayed());
                Assert.assertTrue(fleece.isDisplayed());
                Assert.assertTrue(red.isDisplayed());
                break;

            case 1:
                WebElement onesie = driver.findElement(By.xpath("//div[contains(text(),'Sauce Labs Onesie')]"));
                Assert.assertTrue(onesie.isDisplayed());
                break;

        }
    }

    @And("user logs out")
    public void userLogsOut() {
        driver.findElement(By.xpath("//button[contains(text(),'Open Menu')]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Logout')]")));
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
        WebElement loginButton = driver.findElement(By.xpath("//input[@id='login-button']"));
        Assert.assertTrue(loginButton.isDisplayed());
    }
}
