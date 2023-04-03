import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class SimpleWebTestCsvSource {

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @CsvSource(value = {
            "Наборы, НАБОР С КОФЕ ДЛЯ ЭСПРЕССО",
            "Рекомендуем, БРАЗИЛИЯ МОЖИАНА"
    })
    @ParameterizedTest
    @DisplayName("Поиск кофе в интернет магазине")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("WEB")
    })
    void searchCoffee(String testData, String expectedText) {
        open("https://shop.tastycoffee.ru/");
        $(".m-no-link").hover();
        $(byText("Кофе")).click();
        $(".radioMenu-wrap").$(byText(testData)).click();
        $(".for_paginate").shouldHave(Condition.text(expectedText));
    }
}
