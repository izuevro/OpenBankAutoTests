package web.pages.cards;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import web.pages.base.BasePage;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.parameter;

public class CardsPage extends BasePage {

    private final SelenideElement cardList = $(".card-list");

    @Step("Выбрать карту \"{cardName}\"")
    public CardsPage selectCard(String cardName) {
        parameter("Вид карты", cardName);

        cardList.$(byText(cardName)).click();
        return this;
    }
}
