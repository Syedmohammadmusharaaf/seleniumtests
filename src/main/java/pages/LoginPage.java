package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    @FindBy(id = "Email")
    WebElement email;

    @FindBy(id = "Password")
    WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement signInButton;
//    private By email = By.id("Email");
//    private By password = By.id("Password");
//    private By signInButton = By.xpath("//button[@type='submit']");

    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void setEmail(String Email){
//        driver.findElement(email).sendKeys(Email);
        email.clear();
        email.sendKeys(Email);
    }

    public void setPassword(String pwd){
//        driver.findElement(password).sendKeys(pwd);
        password.clear();
        password.sendKeys(pwd);
    }
    public void clickOnSignInButton(){
//        driver.findElement(signInButton).click();
        signInButton.click();
    }




}
