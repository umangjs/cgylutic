import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class TEST_WEB {
	public static void main(String[] args) {
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] { NewTest.class });

		testng.run();
	}
}
