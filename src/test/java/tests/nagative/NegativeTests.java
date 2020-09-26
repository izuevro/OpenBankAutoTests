package tests.nagative;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

@Tag("negative")
public class NegativeTests extends BaseTest {

    @Test
    @Owner("Роман Зуев")
    @Tag("web")
    @DisplayName("Проверка отправки невалидных данных при оплате сотовой связи Мегафон")
    public void checkSendingInvalidDataOnMobilePaymentPageTest() {
        basePage
                .openURL("/payments")
                .closeAndCheckCookiesBlock();
        paymentsPage
                .clickPaymentsBlockElement("Сотовая связь")
                .clickPaymentsBlockElement("Мегафон");
        paymentsPageSteps
                .checkAndFillDataOnMobilePaymentPage(user, Condition.selected, "disabled");
    }
}
