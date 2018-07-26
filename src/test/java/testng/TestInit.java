package testng;

import helper.Interactions;
import helper.Launcher;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Created by cwarren on 19/07/2018.
 */
public class TestInit {

    WebDriver driver;
    Interactions interactions;

    @BeforeSuite (description = "Setting the environment by deleting existing bookings.")
    public void beforeSuite(){
        Launcher.setChromeDriver();
        deleteBookings();
    }

    @AfterSuite (description = "Tidying environment by removing bookings.")
    public void afterSuite(){
        deleteBookings();
    }

    private void deleteBookings(){
        driver = Launcher.getChromeDriver();
        Launcher.getBrowser(driver);
        interactions = new Interactions(driver);
        interactions.deleteAllBookings();
        driver.quit();
    }

}
