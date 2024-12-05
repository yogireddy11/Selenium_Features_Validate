package BasePack;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {

    public WebDriver driver;
    public WebDriverWait driverWait;
    public JavascriptExecutor executor;
    Properties properties = new Properties();
    FileInputStream inputStream;
    public Actions actions;

    @BeforeSuite
    public void setup(){
        try {
            inputStream = new FileInputStream("src/test/object.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String browserName = properties.getProperty("browser");
        switch (browserName){
            case "edge":
                driver = new EdgeDriver();
                System.out.println("edge driver opened!!");
                break;
            case "chrome":
                driver = new ChromeDriver();
                System.out.println("chrome driver opened!!");
                break;
            case "firefox":
                driver = new FirefoxDriver();
                System.out.println("firefox driver opened!!");
                break;
            default:
                System.out.println("no driver opened!!");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driverWait = new WebDriverWait(driver,Duration.ofSeconds(10));
        executor = (JavascriptExecutor)driver;
        driver.get(properties.getProperty("baseurl"));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        actions = new Actions(driver);
    }
    @AfterClass
    public void exit(){
        driver.quit();
    }
}
