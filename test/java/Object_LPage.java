
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//Object repository for landing page 
public class Object_LPage {
	private static WebElement element = null;
	private static List<WebElement> elements = null;

	public static WebElement Name_Filter(WebDriver driver) {
		element = driver.findElement(By.xpath("/html/body/html/body/div[1]/input"));
		return element;
	}

	public static WebElement Sort_Filter(WebDriver driver) {
		element = driver.findElement(By.xpath("/html/body/html/body/div[3]/select"));
		return element;
	}

	public static WebElement Industry_Filter(WebDriver driver) {
		element = driver.findElement(By.xpath("/html/body/html/body/div[2]/select"));
		return element;
	}

	public static WebElement Full_Companies(WebDriver driver) {
		element = driver.findElement(By.cssSelector("#companies"));
		return element;
	}

	public static List<WebElement> Company_Details(WebDriver driver) {
		elements = driver.findElements(By.xpath("//div[@class='company']"));
		return elements;
	}

	public static List<WebElement> Company_Name(WebDriver driver) {
		elements = driver.findElements(By.xpath("//div[@class='company']/h2"));
		return elements;

	}

	public static List<WebElement> Company_MarketCap(WebDriver driver) {
		elements = driver.findElements(By.xpath("//div[@class='company']/Span[1]"));
		return elements;

	}

	public static List<WebElement> Company_SharePrice(WebDriver driver) {
		elements = driver.findElements(By.xpath("//div[@class='company']/Span[2]"));
		return elements;

	}

	public static List<WebElement> Company_StockExchange(WebDriver driver) {
		elements = driver.findElements(By.xpath("//div[@class='company']/Span[3]"));
		return elements;

	}

	public static List<WebElement> Company_Industry(WebDriver driver) {
		elements = driver.findElements(By.xpath("//div[@class='company']/SPan[4]"));
		return elements;

	}

}
