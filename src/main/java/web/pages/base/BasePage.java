package web.pages.base;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.link;
import static io.qameta.allure.Allure.parameter;

public class BasePage {

    private final SelenideElement cookiesBlock = $(".cookies-agreement");
    private final SelenideElement cookiesCloseButton = $(".cookies-agreement__close-button");

    @Step("Кликнуть на иконку закрытия блока и проверить исчезновение блока Cookies")
    public BasePage closeAndCheckCookiesBlock() {
        cookiesCloseButton.click();
        cookiesBlock.shouldBe(hidden);
        return this;
    }

    @Step("Очистить поле и ввести значение \"{text}\"")
    public void clearFieldAndFillValue(SelenideElement element, String text) {
        element.clear();
        element.val(text);
    }

    @Step("Открыть вкладку и перейти на \"https://www.open.ru{url}\"")
    public BasePage openURL(String url) {
        link("Страница входа", url);
        parameter("Ссылка", url);

        open(url);
        return this;
    }

    @Step("Переключиться в фрейм")
    public BasePage switchToFrame(SelenideElement element) {
        switchTo().frame(element);
        return this;
    }

    @Step("Вернуться в основную DOM-модель")
    public BasePage switchDefaultContent() {
        switchTo().defaultContent();
        return this;
    }
}
