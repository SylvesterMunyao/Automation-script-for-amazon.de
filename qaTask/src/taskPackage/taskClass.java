/*
 * Task: Create automation test for amazon.de
 * Authored by Sylvester Kitaka Munyao
 * Date: 21th March 2019
*/
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

    //Launch the browser and direct it to the test URL
    driver.get(testUrl);
    
    //2 (a). Enter search text in the Search field
    WebElement search = driver.findElement(By.name("field-keywords"));
    search.sendKeys("Batman comics");
    
    //2 (b). Click the search icon
    WebElement searchIcon = driver.findElement(By.xpath("//*[@id=\"nav-search\"]/form/div[2]/div/input"));
    searchIcon.click();
    
    //3. Check that results number above 0
    int searchResultsCount = driver.findElements(By.cssSelector("#search > div.sg-row > div.sg-col-20-of-24.sg-col-28-of-32.sg-col-16-of-20.sg-col.s-right-column.sg-col-32-of-36.sg-col-8-of-12.sg-col-12-of-16.sg-col-24-of-28 > div > span:nth-child(4) > div.s-result-list.sg-row > div:nth-child(1) > div > div > div > div:nth-child(2)")).size();
    System.out.println(searchResultsCount);
    assertTrue(searchResultsCount > 0);
    
    
    //4 (a). Check that the title contains 'Batman' word
    String firstitemTitle = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[2]/div/span[3]/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div[1]/div/div/div[1]/h2/a/span")).getText();
    System.out.println("The first item's title is: " + firstitemTitle);
    if (firstitemTitle.contains("Batman")) {
    	System.out.println("The title of the first item contains Batman");
    }
    else {
    	System.out.println("The title of the first item does not contain Batman");
    }
    
    //4 (b). Verify that the price is above 0
    priceLabel = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[2]/div/span[3]/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div[1]/div/div[1]/div[2]/div/a/span")).getText();
    priceValue = priceLabel.replace("€", "").replace(",", ".");
    System.out.println("The price of the item is: " + Float.parseFloat(priceValue));
    if (basePrice < Float.parseFloat(priceValue)) {
    	System.out.println("PASS: The price of the item is above 0");
    }
    else {
    	System.out.println("FAIL: The price of the item is not 0");
    }
    
    //4. (c). Verify that the price has 'EUR' postfix
    if (priceLabel.endsWith("€")) {
    	System.out.println("PASS: The price of the item has 'EUR' postfix");
    }
    else {
    	System.out.println("PASS: The price of the item has 'EUR' postfix");
    }
    
    //4 (d). Check that item has rating
    if (driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[2]/div/span[3]/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div[1]/div/div/div[2]/div"))!= null) {
    	System.out.println("PASS: The item has rating");
    }
    else {
    	System.out.println("FAIL: Rating for the item is not displayed");
    }
    
    //5 (a). Open the first item on the page and check that the title and price are equal to the ones in result search
    driver.findElement(By.linkText(firstitemTitle)).click();
    String firstProductTitle = driver.findElement(By.id("productTitle")).getText();
    if (firstProductTitle.equals(firstitemTitle)) {
        System.out.println("PASS: The title of the first item matches the title displayed on the products page");
    }
    else {
            System.out.println("FAIL: The titles do not match. The title on the first item is " + firstitemTitle + " while the title on products page is " + firstProductTitle);
    }
    
    //5 (b). Verify that the prices are equal on both pages
    priceLabel2 = driver.findElement(By.xpath("//*[@id=\"a-autoid-2-announce\"]/span[2]/span")).getText();
    priceValue2 = priceLabel2.replace("EUR", "").replace(",", ".").replace(" ", "");
    assertTrue(Float.parseFloat(priceValue2) == Float.parseFloat(priceValue));
//    System.out.println(priceValue2);
//    if (Float.parseFloat(priceValue2) == Float.parseFloat(priceValue)) {
//    	System.out.println("PASS: Prices match on both pages");
//    }
//    else {
//    	System.out.println("FAIL: The prices do not match. The price on the first item is " + priceValue +" while the price on the products page is " + priceValue);
//    }
    
    //close the browser
    driver.close();
}
}
