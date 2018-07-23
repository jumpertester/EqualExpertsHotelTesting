package testng;

import helper.Interactions;
import helper.Launcher;
import helper.ExcelUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateBooking {

    WebDriver driver;
    Interactions interactions;

    @BeforeMethod
    public void before(){
        driver = Launcher.getChromeDriver();
        Launcher.getBrowser(driver);
        interactions = new Interactions(driver);
    }

    @Test(description = "Add a new booking into the hotel system.", dataProvider = "getBookingData")
    public void createBookingTest(String firstName, String surname, String price, String depositPaid, String checkIn, String checkOut){
        //get current number of applications
        int currentNumberOfBookings = interactions.getNumberOfBookings();
        //create booking
        interactions.createBooking(firstName, surname, price, depositPaid, checkIn, checkOut);

        //assert number of bookings increased by 1
        Assert.assertEquals(interactions.getNumberOfBookings(), currentNumberOfBookings + 1 );
    }

    @AfterMethod
    public void after(){
        driver.quit();
    }

    @DataProvider
    public Object[][] getBookingData() throws Exception{
        Object[][] testObjArray = ExcelUtils.getTableArray(System.getProperty("user.dir")+"\\data\\CreateBookingTestData.xlsx","Sheet1");
        return (testObjArray);
    }

}
