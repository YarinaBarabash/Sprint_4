package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmQuestionPage {

    private WebDriver driver;

    // Локатор вопроса о подтверждении заказа
    private By confirmQuestion = By.xpath(".//div[text()='Хотите оформить заказ?']");
    // Локатор кнопки подтверждения заказа
    private By confirmButton = By.xpath(".//button[text()='Да']");

    public ConfirmQuestionPage(WebDriver driver) {
        this.driver = driver;
    }

    public void isConfirmQuestionVisible() {
        driver.findElement(confirmQuestion).isDisplayed();
    }

    public void clickConfirmButton() {
        driver.findElement(confirmButton).click();
    }
}
