package xlucaspx.pages.serenatto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import xlucaspx.pages.BasePage;

public class LoginPage extends BasePage {

	@FindBy(id = "email")
	private WebElement inputEmail;
	@FindBy(id = "password")
	private WebElement inputPassword;
	@FindBy(xpath = "/html/body/main/section[2]/form/button")
	private WebElement loginButton;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public LoginPage fillEmail(String email) {
		inputEmail.sendKeys(email);
		return this;
	}

	public LoginPage fillPassword(String password) {
		inputPassword.sendKeys(password);
		return this;
	}

	public LoginPage clickLogin() {
		loginButton.click();
		return this;
	}
}
