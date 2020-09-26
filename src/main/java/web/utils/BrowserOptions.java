package web.utils;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaOptions;

public class BrowserOptions {

    protected static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--lang=ru");

        return chromeOptions;
    }

    protected static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        firefoxOptions.addArguments("--no-sandbox");
        firefoxOptions.addArguments("--disable-notifications");
        firefoxOptions.addArguments("--disable-infobars");
        firefoxOptions.addArguments("--lang=ru");

        return firefoxOptions;
    }

    protected static OperaOptions getOperaOptions() {
        OperaOptions operaOptions = new OperaOptions();

        operaOptions.addArguments("--no-sandbox");
        operaOptions.addArguments("--disable-notifications");
        operaOptions.addArguments("--disable-infobars");
        operaOptions.addArguments("--lang=ru");

        return operaOptions;
    }
}
