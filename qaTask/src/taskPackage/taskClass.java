package taskPackage;

import static org.junit.Assert.assertTrue;

//import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class taskClass {

    public static void main(String[] args) throws InterruptedException {
    	
        // Declaring and instantiating objects/variables
    	WebDriver driver=new ChromeDriver();
    	driver.manage().window().maximize();
    	
        String testUrl = "https://www.amazon.de/";
        float basePrice = 0;
        String priceLabel, priceValue, priceLabel2, priceValue2;

        // Launch the browser and direct it to the Base URL
        driver.get(testUrl);
        
        //Enter search text in the Search field and click the search icon
        WebElement search = driver.findElement(By.name("field-keywords"));
        search.sendKeys("Batman comics");
        WebElement searchIcon = driver.findElement(By.xpath("//*[@id=\"nav-search\"]/form/div[2]/div/input"));
        searchIcon.click();
        
        //List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"search\"]/div[1]/div[2]/div/span[3]/div[1]/div[1]/div/div*[contains(text(),'" + Batman + "')]"));
        String firstitemTitle = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[2]/div/span[3]/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div[1]/div/div/div[1]/h5/a/span")).getText();
        System.out.println("The first item's title is:" + firstitemTitle);
        if(firstitemTitle.contains("Batman")) {
        	System.out.println("The title of the first item contains Batman");
        }
        else {
        	System.out.println("The title of the first item does not contain Batman");
        }
        
        
        priceLabel = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[2]/div/span[3]/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div[1]/div/div[1]/div[2]/div/a/span")).getText();
        priceValue = priceLabel.replace("€", "").replace(",", ".");
        System.out.println("The price of the item is: " + Float.parseFloat(priceValue));
        assertTrue(basePrice < Float.parseFloat(priceValue));        
        System.out.println("The price of the item is above 0");
        
        assertTrue(priceLabel.endsWith("€"));
        System.out.println("PASS: The price of the item has 'EUR' postfix");
        
        if(driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[2]/div/span[3]/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div[1]/div/div/div[2]/div"))!= null){
        	System.out.println("PASS: The item has rating");
        	}else{
        	System.out.println("FAIL: Rating for the item is not displayed");
        	}
        
        driver.findElement(By.linkText(firstitemTitle)).click();
        String firstProductTitle = driver.findElement(By.id("productTitle")).getText();
        if(firstProductTitle.equals(firstitemTitle)){
            System.out.println("PASS: The title of the first item matches the title displayed on the products page");
        }
            else {
                System.out.println("FAIL: The titles do not match. The title on the first item is " + firstitemTitle + " while the title on products page is " + firstProductTitle);
            }
        priceLabel2 = driver.findElement(By.xpath("//*[@id=\"a-autoid-2-announce\"]/span[2]/span")).getText();
        priceValue2 = priceLabel2.replace("EUR", "").replace(",", ".").replace(" ", "");
        System.out.println(priceValue2);
        if(Float.parseFloat(priceValue2) == Float.parseFloat(priceValue)) {
        	System.out.println("PASS: Prices match on both pages");
        }
        else {
        	System.out.println("FAIL: The prices do not match. The price on the first item is " + priceValue +" while the price on the products page is " + priceValue);
        }
        
    	}
	}