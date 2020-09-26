package web.pages.cards;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import web.pages.base.BasePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.parameter;

public class OpencardPage extends BasePage {

    private final SelenideElement cardFrame = $("#iframe");

    private final SelenideElement visaButton = $("#visa");
    private final SelenideElement mcButton = $("#mc");
    private final SelenideElement mirButton = $("#mir");
    private final SelenideElement rubButton = $("#rub");
    private final SelenideElement usdButton = $("#usd");
    private final SelenideElement eurButton = $("#eur");

    private final SelenideElement lastNameInput = $(byName("lastName"));
    private final SelenideElement nameInput = $(byName("name"));
    private final SelenideElement patronymicInput = $(byName("patronymic"));
    private final SelenideElement cityDeliveryInput = $(byName("cityDelivery"));
    private final SelenideElement emailInput = $(byName("email"));
    private final SelenideElement phoneNumberInput = $(byName("phoneNumber"));
    private final SelenideElement isCitizenCheckbox = $(byName("isCitizen"));
    private final SelenideElement isAcceptedCheckbox = $(byName("isAccepted"));
    private final SelenideElement continueButton = $("[data-aqa=button-get-sms]");

    @Step("Проверить видимость кнопки \"Visa\"")
    public OpencardPage checkVisaButtonCondition(Condition isVisible) {
        visaButton.shouldBe(isVisible);
        return this;
    }

    @Step("Проверить видимость кнопки \"MasterCard\"")
    public OpencardPage checkMcButtonCondition(Condition isVisible) {
        mcButton.shouldBe(isVisible);
        return this;
    }

    @Step("Проверить видимость кнопки \"MIR\"")
    public OpencardPage checkMirButtonCondition(Condition isVisible) {
        mirButton.shouldBe(isVisible);
        return this;
    }

    @Step("Проверить видимость кнопки \"RUB\"")
    public OpencardPage checkRubButtonCondition(Condition isVisible) {
        rubButton.shouldBe(isVisible);
        return this;
    }

    @Step("Проверить видимость кнопки \"USD\"")
    public OpencardPage checkUsdButtonCondition(Condition isVisible) {
        usdButton.shouldBe(isVisible);
        return this;
    }

    @Step("Проверить видимость кнопки \"EUR\"")
    public OpencardPage checkEurButtonCondition(Condition isVisible) {
        eurButton.shouldBe(isVisible);
        return this;
    }

    @Step("Выбрать тип карты \"{type}\"")
    public OpencardPage selectCardType(String type) {
        switch (type) {
            case "Visa":
                visaButton.click();
                return this;
            case "Mastercard":
                mcButton.click();
                return this;
            case "MIR":
                mirButton.click();
                return this;
            default:
                throw new RuntimeException("Неизвестный тип карты: " + type);
        }
    }

    @Step("Выбрать валюту \"{currency}\"")
    public OpencardPage selectCardCurrency(String currency) {
        switch (currency) {
            case "RUB":
                rubButton.click();
                return this;
            case "USD":
                usdButton.click();
                return this;
            case "EUR":
                eurButton.click();
                return this;
            default:
                throw new RuntimeException("Неизвестная валюта: " + currency);
        }
    }

    @Step("Проверить, что поле ввода \"Фамилия\" пустое и заполнить его значением \"{surname}\"")
    public OpencardPage checkEmptyAndFillLastNameInput(String surname) {
        parameter("Фамилия", surname);

        switchToFrame(cardFrame);
        lastNameInput.shouldBe(empty);
        clearFieldAndFillValue(lastNameInput, surname);
        switchDefaultContent();
        return this;
    }

    @Step("Проверить, что поле ввода \"Имя\" пустое и заполнить его значением \"{name}\"")
    public OpencardPage checkEmptyAndFillNameInput(String name) {
        parameter("Имя", name);

        switchToFrame(cardFrame);
        nameInput.shouldBe(empty);
        clearFieldAndFillValue(nameInput, name);
        switchDefaultContent();
        return this;
    }

    @Step("Проверить, что поле ввода \"Отчество\" пустое и заполнить его значением \"{patronymic}\"")
    public OpencardPage checkEmptyAndFillPatronymicInput(String patronymic) {
        parameter("Отчество", patronymic);

        switchToFrame(cardFrame);
        patronymicInput.shouldBe(empty);
        clearFieldAndFillValue(patronymicInput, patronymic);
        switchDefaultContent();
        return this;
    }

    @Step("Проверить, что поле ввода \"Населенный пункт\" пустое и заполнить его значением \"{cityDelivery}\"")
    public OpencardPage checkEmptyAndFillCityDeliveryInput(String cityDelivery) {
        parameter("Населенный пункт", cityDelivery);

        switchToFrame(cardFrame);
        cityDeliveryInput.shouldBe(empty);
        clearFieldAndFillValue(cityDeliveryInput, cityDelivery);
        switchDefaultContent();
        return this;
    }

    @Step("Проверить, что поле ввода \"Email\" пустое и заполнить его значением \"{email}\"")
    public OpencardPage checkEmptyAndFillEmailInput(String email) {
        parameter("Email", email);

        switchToFrame(cardFrame);
        emailInput.shouldBe(empty);
        clearFieldAndFillValue(emailInput, email);
        switchDefaultContent();
        return this;
    }

    @Step("Проверить, что поле ввода \"Номер телефона\" пустое и заполнить его значением \"{phoneNumber}\"")
    public OpencardPage checkEmptyAndFillPhoneNumberInput(String phoneNumber) {
        parameter("Номер телефона", phoneNumber);

        switchToFrame(cardFrame);
        phoneNumberInput.shouldBe(empty);
        clearFieldAndFillValue(phoneNumberInput, phoneNumber);
        switchDefaultContent();
        return this;
    }

    @Step("Проверить чекбокс подтверждения гражданства")
    public OpencardPage checkIsCitizenCheckbox(Condition isSelected) {
        switchToFrame(cardFrame);
        isCitizenCheckbox.shouldBe(isSelected);
        switchDefaultContent();
        return this;
    }

    @Step("Проверить, что checkbox соглашения не выбран и выбрать его")
    public OpencardPage checkAndSelectIsAcceptedCheckbox() {
        switchToFrame(cardFrame);
        isAcceptedCheckbox.shouldNotBe(selected).click();
        switchDefaultContent();
        return this;
    }

    @Step("Проверить, что кнопка содержит текст \"{buttonText}\"")
    public OpencardPage checkContinueButtonText(String buttonText) {
        switchToFrame(cardFrame);
        continueButton.shouldHave(text(buttonText));
        switchDefaultContent();
        return this;
    }
}
