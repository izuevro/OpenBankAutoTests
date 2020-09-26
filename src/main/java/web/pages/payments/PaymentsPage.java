package web.pages.payments;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import web.pages.base.BasePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.parameter;

public class PaymentsPage extends BasePage {

    private final SelenideElement paymentsFrame = $("#pay-service-p2p");
    private final SelenideElement paymentsBlock = $(".wocb-payments-grid");
    private final SelenideElement paymentsBreadCrumbs = $(".wocb-payments-breadcrumbs-title");
    private final SelenideElement paymentButton = $("#iButtonPayment");
    private final SelenideElement paymentAgreeCheckbox = $("#iAgree");
    private final SelenideElement paymentPhoneNumberInput = $("#iParam_account");
    private final SelenideElement paymentCardNumberInput = $("#iPAN");
    private final SelenideElement paymentCardExpDateInput = $("#iExpiry");
    private final SelenideElement paymentCardCvcInput = $("#iCVC");
    private final SelenideElement paymentAmountInput = $("#iAmount");
    private final SelenideElement paymentEmailInput = $("#iEmail");


    @Step("Перейти в раздел меню \"{text}\"")
    public PaymentsPage clickPaymentsBlockElement(String text) {
        parameter("Раздел меню", text);

        switchToFrame(paymentsFrame);
        paymentsBlock.$(byText(text)).click();
        paymentsBreadCrumbs.shouldHave(text(text));
        switchDefaultContent();
        return this;
    }

    @Step("Проверить поле ввода \"Номера телефона\" и заполнить его значением \"{phoneNumber}\"")
    public PaymentsPage checkAndFillPaymentPhoneNumberInput(String phoneNumber) {
        parameter("Номер телефона", phoneNumber);

        switchToFrame(paymentsFrame);
        paymentPhoneNumberInput.shouldBe(empty);
        clearFieldAndFillValue(paymentPhoneNumberInput, phoneNumber);
        switchDefaultContent();
        return this;
    }

    @Step("Проверить поле ввода \"Номер карты\" и заполнить его значением \"{cardNumber}\"")
    public PaymentsPage checkAndFillPaymentCardNumberInput(String cardNumber) {
        parameter("Номер карты", cardNumber);

        switchToFrame(paymentsFrame);
        paymentCardNumberInput.shouldBe(empty);
        clearFieldAndFillValue(paymentCardNumberInput, cardNumber);
        switchDefaultContent();
        return this;
    }

    @Step("Проверить поле ввода \"Срок действия карты\" и заполнить его значением \"{cardExpDate}\"")
    public PaymentsPage checkAndFillPaymentCardExpDateInput(String cardExpDate) {
        parameter("Срок действия карты", cardExpDate);

        switchToFrame(paymentsFrame);
        paymentCardExpDateInput.shouldBe(empty);
        clearFieldAndFillValue(paymentCardExpDateInput, cardExpDate);
        switchDefaultContent();
        return this;
    }

    @Step("Проверить поле ввода \"Cvc карты\" и заполнить его значением \"{cardCvc}\"")
    public PaymentsPage checkAndFillPaymentCardCvcInput(String cardCvc) {
        parameter("Cvc карты", cardCvc);

        switchToFrame(paymentsFrame);
        paymentCardCvcInput.shouldBe(empty);
        clearFieldAndFillValue(paymentCardCvcInput, cardCvc);
        switchDefaultContent();
        return this;
    }

    @Step("Проверить поле ввода \"Сумма\" и заполнить его значением \"{amount}\"")
    public PaymentsPage checkAndFillPaymentAmountInput(String amount) {
        parameter("Сумма", amount);

        switchToFrame(paymentsFrame);
        paymentAmountInput.shouldBe(empty);
        clearFieldAndFillValue(paymentAmountInput, amount);
        switchDefaultContent();
        return this;
    }

    @Step("Проверить поле ввода \"Email\" и заполнить его значением \"{email}\"")
    public PaymentsPage checkAndFillPaymentEmailInput(String email) {
        parameter("Email", email);

        switchToFrame(paymentsFrame);
        paymentEmailInput.shouldBe(empty);
        clearFieldAndFillValue(paymentEmailInput, email);
        switchDefaultContent();
        return this;
    }

    @Step("Проверить значение чекбокса согласия с условиями оферты")
    public PaymentsPage checkPaymentAgreeCheckbox(Condition isSelected) {
        switchToFrame(paymentsFrame);
        paymentAgreeCheckbox.shouldBe(isSelected);
        switchDefaultContent();
        return this;
    }

    @Step("Проверить наличие атрибута \"{attribute}\" у кнопки платежа")
    public PaymentsPage checkPaymentButtonAttribute(String attribute) {
        switchToFrame(paymentsFrame);
        paymentButton.click();
        paymentButton.shouldHave(attribute(attribute));
        switchDefaultContent();
        return this;
    }
}
