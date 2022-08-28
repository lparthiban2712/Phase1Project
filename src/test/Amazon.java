package test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Amazon {

	public static void main(String[] args) throws InterruptedException {
		
		
		//Launch the Amazon Application and maximize the window
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
		
		//Enter 'Samsung' in the search box and press ENTER key.
		//Store the Products and its prize in List collection
		//Print the count in the console.
		WebElement search=driver.findElement(By.id("twotabsearchtextbox"));
		search.sendKeys("Samsung",Keys.ENTER);
		List<WebElement>products=driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[starts-with(text(),'Samsung')]"));
		List<WebElement>prize=driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price-whole']"));
		System.out.println("No of products is "+ products.size());
		System.out.println("No of products prize is "+prize.size());
		
		//Print the Products and its prize in console
		for(int i=0;i<products.size();i++)
		{
			System.out.print(products.get(i).getText());
			System.out.println(" prize is "+prize.get(i).getText());
		}
		
		//Store the Parent window in a String
		String parentwindow=driver.getWindowHandle();
		
		//Store the first product text in a String
		String Expected=products.get(0).getText();
		
		//Click on the first product
		products.get(0).click();
		
		//Store all the windows opened by Selenium in Set collection
		
		Set<String> childwindows=driver.getWindowHandles();
		
		//Iterate the window using for each loop
		
		for(String childwindow:childwindows)
		{
			//switch to child window if not equal to Parent window
			if(!childwindow.equals(parentwindow))
			{
				driver.switchTo().window(childwindow);
			}
		}
		
		//Get the text of first product in new opened window
		WebElement new_window_text=driver.findElement(By.cssSelector("#productTitle"));
		String Actual=new_window_text.getText();
		
		//verify the Expected and Actual content are matching and print the result in console
		
		if(Expected.equals(Actual))
		{
			System.out.println("Test case is passed");
		}
		
		else
		{
			System.out.println("Test case is failed");
		}
		
		

	}

}
