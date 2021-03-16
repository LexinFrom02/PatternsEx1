package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPageTest {
    RegistrationValues registrationValues = RegistrationPage.Registration.generateByFaker();

    String setDate(int daysAfterToday) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, daysAfterToday);
        return dateFormat.format(calendar.getTime());
    }

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTestReschedulingADate() {
        $("[data-test-id=\"city\"] input").setValue(registrationValues.getCity());
        $("[data-test-id=\"name\"] input").setValue(registrationValues.getName());
        $("[data-test-id=\"phone\"] input").setValue(registrationValues.getPhoneNumber());
        $("[data-test-id=\"agreement\"]").click();
        $(withText("Запланировать")).click();
        $(withText("Встреча успешно запланирована на")).shouldBe(visible);
        $(withText("Запланировать")).click();
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        $("[placeholder='Дата встречи']").setValue(setDate(5));
        $(withText("Запланировать")).click();
        $(byText("Перепланировать")).click();
        $("[data-test-id=success-notification] .notification__content").
                shouldHave(exactText("Встреча успешно запланирована на " + setDate(5)));    }
}
