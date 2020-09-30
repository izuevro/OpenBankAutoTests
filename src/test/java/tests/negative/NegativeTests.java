package tests.negative;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tests.BaseTest;

@Tag("negative")
public class NegativeTests extends BaseTest {

    @Tag("web")
    @Owner("Роман Зуев")
    @ParameterizedTest(name = "Тест #{index}. Проверка отправки невалидных данных при оплате сотовой связи \"{0}\"")
    @ValueSource(strings = {"Мегафон", "МТС", "TELE2"})
    public void checkSendingInvalidDataOnMobilePaymentPageTest(String payment) {
        basePage
                .openURL("/payments")
                .closeAndCheckCookiesBlock();
        paymentsPage
                .clickPaymentsBlockElement("Сотовая связь")
                .clickPaymentsBlockElement(payment);
        paymentsPageSteps
                .checkAndFillDataOnMobilePaymentPage(user, Condition.selected, "disabled");
    }
}
