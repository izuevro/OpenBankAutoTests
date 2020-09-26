package dataconfig;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.*;

@LoadPolicy(LoadType.MERGE)
@Sources({"system:properties", "classpath:data.properties"})
public interface DataConfig extends Config {

    @Key("data.user")
    String userData();

    @Key("data.mobile.provider")
    String MobileProviderData();

    @Key("data.debit")
    String DebitCardsData();

    @Key("data.credit")
    String CreditCardsData();
}
