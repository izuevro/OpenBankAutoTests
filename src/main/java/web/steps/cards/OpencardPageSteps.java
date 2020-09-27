package web.steps.cards;

import com.codeborne.selenide.Condition;
import web.data.User;
import io.qameta.allure.Step;
import web.pages.cards.OpencardPage;

public class OpencardPageSteps {

    OpencardPage opencardPage = new OpencardPage();

    @Step("Проверить и заполнить форму заказа карты \"Opencard {type} {currency}\"")
    public OpencardPageSteps CheckAndFillFormOpencard(User user, String type, String currency, Condition isSelected, String buttonText) {
        opencardPage
                .selectCardType(type)
                .selectCardCurrency(currency)
                .checkEmptyAndFillLastNameInput(user.getSurname())
                .checkEmptyAndFillNameInput(user.getName())
                .checkEmptyAndFillPatronymicInput(user.getPatronymic())
                .checkEmptyAndFillCityDeliveryInput(user.getCityDelivery())
                .checkEmptyAndFillEmailInput(user.getEmail())
                .checkEmptyAndFillPhoneNumberInput(user.getPhoneNumber())
                .checkIsCitizenCheckbox(isSelected)
                .checkAndSelectIsAcceptedCheckbox()
                .checkContinueButtonText(buttonText);
        return this;
    }

    @Step("Проверить доступность кнопок валют USD и EUR при оформлении карты \"Opencard {type}\"")
    public OpencardPageSteps checkAvailabilityOfUsdAndEurCurrencyForOpencard(String type, Condition isVisible) {
        opencardPage
                .selectCardType(type)
                .checkUsdButtonCondition(isVisible)
                .checkEurButtonCondition(isVisible);
        return this;
    }
}
