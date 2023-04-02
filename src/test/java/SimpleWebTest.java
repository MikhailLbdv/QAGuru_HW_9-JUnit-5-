import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SimpleWebTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @ValueSource(strings = {
            "Наборы", "Рекомендуем"
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
        $(".radioMenu").$(byText(testData)).click();
    }

    @Disabled("Проверка временно исключается")
    @DisplayName("Вход в личный кабинет МГТС")
    @Test
    void searchTwitter() {
        open("https://mgts.ru/");
        $(".header_login-button").click();
        $(".mainContainer").$(byText("Вход через Mos.ru")).click();
    }
}
