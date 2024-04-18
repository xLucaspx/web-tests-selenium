package xlucaspx.pages.theory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import xlucaspx.pages.BasePage;

public class GooglePage extends BasePage {

	@FindBy(id = "APjFqb")
	private WebElement searchBox;

	public GooglePage(WebDriver driver) {
		super(driver);
	}

	public GooglePage search(String text) {
		searchBox.sendKeys(text, Keys.ENTER);
		return this;
	}

	public String getSearchResult() {
		return driver.getPageSource();
	}
}
