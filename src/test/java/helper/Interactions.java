package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HotelPage;

public class Interactions {
    WebDriver driver;
    HotelPage hotelPage;
    WebDriverWait wait;

    public Interactions(WebDriver driver){
        this.driver = driver;
        hotelPage = new HotelPage(driver);
        wait = new WebDriverWait(driver, 10);
    }

    public void createBooking(String firstName, String surname, String price, String depositPaid, String checkIn, String checkOut)
    {
        hotelPage.enterFirstName(firstName);
        hotelPage.enterSurname(surname);
        hotelPage.enterPrice(price);
        hotelPage.isDepositPaid(Boolean.parseBoolean(depositPaid));
        hotelPage.setCheckInDate(checkIn);
        hotelPage.setCheckOutDate(checkOut);
        saveBooking();
    }

    public int getNumberOfBookings(){
        return hotelPage.getListOfBookings().size();
    }

    public void saveBooking(){
        final int currentNumberOfBookings = getNumberOfBookings();
        hotelPage.clickSave();

        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                int numberOfBookings = getNumberOfBookings();
                if (numberOfBookings == currentNumberOfBookings + 1)
                    return true;
                else
                    return false;
            }
        });
    }

    public void deleteBooking(){
        final int currentNumberOfBookings = getNumberOfBookings();
        hotelPage.clickDelete();
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                int numberOfBookings = getNumberOfBookings();
                if (numberOfBookings == currentNumberOfBookings - 1)
                    return true;
                else
                    return false;
            }
        });
    }

    public void deleteAllBookings(){
        hotelPage.deleteAllBookings();
    }

    public void checkBookingNotCreated(String firstName, String surname, String price, String depositPaid, String checkIn, String checkOut){
        completeBookingForm(firstName, surname, price, depositPaid, checkIn, checkOut);
        hotelPage.clickSave();
        refreshPage();
}

    private void completeBookingForm(String firstName, String surname, String price, String depositPaid, String checkIn, String checkOut){
        hotelPage.enterFirstName(firstName);
        hotelPage.enterSurname(surname);
        hotelPage.enterPrice(price);
        if(depositPaid.toLowerCase().equals("true")||depositPaid.toLowerCase().equals("false"))
            hotelPage.isDepositPaid(Boolean.parseBoolean(depositPaid));
        hotelPage.setCheckInDate(checkIn);
        hotelPage.setCheckOutDate(checkOut);
    }

    private void refreshPage(){
        driver.navigate().refresh();
        hotelPage = new HotelPage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(hotelPage.getSaveButton()));
    }
}