package web.utils;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.annotation.Nonnull;

public class WebDriverProvider implements com.codeborne.selenide.WebDriverProvider {

    private final CustomWebDriver customWebDriver = new CustomWebDriver();
    private final WebDriverConfig webDriverConfig = ConfigFactory.newInstance().create(WebDriverConfig.class);

    /**
     * Метод инициализации вебдрайвера
     */

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull DesiredCapabilities desiredCapabilities) {
        if (webDriverConfig.remote()) {
            switch (webDriverConfig.browserName()) {
                case "chrome":
                    return customWebDriver.getChromeRemote();
                case "firefox":
                    return customWebDriver.getFirefoxRemote();
                case "opera":
                    return customWebDriver.getOperaRemote();
            }
        } else {
            switch (webDriverConfig.browserName()) {
                case "chrome":
                    return customWebDriver.getChromeDriver();
                case "firefox":
                    return customWebDriver.getFirefoxDriver();
                case "opera":
                    return customWebDriver.getOperaDriver();
            }
        }
        throw new RuntimeException("Unknown browser: " + webDriverConfig.browserName());
    }
}

