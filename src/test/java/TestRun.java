import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestRun {

  @Test
  public void testRunWebAutomation() throws InterruptedException {
    ChromeOptions options = new ChromeOptions(); // This Is For what your browser used
    //setting chrome capabilities https://peter.sh/experiments/chromium-command-line-switches/
    options.addArguments("--start-maximized,--incognito");
    WebDriver driver = new ChromeDriver(options);
    //for mac it --start-maximized not working properly. So need use this command to maximize
    driver.manage().window().maximize();
    driver.get("https://www.atlasid.tech/");
    Thread.sleep(5000);
    driver.quit();
  }

  @Test
  public void testArguments() throws InterruptedException {
    ChromeOptions chromeOptions = new ChromeOptions();
    //chromeOptions.addArguments("--headless"); //Untuk Testing Tanpa Perlu Membuka UI
    chromeOptions.addArguments("--start-maximized");

    WebDriver driver = new ChromeDriver(chromeOptions);
    driver.get("https://www.atlasid.tech/");

    Thread.sleep(5000);
    driver.quit();
  }

  @Test
  public void findLocaters() throws InterruptedException {
    ChromeOptions chromeOptions = new ChromeOptions();
    //chromeOptions.addArguments("--headless"); //Untuk Testing Tanpa Perlu Membuka UI
    chromeOptions.addArguments("--start-maximized");
    WebDriver driver = new ChromeDriver(chromeOptions);
    driver.get("https://www.seleniumeasy.com/test/");


    //Elements Find By Id
    driver.findElement(By.id("developer-name"));

    //Elements Find By Class
    driver.findElement(By.className("btn btn-success btn-outline-rounded green"));

    //Elements Find By X-Path
    driver.findElement(By.xpath("//input[@id='developer-name']"));

    Thread.sleep(5000);
    driver.quit();
  }

  @Test
  public void testTestCafe() throws InterruptedException {
    WebDriver driver = new ChromeDriver();
    driver.get("https://devexpress.github.io/testcafe/example/");

    //Click Button Populate
    WebElement buttonElement = driver.findElement(By.xpath("//input[@id='populate']"));
    buttonElement.click();

    // Handle Alert because blocking next step
    driver.switchTo().alert().accept();

    //Get Value On Text Box
    WebElement inputName = driver.findElement(By.xpath("//input[@id='developer-name']"));
    String text = inputName.getAttribute("value");
    System.out.println("text = " + text);

    //What text expected you want - Assert
    assertEquals("Peter Parker", text);

    //Checkbox
    WebElement cbRemote = driver.findElement(By.xpath("//input[@id='remote-testing']"));
    cbRemote.click();
    WebElement cbParallelTesting = driver.findElement(By.xpath("//input[@id='background-parallel-testing']"));
    cbParallelTesting.click();

    assertTrue(cbRemote.isSelected());
    assertTrue(cbParallelTesting.isSelected());

    //Handling Dropdown menu
    WebElement dropDown = driver.findElement(By.xpath("//select[@id='preferred-interface']"));
    Select select = new Select(dropDown);
    //Selected By Index
    int index = 1;
    select.selectByIndex(index);

    String textDropdown = select.getOptions().get(index).getText();

    Thread.sleep(5);
    assertEquals("JavaScript API", textDropdown);

    driver.findElement(By.xpath("//input[@id='tried-test-cafe']")).click();

    WebElement webSlider = driver.findElement(By.xpath("//div[@id='slider']"));
    Actions actions = new Actions(driver);
    actions.dragAndDropBy(webSlider,30,0).build().perform();

    driver.quit();
  }

}
