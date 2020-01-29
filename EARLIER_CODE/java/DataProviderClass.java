import org.testng.annotations.DataProvider;

public class DataProviderClass { // data can be interpreted from the page or provided by other sources like XL
	// here
	@DataProvider(name = "data-provider")
	public static Object[][] dataProviderMethod() {
		return new Object[][] {

				// {"NAME FIELD",ÏNDUSTRY FIELD","EXPECTED NUMBER OUTPUT",ËXPECTED COMPANY NAMES
				// in ORDER"}
				{ "", "transport", "name", "2", "Lufthansa Tesla" }, // Test1
				{ "", "transport", "name", "2", "Tesla Lufthansa" }, // Test2- Fails
				{ "amn", "transport", "name", "0", "Lufthansa Tesla" }, // Test3-
				{ "amn", "transport", "name", "1", "Lufthansa Tesla" }, // Test4-Fail
				{ "Apple", "transport", "name", "0", "" }, { "Apple", "all", "name", "1", "Apple" },
				{ "Luft", "all", "name", "1", "Lufthansa" }, { "", "media", "name", "2", "Disney Netflix" },
				{ "", "media", "market cap", "2", "Netflix Disney" },

				{ "", "all", "market cap", "7", "Lufthansa Tesla Netflix SAP Disney Microsoft Apple" },

				{ "", "media", "name", "2", "Lufthansa Tesla" },
				{ "", "technology", "share price", "3", "SAP Microsoft Apple" },
				{ "", "technology", "industry", "3", "Apple Microsoft SAP" },
				{ "", "all", "industry", "7", "Disney Netflix Apple Microsft SAP Lufthansa Tesla" },
				{ "", "transport", "name", "2", "Lufthansa Tesla" },
				{ "", "transport", "name", "2", "Lufthansa Tesla" },
				{ "", "transport", "name", "2", "Lufthansa Tesla" },
				{ "", "transport", "name", "2", "Lufthansa Tesla" },

		};

	}

}