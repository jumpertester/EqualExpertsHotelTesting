package testng;

import helper.ExcelUtils;
import helper.Interactions;
import helper.Launcher;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Created by cwarren on 19/07/2018.
 */
public class BookingNotCreated {

    WebDriver driver;
    Interactions interactions;

    @BeforeMethod
    public void before(){
        driver = Launcher.getChromeDriver();
        Launcher.getBrowser(driver);
        interactions = new Interactions(driver);
    }

    @Test(description = "No booking created due to a missing input field", dataProvider = "getBookingData")
    public void bookingNotCreatedTest(String firstName, String surname, String price, String depositPaid, String checkIn, String checkOut){
        //get current number of applications
        int currentNumberOfBookings = interactions.getNumberOfBookings();
        //attempt to create booking
        interactions.checkBookingNotCreated(firstName, surname, price, depositPaid, checkIn, checkOut);

        //assert number of bookings remains the same
        Assert.assertEquals(interactions.getNumberOfBookings(), currentNumberOfBookings);
    }

    @AfterMethod
    public void after(){
        driver.quit();
    }

    @DataProvider
    public Object[][] getBookingData() throws Exception{
        Object[][] testObjArray = ExcelUtils.getTableArray(System.getProperty("user.dir")+"\\data\\BookingNotCreatedData.xlsx","Sheet1");
        return (testObjArray);
    }
}
