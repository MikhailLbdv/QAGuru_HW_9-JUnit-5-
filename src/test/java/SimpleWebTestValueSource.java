import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SimpleWebTestValueSource {

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @ValueSource(strings = {
            "Яркий эспрессо", "Сбалансированный эспрессо"
    })
    @ParameterizedTest
    @DisplayName("Поиск кофе в интернет магазине")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("WEB")
    })
    void searchCoffee(String testData) {
        open("https://shop.tastycoffee.ru/");
        $(".m-no-link").hover();
        $(byText("Кофе")).click();
        $(".catalog_form_filters").shouldHave(text(testData));
    }
}
