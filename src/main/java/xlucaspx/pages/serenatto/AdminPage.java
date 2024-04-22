package xlucaspx.pages.serenatto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import xlucaspx.pages.BasePage;

public class AdminPage extends BasePage {

	private final Actions actions;

	@FindBy(xpath = "/html/body/main/section[2]/a")
	private WebElement newProductButton;
	@FindBy(className = "new-type-button")
	private WebElement newTypeButton;

	public AdminPage(WebDriver driver) {
		super(driver);
		this.actions = new Actions(driver);
	}

	public AdminPage clickButton(String xpath) {
		var btn = driver.findElement(By.xpath(xpath));
		actions.moveToElement(btn).click().perform();
		return this;
	}

	public AdminPage clickNewProduct() {
		actions.moveToElement(newProductButton).click().perform();
		return this;
	}

	public AdminPage clickNewType() {
		actions.moveToElement(newTypeButton).click().perform();
		return this;
	}

	public String getPageSource() {
		return driver.getPageSource();
	}
}
