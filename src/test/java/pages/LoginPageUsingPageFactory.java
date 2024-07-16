package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class LoginPageUsingPageFactory {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "username")
    WebElement usernameEle;

    @FindBy(how = How.ID, using = "password")
    WebElement passwordEle;

    @FindBy(className = "login-button")
    WebElement loginButtonEle;

    @FindBy(how = How.CLASS_NAME, using = "error-message")
    WebElement errorMessageEle;

    public LoginPageUsingPageFactory(WebDriver driver){
        this.driver=driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String user) {
        usernameEle.sendKeys(user);
    }
    public void enterPassword(String pass) {
        passwordEle.sendKeys(pass);
    }
    public void clickLoginButton() {
        loginButtonEle.click();
    }

    public void loginToApplication(String username, String password){
        usernameEle.sendKeys(username);
        passwordEle.sendKeys(password);
        loginButtonEle.click();
    }
    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));
        return errorMessageEle.getText();
    }
}
