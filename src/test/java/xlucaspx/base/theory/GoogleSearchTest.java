package xlucaspx.base.theory;

import org.testng.Assert;
import org.testng.annotations.Test;
import xlucaspx.base.BaseTest;
import xlucaspx.pages.theory.GooglePage;

public class GoogleSearchTest extends BaseTest {

	public GoogleSearchTest(String url) {
		super("https://google.com.br");
	}

	@Test(testName = "Testa pesquisa no Google")
	public void testSearch01() {
		var page = new GooglePage(driver);

		page.search("Chess Online");

		var result = page.getSearchResult();
		Assert.assertTrue(result.contains("Aproximadamente "));
	}
}
