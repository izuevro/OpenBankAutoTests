package web.steps.payments;

import com.codeborne.selenide.Condition;
import web.data.User;
import io.qameta.allure.Step;
import web.pages.payments.PaymentsPage;

public class PaymentsPageSteps {

    PaymentsPage paymentsPage = new PaymentsPage();

    @Step("Заполнить форму оплаты мобильной связи и проверить кнопку \"Оплатить\"")
    public PaymentsPageSteps checkAndFillDataOnMobilePaymentPage(User user, Condition isSelected, String attribute) {
        paymentsPage
                .checkAndFillPaymentPhoneNumberInput(user.getPhoneNumber())
                .checkAndFillPaymentCardNumberInput(user.getCardNumber())
                .checkAndFillPaymentCardExpDateInput(user.getCardExpDate())
                .checkAndFillPaymentCardCvcInput(user.getCardCvc())
                .checkAndFillPaymentAmountInput(user.getAmount())
                .checkAndFillPaymentEmailInput(user.getEmail())
                .checkPaymentAgreeCheckbox(isSelected)
                .checkPaymentButtonAttribute(attribute);
        return this;
    }
}
