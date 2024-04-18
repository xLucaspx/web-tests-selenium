package xlucaspx.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
	protected WebDriver driver;
	protected String url;

	public BaseTest(String url) {
		this.url = url;
	}

	@BeforeClass
	public void preCond() {
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
	}

	@AfterClass
	public void postCond() {
		// driver.close();
	}
}
