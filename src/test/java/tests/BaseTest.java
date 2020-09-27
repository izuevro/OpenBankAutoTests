package tests;

import dataconfig.DataConfig;
import web.data.User;
import web.utils.BrowserProvider;
import web.utils.WebDriverConfig;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import web.pages.MainPage;
import web.pages.base.BasePage;
import web.pages.cards.CardsPage;
import web.pages.payments.PaymentsPage;
import web.steps.cards.OpencardPageSteps;
import web.steps.payments.PaymentsPageSteps;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static web.helpers.AttachmentsHelper.*;

public class BaseTest {

    private final WebDriverConfig webDriverConfig = ConfigFactory.newInstance().create(WebDriverConfig.class);
    private final DataConfig DataConfig = ConfigFactory.newInstance().create(DataConfig.class);


    protected User user = new User().getRandomUserFromConfig(DataConfig);

    protected BasePage basePage = new BasePage();
    protected MainPage mainPage = new MainPage();
    protected PaymentsPage paymentsPage = new PaymentsPage();
    protected PaymentsPageSteps paymentsPageSteps = new PaymentsPageSteps();
    protected CardsPage cardsPage = new CardsPage();
    protected OpencardPageSteps opencardPageSteps = new OpencardPageSteps();

    @BeforeAll
    @Step("Применение настроек перед запуском тестов")
    public static void beforeAll() {
        new BrowserProvider().browserSetUp();
    }

    @AfterEach
    @Step("Добавление аттачей и закрытие драйвера")
    public void afterEach() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Console logs", getBrowserConsoleLogs());
        if (webDriverConfig.remote()) {
            attachVideo();
        }
        closeWebDriver();
    }
}
