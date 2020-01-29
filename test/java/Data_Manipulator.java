import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Data_Manipulator {
	private static final String[][] Company_array = initialise();

	public static String[][] initialise() {/*
											 * ideally company details should be known and could imported from data file
											 * -importing from the page for current
											 */
		System.setProperty("webdriver.chrome.driver", "D://CHrome_driver//chromedriver.exe");
		WebDriver driver2;
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		driver2 = new ChromeDriver(options);

		String Url = "https://arnvald.github.io/cgl-data-exercise/";
		driver2.get(Url);
		System.out.println("only once");

		String[][] Company_array;

		List<WebElement> Company_MarketCap = Object_LPage.Company_MarketCap(driver2);
		List<WebElement> Company_SharePrice = Object_LPage.Company_SharePrice(driver2);
		List<WebElement> Company_StockExchange = Object_LPage.Company_StockExchange(driver2);
		List<WebElement> Company_Industry = Object_LPage.Company_Industry(driver2);
		List<WebElement> AllCompany_Names = Object_LPage.Company_Name(driver2);
		Company_array = new String[AllCompany_Names.size()][5];
		for (int i = 0; i < AllCompany_Names.size(); i++) {
			Company_array[i][0] = AllCompany_Names.get(i).getText();
			Company_array[i][1] = (Company_details_modify(Company_MarketCap.get(i).getText(), "number"));
			Company_array[i][2] = (Company_details_modify(Company_SharePrice.get(i).getText(), "number"));
			Company_array[i][3] = (Company_details_modify(Company_StockExchange.get(i).getText()));
			Company_array[i][4] = (Company_details_modify(Company_Industry.get(i).getText()));

		}
		// driver2.close();
		return Company_array;

	}

	public static String[] getdata(String name_filter, String industry_filter, String sort_filter) {

		String Company_text;
		int company_count = 0;
		String[][] Company_array_copy;
		String[] return_array = new String[5];

		HashMap<String, Integer> SORT_FIELD = new HashMap<String, Integer>();// easy map for sorting
		SORT_FIELD.put("name", 0);
		SORT_FIELD.put("market cap", 1);
		SORT_FIELD.put("SharePrice", 2);
		SORT_FIELD.put("industry", 4);

		/*
		 * ideally company details should be known and imported from data file
		 * -importing from the page for current
		 */

		Company_array_copy = Contains_filter(Contains_filter(0, name_filter), 4, industry_filter);
		Company_array_copy = Sorter(Company_array_copy, SORT_FIELD.get(sort_filter));

		Company_text = Company_array_copy[0][0];
		for (company_count = 1; company_count < Company_array_copy.length
				&& Company_array_copy[company_count][0] != null; company_count++)
			Company_text += " " + Company_array_copy[company_count][0];

		return_array[0] = name_filter;
		return_array[1] = industry_filter;
		return_array[2] = sort_filter;
		return_array[3] = "" + company_count;
		return_array[4] = Company_text;

		return (return_array);
	}

	public static String Company_details_modify(String details) {
		String reply = details.substring(details.lastIndexOf(":") + 1);
		return reply.trim();
	}

	public static String Company_details_modify(String details, String s) {
		String reply = details.substring(details.lastIndexOf(":") + 1);
		Double s1 = 0.0;
		reply = reply.trim();
		if (reply.contains("B")) {
			s1 = (Double.parseDouble(reply.substring(0, reply.length() - 1)) * 1000);
			reply = "" + s1;
		} else if (reply.contains("T")) {
			s1 = +(Double.parseDouble(reply.substring(0, reply.length() - 1)) * 100000);
			reply = "" + s1;
		}
		return reply;
	}

	public static String[][] Contains_filter(String[][] s, int col_number, String filter) {

		String Company_return[][] = new String[s.length][s[0].length];
		int m = 0, n = 0;
		if (filter == "all" || s[0][0] == null || filter == "")
			return s;

		for (int i = 0; i < s.length; i++) {
			if (s[i][col_number].toLowerCase().contains(filter.toLowerCase())) {

				for (int j = 0; j < s[0].length; j++)
					Company_return[m][j] = s[i][j];
				m++;
			}

		}
		return Company_return;

	}

	public static String[][] Contains_filter(int col_number, String filter) {
		{
			String[][] s = Company_array;
			String Company_return[][] = new String[s.length][s[0].length];
			int m = 0, n = 0;
			if (filter == "all" || s[0][0] == null || filter == "")
				return s;

			for (int i = 0; i < s.length; i++) {
				if (s[i][col_number].toLowerCase().contains(filter.toLowerCase())) {

					for (int j = 0; j < s[0].length; j++)
						Company_return[m][j] = s[i][j];
					m++;
				}

			}
			return Company_return;

		}
	}

	public static String[][] Sorter(String[][] s2, int column) // avoiding comparator as string and double values
	{
		if (s2[1][0] == "" || s2[1][0] == null)
			return s2;

		String[][] dummy = new String[1][s2[0].length];
		for (int k = 0; k < s2.length - 1; k++)
			for (int i = 0; i < s2.length - 1 && s2[i + 1][column] != null; i++) {
				// System.out.println("S2" + s2[i][column]);
				if (column == 0 || column == 4)
					if (s2[i][column].compareTo(s2[i + 1][column]) > 0) {
						dummy[0] = s2[i];
						s2[i] = s2[i + 1];
						s2[i + 1] = dummy[0];

					}
				if (column == 1 || column == 2) {
					double a1 = Double.parseDouble(s2[i][column]), a2 = Double.parseDouble(s2[i + 1][column]);
					if (a1 > a2) {
						dummy[0] = s2[i];
						s2[i] = s2[i + 1];
						s2[i + 1] = dummy[0];

					}
				}
			}

		return s2;
	}

}
