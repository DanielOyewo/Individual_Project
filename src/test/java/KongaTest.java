import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class KongaTest {
    private By ClickCard = By.xpath("//*[@id=\\\"channel-template\\\"]/div[2]/div/div[2]/button/div/span[1]");
    private By CardNumber = By.id("card-number");
    private By Date = By.id("expiry");
    private By CVV = By.id("cvv");
    private By Submit = By.id("validateCardForm");
    private By Close = By.xpath("/html/body/section/section/section/div[2]/div[1]/aside");

    //import the selenium WebDriver
    private WebDriver driver;

    @BeforeTest
    public void Begin() throws InterruptedException {
        //locate the WebDriver
        System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver.exe");
        //1. open Edge browser
        driver = new EdgeDriver();
        //2. input the konga page url (https://www.konga.com/)
        driver.get("https://www.konga.com/");
        //Test 1.Verify the user input the right url and is on the right webpage
        if (driver.getCurrentUrl().contains("https://www.konga.com/"))
            //pass
            System.out.println("current webpage");
        else
            //fail
            System.out.println("wrong webpage");
        Thread.sleep(5000);
        //3. maximize the browser
        driver.manage().window().maximize();
        //4. click on login/signup button to open login page
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/div[4]/a")).click();
        //Test 2. Verify that when user clicks on the login/signup button, the user is directed to the login page
        String expectedTitle = "Login";
        String actualTitle = driver.getTitle();
        if (expectedTitle.contains(actualTitle))
            //pass
            System.out.println("correct webpage");
        else
            //fail
            System.out.println("wrong webpage");
        Thread.sleep(5000);
    }


    @Test(priority = 1)
    public void InvalidUsernameLogin() throws InterruptedException {
        //Test 3. Verify that user cannot log in with an invalid Username
        //5. input invalid email in the email field
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("mo1@mailnator");
        //6. input valid password in the password field
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("admin123");
        //7. click on the login button
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();
        //8. verify that an error message (The username or password you have entered is incorrect. Please try again.) is displayed
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[1]/div")).isDisplayed();
        Thread.sleep(5000);

    }

    @Test(priority = 2)
    public void WrongPasswordLogin() throws InterruptedException {
        //Test 4. Verify that user cannot log in with wrong password
        //9. clear the email field
        driver.findElement(By.xpath("//*[@id=\"username\"]")).clear();
        //10. clear the password field
        driver.findElement(By.xpath("//*[@id=\"password\"]")).clear();
        Thread.sleep(5000);
        //11. input valid email in the email field
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("moyosore01@mailnator.com");
        //12. input wrong password in the password field
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("admin12345");
        //13. click on the login button
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();
        //14. verify that an error message (The username or password you have entered is incorrect. Please try again.) is displayed
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[1]/div")).isDisplayed();
        Thread.sleep(5000);

    }

    @Test(priority = 3)
    public void LessThanSixCharactersPasswordLogin() throws InterruptedException {
        //Test 5. verify that user  cannot log in with less than six characters password
        //15. clear the email field
        driver.findElement(By.xpath("//*[@id=\"username\"]")).clear();
        //16. clear the password field
        driver.findElement(By.xpath("//*[@id=\"password\"]")).clear();
        Thread.sleep(5000);
        //17. input valid email in the email field
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("moyosore01@mailnator.com");
        //18. input less than six characters password in the password field
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("admin");
        //19. click on the login button
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();
        //20. verify that an error message (The password must be at least 6 characters) is displayed
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[2]/div")).isDisplayed();
        Thread.sleep(5000);

    }

    @Test(priority = 4)
    public void BlankEmailFieldLogin() throws InterruptedException {
        //Test 6. verify that user cannot log in with a blank email field
        //21. clear the email field
        driver.findElement(By.xpath("//*[@id=\"username\"]")).clear();
        //22 clear the password field
        driver.findElement(By.xpath("//*[@id=\"password\"]")).clear();
        Thread.sleep(5000);
        //23. input valid password in the password field
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("admin123");
        //24. click on the login button
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();
        Thread.sleep(5000);

    }

    @Test(priority = 5)
    public void BlankPasswordFieldLogin() throws InterruptedException {
        //Test 7. verify that user cannot log in with a blank password field
        //25. clear the email field
        driver.findElement(By.xpath("//*[@id=\"username\"]")).clear();
        //26. clear the password field
        driver.findElement(By.xpath("//*[@id=\"password\"]")).clear();
        Thread.sleep(5000);
        //27. input valid email in the email field
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("moyosore01@mailnator.com");
        //28. click on the login button
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();
        Thread.sleep(5000);
    }

    @Test(priority = 6)
    public void BlankRequiredFieldLogin() throws InterruptedException {
        //Test 8. verify that user cannot log in with a blank required field
        //29. clear the email field
        driver.findElement(By.xpath("//*[@id=\"username\"]")).clear();
        //30. clear the password field
        driver.findElement(By.xpath("//*[@id=\"password\"]")).clear();
        //31. click on the login button
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();
        Thread.sleep(5000);

    }

    @Test(priority = 7)
    public void positiveLogin() throws InterruptedException {
        //Test 9. verify that user can log in with a valid details
        //32. input email in the email field
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("moyosore01@mailnator.com");
        //33. input password in the password filed
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("admin123");
        //34. click on the login button
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();
        Thread.sleep(5000);

    }

    @Test(priority = 8)
    public void ClickOnComputerAndAccessories() throws InterruptedException {
        //Test10.verify that the user can successfully navigate and click computer and accessories
        //35. click on the computer and accessories
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[2]/div/a[2]")).click();
        Thread.sleep(15000);

    }

    @Test(priority = 9)
    public void ClickOnLaptop() throws InterruptedException {
        //verify that the user can navigate and click on laptop
        //36. click on laptop on the item list
        driver.findElement(By.xpath("/html/body/div[1]/div/section/div[3]/section/main/section[3]/section/div/section/div[2]/div[2]/ul/li[3]/a/label/span")).click();
        Thread.sleep(15000);

    }

    @Test(priority = 10)
    public void ClickOnAppleMacBooks() throws InterruptedException {
        //verify that the user can navigate and click on Apple Macbooks
        //37. click on the Apple Macbooks
        driver.findElement(By.xpath("/html/body/div[1]/div/section/div[3]/section/main/section[3]/section/div/section/div[2]/div[2]/ul/li/a/ul/li[1]/a/label")).click();
        Thread.sleep(10000);

    }

    @Test(priority = 11)
    public void AddItemToTheCart() throws InterruptedException {
        //verify that user can navigate and click on item and add it to the cart
        //38. click on item
        driver.findElement(By.xpath("/html/body/div[1]/div/section/div[3]/section/main/section[3]/section/section/section/section/ul/li[6]/div/div/div[2]/a/div[1]/h3")).click();
        Thread.sleep(5000);
        //39. click on buy now to add to cart
        driver.findElement(By.xpath("/html/body/div[1]/div/section/div[3]/section/main/div[4]/div[3]/div[1]/div[2]/div[2]/form/div[5]/div[1]/button")).click();
        Thread.sleep(5000);

    }
    @Test(priority = 12)
    public void Payment() throws InterruptedException {
        //40. click on continue checkout
        driver.findElement(By.xpath("/html/body/div[1]/div/section/div[3]/section/main/div/div[2]/div[4]/div/div[6]/div/a")).click();
        Thread.sleep(5000);
    } 
    @Test(priority = 13)
    public void PaymentOption() throws InterruptedException {
         //51. click on "pay now" to make payment
        driver.findElement(By.xpath("/html/body/div[1]/div/section/div[2]/section/main/div/form/div/div[1]/section[2]/div/div[2]/div[1]/div[1]/span/input")).click();
        //52. click on "continue to pay" to place order
        driver.findElement(By.xpath("/html/body/div[1]/div/section/div[2]/section/main/div/form/div/div[1]/section[2]/div/div[2]/div[3]/div[2]/div/button")).click();
        Thread.sleep(5000);

    }

    @Test(priority = 14)
    public void SelectPaymentMethod() throws InterruptedException{
        //53. from the select payment method, click on the card option
        click(ClickCard);
    }

    private void click(By clickCard) {
    }
    @Test (priority = 15)
    public void EnterCardDetails() throws InterruptedException{
        //54. input an invalid card number in the card field
        setText(CardNumber,119518);
        //55. input an invalid expiry date in the date field
        setText(Date,8/26);
        //56. input an invalid cvv number in the cvv field
        setText(CVV, 245);

    }
    public void setText(By cardNumber, int i) {

    }
    @Test (priority = 16)
    public void PayNow ()throws InterruptedException{
        //57 click on the pay now button
        click(Submit);
        Thread.sleep(5000);
        //58 close the iFrame displayed
        click(Close);
        Thread.sleep(5000);
        //verify that user is unable to complete order with invalid card details
        String actualErrorMessage = "Invalid card number";
        if (actualErrorMessage.contains("Invalid card number"))
            //pass
            System.out.println("Invalid card number");
        else
            //fail
            System.out.println("successful");
        Thread.sleep(5000);

    }



    @AfterTest
    public void CloseBrowser() {
        //Quit the browser
        driver.quit();

    }

}