import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;


public class AMZTestScript {

    WebDriver driver = new ChromeDriver();
    Actions actions = new Actions(driver);

    @BeforeMethod public void setup()
    {
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");

        String baseurl = ConfigReader.properties.getProperty("BaseUrl");
        driver.get(baseurl);

        driver.manage().window().maximize();

    }
    @Test
    public void chnagelocation() throws InterruptedException{


        WebElement Button = driver.findElement(By.xpath("//*[@class='a-button-text']"));
        Assert.assertEquals(Button.getText(),"Continue shopping", "Button Mismatch");
        Button.click();

        WebElement Location = driver.findElement(By.id("nav-global-location-popover-link"));
        Location.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement Country = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("GLUXZipUpdateInput"))
        );
        Country.click();
        Country.sendKeys("M11AD");

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement Submit = wait1.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='a-button-input' and @type='submit']"))
        );
        Thread.sleep(3000);
        actions.sendKeys(Keys.ENTER).perform();

        //WebElement ZipCode = driver.findElement(By.xpath("//input[@class='GLUX_Full_Width a-declarative' and @type='text']"));
        //WebElement imgContainer = driver.findElement(By.xpath("//*[@class='lnXdpd']"));
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
