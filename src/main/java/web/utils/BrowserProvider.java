package web.utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;

public class BrowserProvider {

    private final WebDriverConfig webDriverConfig = ConfigFactory.newInstance().create(WebDriverConfig.class);

    /**
     * Метод настройки типа браузера и базового url
     */

    public void browserSetUp() {
        Configuration.browser = WebDriverProvider.class.getName();
        Configuration.baseUrl = webDriverConfig.baseURL();
        Configuration.headless = webDriverConfig.browserHeadLess();
        Configuration.holdBrowserOpen = webDriverConfig.browserHoldOpen();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .savePageSource(true).screenshots(true));
    }
}
