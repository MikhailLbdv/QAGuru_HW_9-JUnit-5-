import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SimpleWebTestMethodSource {


    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

//    static Stream<Arguments> testCoffee() {
//        return Stream.of(
//               Arguments.of(TestData.По_возрастанию_цены, List.of("БЭРРИ","ЭФИОПИЯ СИДАМО","КЕНИЯ МАУНТ")),
//                Arguments.of(TestData.По_убыванию_цены, List.of("КОФЕ + КОФЕМОЛКА + АЭРОПРЕСС","КОФЕ + КОФЕМОЛКА + КРУЖКА","КОФЕ + ФРЕНЧ-ПРЕСС + КОФЕМОЛКА"))
//        );
//    }

//    @MethodSource("testCoffee")
    @ParameterizedTest
    void searchCoffee() {
        open("https://shop.tastycoffee.ru/");
        $(".m-no-link").hover();
        $(byText("Кофе")).click();
        $(".select-wrap").click();
        $(".filter-option-inner").$(byText("")).click();
//        $$("#bs-select-1-0").find(Condition.text(testData.name())).click();
//        $$(".for_paginate").filter(visible).shouldHave(CollectionCondition.texts(expectedButtons));
    }
}
