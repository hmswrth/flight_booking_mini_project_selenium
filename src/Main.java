import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.*;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver(); // instantiate web driver

		String baseUrl = "https://www.makemytrip.com"; // set base URL

		driver.get(baseUrl); // access the URL using driver object

		Thread.sleep(1000); // explicitly wait for the web page to load

		driver.manage().window().maximize(); // maximize the browser window

		driver.findElement(By.cssSelector("li[data-cy='oneWayTrip']")).click(); // select one-way trip in the home page

		driver.findElement(By.cssSelector("#fromCity")).click(); // click from city

		driver.findElement(By.cssSelector("input[placeholder='From']")).sendKeys("DEL"); // search for Delhi
		Thread.sleep(1000);

		driver.findElement(By.cssSelector("#react-autowhatever-1-section-0-item-0")).click(); // click first hit from
																								// suggestions

		driver.findElement(By.cssSelector("input[placeholder='To']")).sendKeys("MAA"); // search to city (Chennai)
		Thread.sleep(1000);

		driver.findElement(By.cssSelector("#react-autowhatever-1-section-0-item-0")).click(); // click first hit from
																								// suggestions

		driver.findElement(By.cssSelector("div[aria-label='Fri Apr 24 2020']")).click(); // select depature date after
																							// one week
		Thread.sleep(1000);

		driver.findElement(By.cssSelector(".widgetSearchBtn")).click(); // click search button
		Thread.sleep(1000); // wait for the page to load

		driver.findElement(By.cssSelector("span[class='down sort-arrow']")).click(); // click on sort: high to low
		Thread.sleep(1000); // wait for search results to sort

		// store results in a list
		List<WebElement> airway_list = driver.findElements(By.cssSelector(".airways-name")); // returns a list of web
																								// elements with
																								// specified class name
		List<WebElement> flight_code = driver.findElements(By.cssSelector(".fli-code"));
		List<WebElement> price = driver.findElements(By.cssSelector(".actual-price"));

		String[] airways = new String[airway_list.size()]; // string array to store the airway name
		int index = 0; // index value to append string value to the array
		for (WebElement i : airway_list) { // for each web element in list
			String a = i.getText(); // get the text value
			if (!a.isEmpty()) {   // conditional statement to check if the string value is empty. This is important as the web page consists several nodes which implement same css class that are empty at times.
				airways[index] = a;
				index++;
			}
		}
		index = 0; // resetting the index value to zero
		String[] codes = new String[flight_code.size()]; // string array to store flight codes
		for (WebElement i : flight_code) {
			String a = i.getText();
			if (!a.isEmpty()) {
				codes[index] = a;
				index++;
			}
		}
		index = 0; // resetting the index to zero
		String[] final_prices = new String[price.size()]; // string array to store the ticket prices
		for (WebElement i : price) {
			String a = i.getText();
			// System.out.println(a);
			if (!a.isEmpty()) {
				final_prices[index] = a;
				index++;
			}
		}

		// output formatting for better visual experience
		System.out.format("%5s", "S.No.");
		System.out.format("%10s", "AIRWAYS");
		System.out.format("%30s", "FLIGHT NUMBER");
		System.out.format("%14s", "PRICE");
		System.out.println();

		for (int i = 0; i < 5; i++) {
			System.out.format("%5d", i + 1);
			System.out.format("%10s", airways[i]);
			System.out.format("%30s", codes[i]);
			System.out.format("%14s", final_prices[i]);
			System.out.println();
		}
		driver.close();
	}

}
