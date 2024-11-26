
=>set up for the code
__________________________________________________________________________________________________________________

1.install eclipse ide
2.associate selenium to it and i used (selenium 3.141.59 version)
3.download chromedriver.exe file and attch to the project for running it in chrome browser 


=> Automation Script 
__________________________________________________________________________________________________________________

package sample;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Selenium {

	public static void main(String[] args) throws Throwable 
	{
		// open the chrome browser
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		   WebDriver driver = new ChromeDriver();
		   driver.manage().deleteAllCookies();
		   driver.manage().window().maximize();
		   driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		   
		   // navigate to fitpeo.com
		   driver.get("https://www.fitpeo.com/");
		   
		   // navigate to Revenue Calculator Page
		   driver.navigate().to("https://fitpeo.com/revenue-calculator");
		   
		   // scrolling the window upto visibility of slider
		   JavascriptExecutor js = (JavascriptExecutor) driver;
         js.executeScript("window.scrollBy(0,400)", "");
         
         // adjust the slider to given value
        WebElement ele = driver.findElement(By.xpath("//span[contains(@class,'MuiSlider-thumb MuiSlider')]"));
        Actions ac = new Actions(driver);
        ac.dragAndDropBy(ele, 94, 0).perform();
        ac.sendKeys(Keys.ARROW_LEFT,Keys.ARROW_LEFT,Keys.ARROW_LEFT).perform();
        
        // changing the slider color to red
        js.executeScript("arguments[0].style.backgroundColor='red'", ele);
        WebElement ele2 = driver.findElement(By.xpath("//span[@class='MuiSlider-track css-10opxo5']"));
        js.executeScript("arguments[0].style.backgroundColor='red'", ele2);
        
        // then click the text field and enter the value
        Thread.sleep(2000);
        WebElement ele3 = driver.findElement(By.xpath("//input[@type='number']"));
        
        // clearing the value first
        ele3.clear();
		ele3.sendKeys(Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE);
		Thread.sleep(2000);
		ele3.sendKeys("560");
		
		// validate the value in the text field and slider position are same
		String s_value = driver.findElement(By.xpath("//input[@aria-orientation='horizontal']")).getAttribute("value");
		System.out.println(s_value);
		
		//validating slider value with text field
		if(s_value.equals("560")) 
		{
			System.out.println("The slider value and text field value both are same");
		}
		
		// scrolling the page 200 pixels and clicking the specified checkboxes
		js.executeScript("window.scrollBy(0,200)");
		driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
		driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
		driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@type='checkbox'])[8]")).click();
		
		Thread.sleep(2000);
		ele3.clear();
		ele3.sendKeys(Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE);
		Thread.sleep(2000);
		ele3.sendKeys("820");
		
		// validating the header displayed total recurring amount is same as expected value
		js.executeScript("window.scrollBy(0,200)");
		String total_RR = driver.findElement(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 inter css-1bl0tdj'][normalize-space()='$110700']")).getText();
		System.out.println("Total Recurring Reimbursement for all Patients Per Month: "+total_RR);
		
		String expected = "$110700";
		
		if(total_RR.equals(expected))
		{
			System.out.println("Total Recurring Reimbursement for all Patients Per Month value and expected value both are same");
		}
		
	}

}
