import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


import org.testng.annotations.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import org.testng.Assert;
import pages.LoginPageUsingPageFactory;

public class LoginPageTest {

    public WebDriver driver;
    LoginPageUsingPageFactory loginPageUsingPageFactory;


    @DataProvider
    public Object[][] loginData() {
        return new Object[][] {
                { "praneetha", "praneetha@2021" },
                { "rahul", "rahul@2021" }

        };
    }

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "D:\\sspto\\Downloads1\\Softwares\\Chrome Driver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://rahulnxttrendz.ccbp.tech/login");
        loginPageUsingPageFactory = new LoginPageUsingPageFactory(driver);

    }


    @Test(priority = 1, dataProvider = "loginData")
    public void loginWithValidCredentials(String username, String password) {
        loginPageUsingPageFactory.loginToApplication(username, password);
        String expectedUrl = "https://rahulnxttrendz.ccbp.tech/";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, currentUrl, "URLs do not match");
    }



    @Test(priority = 2)
    public void loginWithInvalidCredentials() {
        loginPageUsingPageFactory.loginToApplication("rahul", "raahul2021");
        String errorMessage = loginPageUsingPageFactory.getErrorMessage();
        Assert.assertEquals(errorMessage, "*username and password didn't match");
    }

    @AfterMethod
    public void tearDown() {

        driver.close();
    }
}
