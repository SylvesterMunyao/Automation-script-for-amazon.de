/*
 * Task: Create automation test for search functionality of a German car web site
 * Authored by Sylvester Kitaka Munyao
 * Date: 21th March 2019
*/
package newPackage;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
public class myClass {
    public static void main(String[] args) throws InterruptedException {
    	
        // Declaring and instantiating objects/variables
    	WebDriver driver=new ChromeDriver();
    	driver.manage().window().maximize();
    	
        String testUrl = "https://www.autohero.com/de/search/";
        int baseFilterYear = 2015;
        

        // Launch the browser and direct it to the Base URL
        driver.get(testUrl);
        
        //Filter for First registration (Erstzulassung) from 2015
        Select erstzulassungAb = new Select(driver.findElement(By.name("yearRange.min")));
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/main/div[4]/div/div[1]/div/div/div/div[3]/div[1]/span")).click();
        erstzulassungAb.selectByVisibleText(Integer.toString(baseFilterYear));
        
        //Sort cars by Price Descending (Höchster Preis)
        Select sortiertNach = new Select(driver.findElement(By.name("sort")));
        sortiertNach.selectByVisibleText("Höchster Preis");
        Thread.sleep(5000);
        
        List<WebElement> tdList = driver.findElements(By.className("item___T1IPF"));
        System.out.println(tdList.size());
        String tempYearList, tempYear, tempPrice;
        
        float baseSortPrice = 0;
        
        for (int i = 0; i < tdList.size(); i++) {
        	tempPrice = tdList.get(i).findElement(By.className("totalPrice___3yfNv")).getText();
        	tempYearList = tdList.get(i).findElement(By.className("specList___2i0rY")).getText();
        	tempYear = tempYearList.split("\n")[1];
        	
        	//Verify all cars are filtered by first registration (2015+)
        	if (baseFilterYear <= Integer.parseInt(tempYear.split("/")[1])) {
        		System.out.println("Year for item " + i + " passed");
			}   
        	else {
        		System.out.println("Year for item " + i + " failed");
        	}
        	
        	//Verify all cars are sorted by price descending
        	if (i == 0) {
        		baseSortPrice = Float.parseFloat(tempPrice.split(" ")[0]);
			}   
        	else {
        		if (baseSortPrice >= Float.parseFloat(tempPrice.split(" ")[0])) {
        			System.out.println("Sort for item " + i + " passed\n");
				}
        		else {
        			System.out.println("Sort for item " + i + " failed\n");
        		}
        	}	
		}		
        //close the browser 
        driver.close();      
    }
}
