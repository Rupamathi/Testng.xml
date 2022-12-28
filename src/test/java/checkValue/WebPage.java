package checkValue;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

@Test
public class WebPage {
	public static WebDriver driver ;
	public static long start;
	public static long ending;
	public static WebElement item;
	@DataProvider(name="mobiles")
	public Object[][] method(){
		return new Object[][] {{"saree"}};
	}
	@Parameters({"driver"}) 
	@BeforeClass
	public static void launch(String value) {
		if(value.equals("chrome")) {
	    	
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
	    	}else
	    	{

	    		WebDriverManager.edgedriver().setup();
	    		driver = new EdgeDriver();
	    	}
		
		
	}
	@AfterClass
	public static void close(){
//		driver.quit();
		
	}
	@BeforeMethod
	public void starting() {
		 start = System.currentTimeMillis();
		 System.out.println("Starting==> "+start);
		
	}
	@AfterMethod
	public void ending() {
		ending= System.currentTimeMillis();
		long Total =(ending-start);
		System.out.println("Totaltime==> "+Total);
		
	}
	@Test(priority=1,enabled=true,dataProvider="mobiles")
	public void saree(String name1) throws Throwable {
	    

		driver.findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys(name1,Keys.ENTER);
		Thread.sleep(1000);
		WebElement item = driver.findElement(By.xpath("//p[contains(text(),'Aagam Drishya Sarees')]"));
		String text = item.getText();
		System.out.println("Selected Saree: "+text);
		boolean displayed = item.isDisplayed();
		Assert.assertTrue(displayed);
		item.click();
		String parent = driver.getWindowHandle();
		Set<String> child = driver.getWindowHandles();
		for(String x:child) {
			if(!parent.equals(x)) {
				driver.switchTo().window(x);
		WebElement name = driver.findElement(By.xpath("//span[contains(text(),'Aagyeyi Alluring Sarees')]"));
		String text2 = name.getText();
		System.out.println(text2);
	}
		}
	}
	@Test(priority=2,dependsOnMethods="saree")
	public void addCart() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement until = wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//span[contains(text(),'Add to Cart')]"))));
		until.click();
	}

		
	}		

		


