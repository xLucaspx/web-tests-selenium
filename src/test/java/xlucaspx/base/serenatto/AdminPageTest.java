package xlucaspx.base.serenatto;

import org.openqa.selenium.By;
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
		var dialog = wait.until(ExpectedConditions.alertIsPresent());
		var text = dialog.getText();

		dialog.dismiss();

		Assert.assertEquals(text, "Digite o novo tipo:");
	}

	@Test(testName = "Deve cadastrar um novo tipo")
	public void testAdmin03() {
		var page = new AdminPage(driver);

		page.clickNewType();
		var dialog = wait.until(ExpectedConditions.alertIsPresent());
		dialog.sendKeys("cerveja");
		dialog.accept();

		// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/main/section[3]/table/tbody/tr[6]")));
		driver.get(driver.getCurrentUrl());

		Assert.assertTrue(driver.getPageSource().contains("<td class=\"table__td\">cerveja</td>"));
	}

	@Test(testName = "Deve abrir o prompt para editar o tipo selecionado")
	public void testAdmin04() {
		var page = new AdminPage(driver);

		page.clickButton("/html/body/main/section[3]/table/tbody/tr[3]/td[4]/button");

		var dialog = wait.until(ExpectedConditions.alertIsPresent());
		var text = dialog.getText();
		dialog.dismiss();

		Assert.assertEquals(text, "Digite o novo nome para o tipo \"cerveja\"");
	}

	@Test(testName = "Deve editar o tipo selecionado")
	public void testAdmin05() {
		var page = new AdminPage(driver);

		page.clickButton("/html/body/main/section[3]/table/tbody/tr[3]/td[4]/button");

		var dialog = wait.until(ExpectedConditions.alertIsPresent());
		dialog.sendKeys("Cervejas");
		dialog.accept();

		driver.get(driver.getCurrentUrl());

		Assert.assertTrue(driver.getPageSource().contains("<td class=\"table__td\">Cervejas</td>"));
	}

	@Test(testName = "Deve abrir o prompt para deletar o tipo selecionado")
	public void testAdmin06() {
		var page = new AdminPage(driver);

		page.clickButton("/html/body/main/section[3]/table/tbody/tr[3]/td[5]/button");

		var dialog = wait.until(ExpectedConditions.alertIsPresent());
		var text = dialog.getText();
		dialog.dismiss();

		Assert.assertEquals(text, "Tem certeza que deseja excluir o tipo \"Cervejas\"?");
	}

	@Test(testName = "Deve deletar o tipo selecionado")
	public void testAdmin07() {
		var page = new AdminPage(driver);

		page.clickButton("/html/body/main/section[3]/table/tbody/tr[3]/td[5]/button");

		var dialog = wait.until(ExpectedConditions.alertIsPresent());
		dialog.accept();

		driver.get(driver.getCurrentUrl());

		Assert.assertFalse(page.getPageSource().contains("<td class=\"table__td\">Cervejas</td>"));
	}
}
