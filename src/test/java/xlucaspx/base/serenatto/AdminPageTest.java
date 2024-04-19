package xlucaspx.base.serenatto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import xlucaspx.base.BaseTest;
import xlucaspx.pages.serenatto.AdminPage;
import xlucaspx.pages.serenatto.LoginPage;

import java.time.Duration;

public class AdminPageTest extends BaseTest {

	private static final String EMAIL = "lucas@serenatto.com";
	private static final String PASSWORD = "#senhaLucas01";

	private final Wait<WebDriver> wait;

	public AdminPageTest() {
		super("http://localhost:8080/admin");
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(2));
	}

	@BeforeClass
	public void login() {
		var page = new LoginPage(driver);
		page.fillEmail(EMAIL).fillPassword(PASSWORD).clickLogin();
	}

	@BeforeMethod
	public void goToAdminPage() {
		driver.get(url);
	}

	@Test(testName = "Deve redirecionar para a página de cadastro de produto")
	public void testAdmin01() {
		var page = new AdminPage(driver);

		page.clickNewProduct();
		var url = driver.getCurrentUrl();
		var title = driver.getTitle();

		Assert.assertEquals(url, "http://localhost:8080/novo-produto");
		Assert.assertEquals(title, "Cadastrar produto");
	}

	@Test(testName = "Deve abrir a caixa para cadastro de tipo")
	public void testAdmin02() {
		var page = new AdminPage(driver);

		page.clickNewType();
		var confirm = wait.until(ExpectedConditions.alertIsPresent());
		var text = confirm.getText();

		confirm.dismiss();

		Assert.assertEquals(text, "Digite o novo tipo:");
	}
}
