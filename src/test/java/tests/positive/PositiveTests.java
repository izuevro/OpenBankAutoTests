package tests.positive;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

@Tag("positive")
public class PositiveTests extends BaseTest {

    @Test
    @Owner("Роман Зуев")
    @Tag("web")
    @DisplayName("Проверка открытия и закрытия submenu")
    public void checkOpenAndCloseSubMenuTest() {
        basePage
                .openURL("")
                .closeAndCheckCookiesBlock();
        mainPage.
                clickAndCheckHeaderSubNavMenuItemBox("Кредиты")
                .closeAndCheckHeaderCollapseMenu();
    }

    @Test
    @Owner("Роман Зуев")
    @Tag("web")
    @DisplayName("Проверка выбора региона в навигационном меню (в header)")
    public void checkRegionInHeaderNavigationMenuTest() {
        basePage
                .openURL("")
                .closeAndCheckCookiesBlock();
        mainPage
                .checkTextOfNavCitySelectLink("Другой город")
                .clickAndCheckAppearanceCityBlock()
                .fillFieldAndClickCityInput("Москва")
                .checkTextOfNavCitySelectLink("Москва");
    }

    @Test
    @Owner("Роман Зуев")
    @Tag("web")
    @DisplayName("Проверка и заполнение формы заказа карты Opencard Visa USD")
    public void CheckAndFillFormOpencardVisaUsdTest() {
        basePage
                .openURL("")
                .closeAndCheckCookiesBlock();
        mainPage
                .clickAndCheckHeaderSubNavMenuItemBox("Карты")
                .clickDropDownMenuItem("Дебетовые карты");
        cardsPage
                .selectCard("Opencard");
        opencardPageSteps
                .CheckAndFillFormOpencard(user, "Visa", "USD", Condition.selected, "Продолжить");
    }

    @Test
    @Owner("Роман Зуев")
    @Tag("web")
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


