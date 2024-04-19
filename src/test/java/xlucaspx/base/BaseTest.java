package xlucaspx.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
	protected final WebDriver driver;
	protected String url;

	public BaseTest(String url) {
		driver = new ChromeDriver();
		this.url = url;
	}

	@BeforeClass
	public void preCond() {
		driver.get(url);
		// driver.manage().window().maximize();
	}

	@AfterClass
	public void postCond() {
		driver.close();
	}
}
