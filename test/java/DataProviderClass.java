import org.testng.annotations.DataProvider;

public class DataProviderClass { // data can be interpreted from the page or provided by other sources like XL
	// here

	@DataProvider(name = "data-provider")
	public static Object[][] dataProviderMethod() {
		Data_Manipulator r = new Data_Manipulator();
		return new Object[][] { r.getdata("", "transport", "name"), // test 1
				Data_Manipulator.getdata("", "media", "name"), r.getdata("", "all", "name")

		};
	}

}
