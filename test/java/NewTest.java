
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class NewTest {
	WebDriver driver;
	String Url;
	Map<String, String> Full_company_list = new HashMap<String, String>();
	// System.setProperty("webdriver.chrome.driver",
	// "src/main/resources/chromedriver.exe");

	// Initializing drivers and getting site data
	@BeforeSuite
	public void Initialise() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		driver = new ChromeDriver(options);
		Url = "https://arnvald.github.io/cgl-data-exercise/";
		driver.get(Url);
		List<WebElement> AllCompany_Details = Object_LPage.Company_Details(driver);
		List<WebElement> AllCompany_Names = Object_LPage.Company_Name(driver);
		Assert.assertEquals(AllCompany_Details.size(), AllCompany_Names.size());

		for (int i = 0; i < AllCompany_Details.size(); i++)// getting Company details in a hash map
			Full_company_list.put(AllCompany_Names.get(i).getText(), AllCompany_Details.get(i).getText());
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.close();
	}

	// Test start

	@Test(dataProvider = "data-provider", dataProviderClass = DataProviderClass.class)
	public void LandingPage(String name_filter, String industry_filter, String sort_filter, String expected_no_comp,
			String expected_output) throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 100);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		driver = new ChromeDriver(options);
		driver.get(Url);

		Object_LPage.Name_Filter(driver).clear();// clearing existing data
		Object_LPage.Name_Filter(driver).sendKeys(name_filter);// sending name data

		Select IndustryFilter = new Select(Object_LPage.Industry_Filter(driver));
		IndustryFilter.selectByVisibleText(industry_filter);// selecting industry details

		Select SortFilter = new Select(Object_LPage.Sort_Filter(driver));
		SortFilter.selectByVisibleText(sort_filter);// selecting sorting details

		// Check for no output cases or no output results
		String page_output = Object_LPage.Full_Companies(driver).getText();// getting all companies displayed
		int page_output_size = (page_output.replaceAll("//s", "")).length();
		if (Integer.parseInt(expected_no_comp) == 0 || page_output_size == 0)
			try {

				Assert.assertEquals(page_output_size, Integer.parseInt(expected_no_comp));
			} catch (AssertionError e) {
				e.printStackTrace();
				Assert.fail();
			}
		// Check for remaining cases
		if (Integer.parseInt(expected_no_comp) != 0)
			try {
				List<WebElement> Company_Details = Object_LPage.Company_Details(driver);// getting elements from
				// repository
				List<WebElement> Company_Names = Object_LPage.Company_Name(driver);
				String[] expected_output_array = expected_output.split(" ");// expected company format split in arrays
				String[] actual_output = new String[Company_Names.size()];

				for (int i = 0; i < Company_Names.size(); i++) {
					actual_output[i] = Company_Names.get(i).getText();
					// System.out.println("1 list" + Company_Details.get(i).getText() + "2"
					// + Full_company_list.get(expected_output_array[i]));

					Assert.assertEquals(actual_output[i], expected_output_array[i]);
					Assert.assertEquals(Company_Details.get(i).getText(),
							Full_company_list.get(expected_output_array[i]));
					// System.out.println("1 list" + Company_Details.get(i).getText() + "2"
					// + Full_company_list.get(expected_output_array[i]));
				}
				Assert.assertEquals(actual_output, expected_output_array);

			} catch (AssertionError e) {
				e.printStackTrace();
				Assert.fail();

			}
		// Thread.sleep(5);
		driver.close();

	}

}
