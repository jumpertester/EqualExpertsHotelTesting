package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by cwarren on 11/07/2018.
 */
public class Launcher {

    public static WebDriver getChromeDriver(){
        return new ChromeDriver();
    }

    public static void setChromeDriver(){
        String webDriverKey = "webdriver.chrome.driver";
        String webDriverValue;
        System.out.println(System.getProperty("os.name").toLowerCase());
        String osType = System.getProperty("os.name").toLowerCase();

        if(osType.contains("win")){
            webDriverValue = System.getProperty("user.dir") +
                    "/target/tmp_webdrivers/chromedriver-windows-32bit.exe";
        }else if(osType.contains("mac")){
            webDriverValue = System.getProperty("user.dir") +
                    "/target/tmp_webdrivers/chromedriver";
        } else{
            throw new IllegalArgumentException("We are sorry but we do not support the operating system you are using.");
        }
        
        System.setProperty(webDriverKey, webDriverValue);
    }

    public static WebDriver getBrowser(WebDriver driver){
        String baseUrl = ("http://hotel-test.equalexperts.io/");
        driver.get(baseUrl);
        // temporary work around to get past intermittent issue with application
        // occasionally createBooking function fails to load application correctly
        for(int i =0; i<3; i++)
        {
            driver.navigate().refresh();
        }
        return driver;
    }
}
