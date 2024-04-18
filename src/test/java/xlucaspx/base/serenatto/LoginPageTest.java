package xlucaspx.base.serenatto;

import org.testng.Assert;
import org.testng.annotations.Test;
import xlucaspx.base.BaseTest;
import xlucaspx.pages.serenatto.LoginPage;

public class LoginPageTest extends BaseTest {
	private static final String EMAIL = "lucas@serenatto.com";
	private static final String PASSWORD = "#senhaLucas01";

	public LoginPageTest() {
		super("http://localhost:8080/login");
	}

	@Test(testName = "Testa login com e-mail inexistente")
	public void testLogin01() {
		var page = new LoginPage(driver);
		var loginPageSource = driver.getPageSource();

		page.fillEmail("invalid@email.com").fillPassword(PASSWORD).clickLogin();

		// credenciais incorretas redirecionam de volta para a página de login
		Assert.assertEquals(driver.getPageSource(), loginPageSource);
	}

	@Test(testName = "Testa login com senha incorreta")
	public void testLogin02() {
		var page = new LoginPage(driver);
		var loginPageSource = driver.getPageSource();

		page.fillEmail(EMAIL).fillPassword("#senha123").clickLogin();

		// credenciais incorretas redirecionam de volta para a página de login
		Assert.assertEquals(driver.getPageSource(), loginPageSource);
	}

	@Test(testName = "Testa login com credenciais corretas")
	public void testLogin03() {
		var page = new LoginPage(driver);

		page.fillEmail(EMAIL).fillPassword(PASSWORD).clickLogin();

		Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/admin");
		Assert.assertEquals(driver.getTitle(), "Serenatto - Admin");
	}
}
