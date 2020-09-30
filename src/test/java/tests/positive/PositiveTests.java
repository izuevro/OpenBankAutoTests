package tests.positive;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import tests.BaseTest;

@Tag("positive")
public class PositiveTests extends BaseTest {

    @Tag("web")
    @Owner("Роман Зуев")
    @ParameterizedTest(name = "Тест #{index}. Проверка открытия и закрытия submenu \"{0}\"")
    @ValueSource(strings = {"Кредиты", "Карты", "Ипотека", "Вклады", "Платежи и переводы"})
    public void checkOpenAndCloseSubMenuTest(String item) {
        basePage
                .openURL("")
                .closeAndCheckCookiesBlock();
        mainPage
                .clickAndCheckHeaderSubNavMenuItemBox(item)
                .closeAndCheckHeaderCollapseMenu();
    }

    @Tag("web")
    @Owner("Роман Зуев")
    @ParameterizedTest(name = "Тест #{index}. Проверка выбора города \"{0}\" в навигационном меню (в header)")
    @ValueSource(strings = {"Москва", "Челябинск", "Санкт-Петербург"})
    public void checkRegionInHeaderNavigationMenuTest(String city) {
        basePage
                .openURL("")
                .closeAndCheckCookiesBlock();
        mainPage
                .checkTextOfNavCitySelectLink("Другой город")
                .clickAndCheckAppearanceCityBlock()
                .fillFieldAndClickCityInput(city)
                .checkTextOfNavCitySelectLink(city);
    }

    @Tag("web")
    @Owner("Роман Зуев")
    @ParameterizedTest(name = "Тест #{index}. Проверка и заполнение формы заказа карты Opencard [{arguments}]")
    @CsvFileSource(resources = "/cardsWebTestsData.csv", numLinesToSkip = 1, delimiter = '|')
    public void CheckAndFillFormOpencardCardTest(String type, String currency) {
        basePage
                .openURL("")
                .closeAndCheckCookiesBlock();
        mainPage
                .clickAndCheckHeaderSubNavMenuItemBox("Карты")
                .clickDropDownMenuItem("Дебетовые карты");
        cardsPage
                .selectCard("Opencard");
        opencardPageSteps
                .CheckAndFillFormOpencard(user, type, currency, Condition.selected, "Продолжить");
    }

    @Tag("web")
    @Owner("Роман Зуев")
    @Test
    @DisplayName("Проверка недоступности для выбора валют USD и EUR при оформлении карты Opencard MIR")
    public void checkNotAvailabilityOfUsdAndEurCurrencyForOpencardMirTest() {
        basePage
                .openURL("")
                .closeAndCheckCookiesBlock();
        mainPage
                .clickAndCheckHeaderSubNavMenuItemBox("Карты")
                .clickDropDownMenuItem("Дебетовые карты");
        cardsPage
                .selectCard("Opencard");
        opencardPageSteps
                .checkAvailabilityOfUsdAndEurCurrencyForOpencard("MIR", Condition.hidden);
    }
}


