package web.helpers;

import com.codeborne.selenide.Selenide;
import web.utils.WebDriverConfig;
import io.qameta.allure.Attachment;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class AttachmentsHelper {

    private static final WebDriverConfig webDriverConfig = ConfigFactory.newInstance().create(WebDriverConfig.class);

    @Attachment(value = "{attachName}", type = "text/plain")
    public static String attachAsText(String attachName, String message) {
        return message;
    }

    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] attachScreenshot(String attachName) {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] attachPageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String attachVideo() {
        String videoUrl = getVideoUrl();
        System.out.println(videoUrl);

        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + videoUrl + "' type='video/mp4'></video></body></html>";
    }

    public static String getVideoUrl() {
        try {
            return new URL(webDriverConfig.videoStorageUrl() + getSessionId() + ".mp4") + "";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getSessionId() {
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString().replace("selenoid", "");
    }

    public static String getBrowserConsoleLogs() {
        return String.join("\n", Selenide.getWebDriverLogs(BROWSER));
    }
}
