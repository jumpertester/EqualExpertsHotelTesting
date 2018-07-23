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
