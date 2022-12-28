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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RedmiMobile {

	public static WebDriver driver1;
	 public static  long start;
	 public static long end;
	
	 @Parameters({"browser"})
    @BeforeClass(groups="default")
    
	public static void launch(String webpage) {
    	if(webpage.equals("chrome")) {
    	
		WebDriverManager.chromedriver().setup();
		driver1 = new ChromeDriver();
    	}else
    	{

    		WebDriverManager.edgedriver().setup();
    		driver1 = new EdgeDriver();
    	}
		driver1.navigate().to("https://www.flipkart.com/");
		driver1.manage().window().maximize();
		
    }
    @AfterClass
    public static void close() {
    	//driver.close();
    }
    @BeforeMethod
    public void startTime() {
    	 start = System.currentTimeMillis();
    	 System.out.println("Redmi start Time:" +start);
    }
    @AfterMethod
    public void endTime() {
    	end = System.currentTimeMillis();
    	System.out.println("Redmi end Time==>"+(end-start));
    }
    @Test(priority=1)
    public void homePage() throws InterruptedException {
    	try {
    	driver1.findElement(By.xpath("//button[text()='✕']")).click();
    	}catch(Exception e)
    	{
    		System.out.println("close");
    	}
    	}
    @Parameters("setvalue")
    @Test(priority=2)	
    	public void mobile(String name) throws InterruptedException {
    		try {
    		driver1.findElement(By.xpath("//input[@type='text']")).sendKeys(name,Keys.ENTER);
    		
    		
    		}catch(Exception e) {
    			System.out.println("enter to mobile list");
    		}
    		
   
    	}
    	@Test(priority=3)
    	public void addCart() throws InterruptedException {
    		try {
    			Thread.sleep(5000);
    		WebElement firstMobile = driver1.findElement(By.xpath("(//div[@class='_4rR01T'])[1]"));
    		String text = firstMobile.getText();
            firstMobile.click();
            System.out.println(text);

				
				String parent = driver1.getWindowHandle();
				Set<String> child = driver1.getWindowHandles();
				for(String x:child) {
					driver1.switchTo().window(x);
					Thread.sleep(5000);
					if(!parent.equals(x)) {
						
						 WebElement addCart = driver1.findElement(By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']"));
				    		addCart.click();
				    		
				    		WebDriverWait wait = new WebDriverWait(driver1,10);
				    		
				    		//WebElement placeorder = driver.findElement(By.xpath("//span[contains(text(),'Place Order')]"));
				    		
				    				wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//span[contains(text(),'Place Order')]")))).click();		
					}
				}
							}catch(Exception e) {
								System.out.println(e);
							}
 
					}
    	
			
			

}
