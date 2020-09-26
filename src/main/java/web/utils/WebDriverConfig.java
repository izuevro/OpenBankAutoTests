package web.utils;

import org.aeonbits.owner.Config;

import java.net.URL;

import static org.aeonbits.owner.Config.*;

@LoadPolicy(LoadType.MERGE)
@Sources({"system:properties", "classpath:webdriver.properties"})
public interface WebDriverConfig extends Config {

    @Key("webdriver.base.url")
    String baseURL();

    @Key("webdriver.browser.name")
    String browserName();

    @Key("webdriver.browser.version")
    String browserVersion();

    @Key("webdriver.remote")
    boolean remote();

    @Key("webdriver.remote.url")
    URL remoteURL();

    @Key("webdriver.video.storage.url")
    String videoStorageUrl();

    @Key("webdriver.enable.vnc")
    boolean enableVNC();

    @Key("webdriver.enable.video")
    boolean enableVideo();

    @Key("webdriver.video.frame.rate")
    int videoFrameRate();

    @Key("webdriver.head.less")
    boolean browserHeadLess();

    @Key("webdriver.hold.open")
    boolean browserHoldOpen();
}
