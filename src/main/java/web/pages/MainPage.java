package web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import web.pages.base.BasePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.parameter;

public class MainPage extends BasePage {

    private final SelenideElement headerSubNavMenu = $(".main-page-header__sub-nav-wrap");
    private final SelenideElement headerCollapseMenu = $(".main-page-header__collapse-menu");
    private final SelenideElement headerCollapseMenuCloseButton = $(".main-page-header__links-cross");

    private final SelenideElement headerNavCitySelectLink = $(".main-page-header__main-nav-city-select-row");
    private final SelenideElement cityBlock = $(".ant-modal-content");
    private final SelenideElement cityInput = $(".ant-input");
    private final SelenideElement cityResultsLink = $(".geolocation-modal__list-item-title");


    @Step("Кликнуть на раздел меню \"{text}\" и проверить появление выпадающего меню")
    public MainPage clickAndCheckHeaderSubNavMenuItemBox(String text) {
        parameter("Раздел меню", text);

        headerSubNavMenu.$(byText(text)).closest("div").click();
        headerCollapseMenu.shouldBe(visible);
        return this;
    }

    @Step("Кликнуть на раздел в выпадающем меню \"{text}\"")
    public MainPage clickDropDownMenuItem(String text) {
        parameter("Раздел в выпадающем меню", text);

        headerCollapseMenu.$(byText(text)).closest("div").click();
        return this;
    }

    @Step("Кликнуть на иконку закрытия выпадающего меню и проверить его отсутствие")
    public MainPage closeAndCheckHeaderCollapseMenu() {
        headerCollapseMenuCloseButton.click();
        headerCollapseMenu.shouldBe(hidden);
        return this;
    }

    @Step("Проверить, что текст ссылки выбора города = \"{cityName}\"")
    public MainPage checkTextOfNavCitySelectLink(String cityName) {
        headerNavCitySelectLink.shouldHave(text(cityName));
        return this;
    }

    @Step("Кликнуть на кнопку выбора города и проверить появление блока выбора")
    public MainPage clickAndCheckAppearanceCityBlock() {
        headerNavCitySelectLink.click();
        cityBlock.shouldBe(visible);
        return this;
    }

    @Step("Заполнить поле с названием города \"{cityName}\" и проверить результат поиска")
    public MainPage fillFieldAndClickCityInput(String cityName) {
        parameter("Название заполняемого города", cityName);

        clearFieldAndFillValue(cityInput, cityName);
        cityResultsLink.shouldHave(text(cityName)).click();
        switchDefaultContent();
        return this;
    }
}
