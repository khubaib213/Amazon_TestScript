import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;


public class TestScript {
    @Test
    public void mainTest1() throws InterruptedException{

        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");

        driver.get("https://www.amazon.com/?&tag=googleglobalp-20&ref=pd_sl_7nnedyywlk_e&adgrpid=159651196451&hvpone=&hvptwo=&hvadid=675114638556&hvpos=&hvnetw=g&hvrand=12072806457983618597&hvqmt=e&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=1011082&hvtargid=kwd-10573980&hydadcr=2246_13649807");
        driver.manage().window().maximize();


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


        driver.quit();
    }
}
