import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import data.Filter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SimpleWebTest2MethodSource {

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    static Stream<Arguments> searchTestCoffee() {
        return Stream.of(
                Arguments.of(Filter.LIGHT, List.of("БРАЗИЛИЯ СУЛЬ-ДЕ-МИНАС", "КОЛУМБИЯ УИЛА", "ГВАТЕМАЛА САНТЬЯГО", "ЭФИОПИЯ СИДАМО",
                        "БАТЧ-БРЮ", "КЛИН СКИН", "КОЛУМБИЯ ДЕКАФ", "КОСТА-РИКА ТАРРАЗУ", "КЕНИЯ МАУНТ", "СУМАТРА ПИЛИХАН", "ЯРКИЙ КОФЕ ДЛЯ ФИЛЬТРА")),
                Arguments.of(Filter.MIDDLE, List.of("БРАЗИЛИЯ СЕРРАДО", "БРАЗИЛИЯ МОЖИАНА", "ЭФИОПИЯ ГУДЖИ", "ГУРМЕ", "СБАЛАНСИРОВАННЫЙ КОФЕ ДЛЯ ЭСПРЕССО",
                        "КОСТА-РИКА САН ХОСЕ", "ГВАТЕМАЛА ФЭНСИ", "КОЛУМБИЯ БОГОТА", "ЭФИОПИЯ ОРОМИЯ"," КИТАЙ СИМАО", "КЛИН СКИН"))
        );
    }

    @MethodSource("searchTestCoffee")
    @DisplayName("Поиск кофе в интернет магазине в зависимости от степени обжарки")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("WEB")
    })
    @ParameterizedTest
    void searchTestCoffee(Filter filter, List<String> expectedButtons) {
        open("https://shop.tastycoffee.ru/");
        $(".m-no-link").hover();
        $(byText("Кофе")).click();
        $(".catalogBox").$(byText("Степень обжарки")).click();
        $("#bs-select-2").$(byText(filter.getDesc())).click();
        $$(".nameTovar").filter(visible).shouldHave(CollectionCondition.texts(expectedButtons));
    }
}
