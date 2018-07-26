package testng;

import helper.ExcelUtils;
import helper.Interactions;
import helper.Launcher;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DeleteBooking {

    WebDriver driver;
    Interactions interactions;

    @BeforeMethod
    public void before(){
        driver = Launcher.getChromeDriver();
        Launcher.getBrowser(driver);
        interactions = new Interactions(driver);
    }

    @Test(description = "Remove a booking from the hotel system.", dataProvider = "getBookingData")
    public void deleteBooking(String firstName, String surname, String price, String depositPaid, String checkIn, String checkOut){
        //create booking
        interactions.createBooking(firstName, surname, price, depositPaid, checkIn, checkOut);

        //get current number of bookings
        int currentNumberOfBookings = interactions.getNumberOfBookings();

        //delete a booking
        interactions.deleteBooking();

        //assert the number of bookings has decreased by 1
        Assert.assertEquals(interactions.getNumberOfBookings(), currentNumberOfBookings -1);
    }

    @AfterMethod
    public void after(){
        driver.quit();
    }

    @DataProvider
    public Object[][] getBookingData() throws Exception{
        Object[][] testObjArray = ExcelUtils.getTableArray(System.getProperty("user.dir")+"/data/DeleteBookingTestData.xlsx","Sheet1");
        return (testObjArray);
    }

}
