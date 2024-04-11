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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;


public class stepdefinition {

    static WebDriver driver;

    @Given("user is on {string} website")
    public void userIsOnWebsite(String site) {
        driver = DriverFactory.getDriver();
        switch(site.toLowerCase()) {
            case "swag labs":
                driver.get("https://www.saucedemo.com");
                break;
            case "para bank":
                driver.get("https://parabank.parasoft.com/parabank/index.htm");
                break;
            default:
                break;
        }
    }

    @And("Website finishes loading")
    public void websiteFinishesLoading() throws InterruptedException {
        Thread.sleep(2000);

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

    @Then("user clicks on {string} link")
    public void userLogsClicksOn(String element) {
        driver.findElement(By.linkText(element)).click();
    }

    @And("user enters their data")
    public void userEntersTheirData() {
        driver.findElement(By.id("customer.firstName")).sendKeys("John");
        driver.findElement(By.id("customer.lastName")).sendKeys("Doe");
        driver.findElement(By.id("customer.address.street")).sendKeys("101 E. Huntington Drive");
        driver.findElement(By.id("customer.address.city")).sendKeys("Monrovia");
        driver.findElement(By.id("customer.address.state")).sendKeys("California");
        driver.findElement(By.id("customer.address.zipCode")).sendKeys("91016");
        driver.findElement(By.id("customer.phoneNumber")).sendKeys("+1 888 305 0041");
        driver.findElement(By.id("customer.ssn")).sendKeys("275-19-0041");
    }

    @Then("user enters their username and password")
    public void userAddsTheirUsernameAndPassword() {
        driver.findElement(By.id("customer.username")).sendKeys("upToNoGood");
        driver.findElement(By.id("customer.password")).sendKeys("m!schief_Manag3d");

    }

    @And("user clicks on {string} button")
    public void userClicksOnButton(String button) throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@value='" +button+ "']")).click();
    }

    @Then("user is logged in")
    public void userIsLoggedIn() {
        WebElement successMsg = driver.findElement(By.xpath("//p[contains(text(), 'Your account was created successfully. You are now logged in.')]"));
        Assert.assertTrue(successMsg.isDisplayed());
    }

    @And("user confirms their password")
    public void userConfirmsTheirPassword() {
        driver.findElement(By.id("repeatedPassword")).sendKeys("m!schief_Manag3d");
    }

    @Then("user logs in to their bank account")
    public void userLogsInToTheirBankAccount() {
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("upToNoGood");
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("//input[@name='password']")));
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("m!schief_Manag3d");
    }

    @Then("check if user is logged in")
    public void checkIfUserIsLoggedIn() {
        WebElement homeBtn = driver.findElement(By.xpath("//a[contains(text(),'home')]"));
        Assert.assertTrue(homeBtn.isDisplayed());
    }

    @And("account is created")
    public void accountIsCreated() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Congratulations, your account is now open.')]")));
        WebElement successMsg = driver.findElement(By.xpath("//p[contains(text(),'Congratulations, your account is now open.')]"));
        Assert.assertTrue(successMsg.isDisplayed());
    }

    @Then("user selects {string} type")
    public void userSelectsType(String accType) {
        driver.findElement(By.xpath("//select[@id='type']")).click();
        switch(accType.toLowerCase()) {
            case "checking":
                driver.findElement(By.xpath("//option[@value='0']")).click();
                break;
            case "savings":
                driver.findElement(By.xpath("//option[@value='1']")).click();
        }
    }


    @And("user enters amount to be transferred")
    public void userEntersAmountToBeTransferred() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name='input']")).sendKeys("10");
    }

    @Then("user checks if money was transferred")
    public void userChecksIfMoneyWasTransferred() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Transfer Complete')]")));
        WebElement successMsg =  driver.findElement(By.xpath("//h1[contains(text(),'Transfer Complete')]"));
        Assert.assertTrue(successMsg.isDisplayed());
    }

    @And("user finds transaction by today's date")
    public void userFindsTransanctionByTodaySDate() {
        LocalDate todaysDate = LocalDate.now();
        driver.findElement(By.id("criteria.onDate")).sendKeys(todaysDate.format(DateTimeFormatter.ofPattern("MM-dd-yyy")));
        List<WebElement> buttons = driver.findElements(By.xpath("//button[@class='button']"));
        buttons.get(1).click();
    }

    @Then("user checks if a Transaction was made")
    public void userChecksIfATransactionWasMade() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Transaction Results')]")));
        WebElement successMsg = driver.findElement(By.xpath("//h1[contains(text(),'Transaction Results')]"));
        Assert.assertTrue(successMsg.isDisplayed());
    }

    @And("user verifies if they logged out")
    public void userVerifiesIfTheyLoggedOut() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
        WebElement logIn =  driver.findElement(By.xpath("//input[@name='username']"));
        WebElement passWord = driver.findElement(By.xpath("//input[@name='password']"));
        Assert.assertTrue(logIn.isDisplayed());
        Assert.assertTrue(passWord.isDisplayed());
    }

    @And("user updates their contact info")
    public void userUpdatesTheirContactInfo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customer.firstName")));
        WebElement firstName = driver.findElement(By.id("customer.firstName"));
        WebElement lastName = driver.findElement(By.id("customer.lastName"));
        WebElement city = driver.findElement(By.id("customer.address.city"));

        firstName.clear();
        firstName.sendKeys("Bruce");
        lastName.clear();
        lastName.sendKeys("Wayne");
        city.clear();
        city.sendKeys("Gotham");
    }

    @Then("user verifies if contact info was updated")
    public void userVerifiesIfContactInfoWasUpdated() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Profile Updated')]")));
        WebElement successMsg = driver.findElement(By.xpath("//p[contains(text(),'Your updated address and phone number have been added to the system. ')]"));
        Assert.assertTrue(successMsg.isDisplayed());
    }
}
