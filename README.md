#### Lingoda-QA-Test
- This is an automation test script, written in Java for https://www.amazon.de/
- Code:  Lingoda-QA-Test/qaTask/src/taskPackage/taskClass.java 

Pre-requisites:
1. Selenium WebDriver installed (install Java, Eclipse IDE and configure the IDE with webdriver). Follow the link below for steps on how to: https://www.guru99.com/installing-selenium-webdriver.html
2. After setting up the IDE, load the project and then click the Run button.

Steps followed in the test script
1. Launch the browser and direct it to the test URL
2. Enter 'Batman comics' as the search text in the Search field and click the search icon
3. Check that results number is above 0
4. Check that the title contains 'Batman' word
5. Verify that the price of the first item is above 0
6. Verify that the price of the first item has 'EUR' postfix
7. Check that the first item has rating
8. Open the first item on the page and check that the title and price are equal to the ones in search results 
9. Verify that the prices are equal on both pages
10. Close the browser
