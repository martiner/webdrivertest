import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class FormTest {

	private static WebDriver driver;

	@BeforeClass
	public static void setUp() {
		FormTest.driver = new HtmlUnitDriver();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void shouldSendForm() throws Exception {
		// go to page
		driver.get("http://www.w3schools.com/html/html_forms.asp");
		assertThat(driver.getTitle(), is("HTML Forms"));

		// fill and submit the form
		final WebElement form = driver.findElement(By.xpath("//div[@class='w3-example']//form"));
		form.findElement(By.name("firstname")).sendKeys("Roman");
		form.findElement(By.name("lastname")).sendKeys("Stanek");

		form.submit();

		// validate confirm page
		assertThat(driver.getTitle(), is("Forms action page"));

		final WebElement element = driver.findElement(By.xpath("//div"));
		assertThat(element.getText(), allOf(containsString("Roman"), containsString("Stanek")));
	}
}
