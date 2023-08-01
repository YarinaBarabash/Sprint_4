package ru.yandex.practicum;

import PageObject.*;
import PageObject.Consts.OrderButton;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class OrderScooterTest extends BaseTest {

    // Поля класса
    // Kнопка заказа (верхняя или нижняя)
    private final String orderButton;
    // Имя заказчика
    private final String name;
    // Фамилия заказчика
    private final String surname;
    // Адрес доставки
    private final String address;
    // Номер телефона
    private final String phoneNumber;
    // Период аренды из выпадающего меню
    private final String rentalPeriod;
    // Цвет самоката
    private final String colour;
    // Комментарий
    private final String comment;
    // Ожидаемый результат
    private boolean actual;

    public OrderScooterTest(String orderButton, String name, String surname, String address, String phoneNumber,
                            String rentalPeriod, String colour, String comment)
    {
        this.orderButton = orderButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.rentalPeriod = rentalPeriod;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderFormData() {
        return new Object[][] {
                { OrderButton.TOP_BUTTON, "Барабаш", "Ярина", "Москва", "89131234567", "сутки", "black", "1" },
                { OrderButton.BOTTOM_BUTTON, "Мухаммад",
                        "Али",
                        "г. Благовещенск, ул. Полевая, дом 3 квартира 33",
                        "+79631234567", "семеро суток", "grey",
                        "Не представляю как вы привезете скутер в Благовещенск, но мне очень надо, "
                        + "плачу любые деньги, но не больше 2 тысяч рублей. " +
                                "Повторяю очень надо, плачу любые деньги"
                },
        };
    }

    // Тест: проверяем весь флоу позитивного сценария заказа самоката
    @Test
    public void checkOrderScooterValidData_expectScooterIsOrdered() {
        super.implicitlyWait(3);

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickOrderButton(orderButton);

        OrderFormPage orderFormPage = new OrderFormPage(driver);
        orderFormPage.fillAllRequiredFields(name, surname, address, phoneNumber);
        orderFormPage.clickNextButton();

        RentPage rentPage = new RentPage(driver);
        rentPage.waitRentPageWillBeLoaded();
        rentPage.fillRequiredFields(rentalPeriod, colour, comment);
        rentPage.clickOrderButton();

        ConfirmQuestionPage confirmQuestionPage = new ConfirmQuestionPage(driver);
        confirmQuestionPage.clickConfirmButton();

        SuccessOrderCreationPage successOrderCreationPage = new SuccessOrderCreationPage(driver);
        actual = successOrderCreationPage.isSuccessOrderCreationMessageVisible();
        Assert.assertTrue("Expected: a message is displayed that the order was created successfully ",
                actual);
    }
}
