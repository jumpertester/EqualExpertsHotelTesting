package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HotelPage {
    WebDriver driver;
    WebElement firstNameField;
    WebElement surnameField;
    WebElement priceField;
    WebElement depositField;
    WebElement checkInField;
    WebElement checkOutField;
    WebElement saveButton;
    WebElement bookingRows;
    WebElement deleteButton;

    public HotelPage(WebDriver driver){
        this.driver = driver;
        firstNameField = driver.findElement(By.id("firstname"));
        surnameField = driver.findElement(By.id("lastname"));
        priceField = driver.findElement(By.id("totalprice"));
        depositField = driver.findElement(By.id("depositpaid"));
        checkInField = driver.findElement(By.id("checkin"));
        checkOutField = driver.findElement(By.id("checkout"));
        saveButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div[7]/input"));
        bookingRows = driver.findElement(By.id("bookings"));
        deleteButton = driver.findElement(By.xpath("//*[@class=\"row\"]/div[7]/input"));
    }

    public void enterFirstName(String firstName){
        firstNameField.sendKeys(firstName);
    }

    public void enterSurname(String surname){
        surnameField.sendKeys(surname);
    }

    public void enterPrice(String price){
        priceField.sendKeys(price);
    }

    public void isDepositPaid(boolean depositPaid){
        Select depositDropDown = new Select(depositField);
        if(depositPaid)
            depositDropDown.selectByVisibleText("true");
        else
            depositDropDown.selectByVisibleText("false");
    }

    public void setCheckInDate(String checkInDate){
        checkInField.sendKeys(checkInDate);
    }

    public void setCheckOutDate(String checkOutDate){
        checkOutField.sendKeys(checkOutDate);
    }

    public void clickSave(){
        saveButton.click();
    }

    public WebElement getSaveButton(){
        return saveButton;
    }

    public void clickDelete(){
        deleteButton.click();
    }

    public void deleteAllBookings(){
        List<WebElement> bookings = getListOfBookings();
        for(WebElement booking : bookings){
            booking.click();
        }
    }

    public List<WebElement> getListOfBookings(){
        return driver.findElements(By.xpath("//*[@class=\"row\"]/div[7]/input"));
    }

}
