import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;


public class BaseDriver {

    static Logger logger = LogManager.getLogger();
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    public WebDriver getMyDriver() {
        return driver;
    }

    @BeforeClass
    public void setUpDriver() {
        logger.info("driver initiating...");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
        logger.info("driver initiated!");

        logger.info("Initiating extentReporter");
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("Spark.html");
        extent.attachReporter(spark);
        test = extent.createTest("TestMainPage");
        test.log(Status.INFO, "Main Page is open");
    }


    @AfterClass
    public void tearDown() {
        logger.info("driver quitting");
//        extent.removeTest(test);
        extent.flush();
        driver.quit();
    }
}
