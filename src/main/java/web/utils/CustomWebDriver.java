package web.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.codeborne.selenide.Browsers.*;
import static web.utils.BrowserOptions.*;

public class CustomWebDriver {

    private final WebDriverConfig webDriverConfig = ConfigFactory.newInstance().create(WebDriverConfig.class);


    protected RemoteWebDriver getChromeRemote() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(CHROME);
        capabilities.setVersion(webDriverConfig.browserVersion());
        capabilities.setCapability(ChromeOptions.CAPABILITY, getChromeOptions());
        capabilities.setCapability("enableVNC", webDriverConfig.enableVNC());
        capabilities.setCapability("enableVideo", webDriverConfig.enableVideo());
        capabilities.setCapability("videoFrameRate", webDriverConfig.videoFrameRate());
        return new RemoteWebDriver(webDriverConfig.remoteURL(), capabilities);
    }

    protected RemoteWebDriver getFirefoxRemote() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(FIREFOX);
        capabilities.setVersion(webDriverConfig.browserVersion());
        capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, getFirefoxOptions());
        capabilities.setCapability("enableVNC", webDriverConfig.enableVNC());
        capabilities.setCapability("enableVideo", webDriverConfig.enableVideo());
        capabilities.setCapability("videoFrameRate", webDriverConfig.videoFrameRate());
        return new RemoteWebDriver(webDriverConfig.remoteURL(), capabilities);
    }

    protected RemoteWebDriver getOperaRemote() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(OPERA);
        capabilities.setVersion(webDriverConfig.browserVersion());
        capabilities.setCapability(OperaOptions.CAPABILITY, getOperaOptions());
        capabilities.setCapability("enableVNC", webDriverConfig.enableVNC());
        capabilities.setCapability("enableVideo", webDriverConfig.enableVideo());
        capabilities.setCapability("videoFrameRate", webDriverConfig.videoFrameRate());
        return new RemoteWebDriver(webDriverConfig.remoteURL(), capabilities);
    }

    protected WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().driverVersion(webDriverConfig.browserVersion()).setup();
        return new ChromeDriver();
    }

    protected WebDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().driverVersion(webDriverConfig.browserVersion()).setup();
        return new FirefoxDriver();
    }

    protected WebDriver getOperaDriver() {
        WebDriverManager.operadriver().driverVersion(webDriverConfig.browserVersion()).setup();
        return new OperaDriver();
    }
}
