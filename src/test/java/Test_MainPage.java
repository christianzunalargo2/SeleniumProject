import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Test_MainPage extends BaseDriver {

    private WebDriver driver;
    private ExtentTest node;

    @BeforeMethod
    public void setUp() {
        driver = getMyDriver();
        logger.info("I'm in Test_MainPage");
        node = test.createNode("Test_MainPage");
    }

    @Test(description = "test_title")
    public void test_title() {
        logger.info("getting website title");
        String title = driver.getTitle();
        logger.info("this is the title" + title);

        Assert.assertEquals(title, "Google");

        node.log(Status.INFO, "website title");
        node.pass("Passed!");
    }

    @Test(dependsOnMethods = "test_title", description = "test_searchFunctionality")
    public void test_searchFunctionality() {
        logger.info("finding search box");
        WebElement searchBox = driver.findElement(By.xpath("//input[@name='q']"));

        searchBox.sendKeys("Dogs", Keys.RETURN);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String dogsTitle = driver.getTitle();

        Assert.assertEquals(dogsTitle, "Dogs - Google Search");
        node.log(Status.INFO, "searching Dogs in Google search");
        node.pass("Passed!");
    }
}
