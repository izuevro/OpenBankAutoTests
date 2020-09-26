package tests;

import api.models.CardsModel;
import api.models.CitiesModel;
import api.models.MobileProvidersModel;
import dataconfig.DataConfig;
import org.aeonbits.owner.ConfigFactory;

public class BaseApiTest {

    protected final DataConfig dataConfig = ConfigFactory.newInstance().create(DataConfig.class);
    protected CitiesModel citiesModel = new CitiesModel();
    protected MobileProvidersModel mobileProviderModel = new MobileProvidersModel();
    protected CardsModel cardsModel = new CardsModel();
}
