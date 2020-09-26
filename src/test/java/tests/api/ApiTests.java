package tests.api;

import api.data.CityData;
import api.data.MobileProviderData;
import api.data.cards.CardsData;
import api.helpers.ParamsBuilder;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.BaseApiTest;

import static api.data.CityData.createCityData;
import static api.data.MobileProviderData.getMobileProviderFromConfig;
import static api.data.cards.CardsData.getCardsFromConfig;

public class ApiTests extends BaseApiTest {

    @Test
    @Owner("Роман Зуев")
    @Tag("api")
    @DisplayName("Проверка наличия города \"Москва\" в справочнике городов")
    public void checkPresenceCityInCitiesDirectoryTest() {
        CityData cityData = createCityData(1204, "Москва");
        Response response = citiesModel.getAllCitiesResponse();
        CityData cityFromResponse = citiesModel.extractCityFromResponse(response, cityData.getTitle());
        citiesModel.assertEqualsCityData(cityFromResponse, cityData);
    }

    @Test
    @Owner("Роман Зуев")
    @Tag("api")
    @DisplayName("Проверка соответствия ответа с данными поставщика \"Мегафон\"")
    public void checkEqualsMobileProviderDataResponseTest() {
        MobileProviderData megafonData = getMobileProviderFromConfig(dataConfig, "4");
        MobileProviderData responseData = mobileProviderModel.getMobileProviderResponseData(megafonData.getServiceId());
        mobileProviderModel.assertEqualsProviderData(responseData, megafonData);
    }

    @Test
    @Owner("Роман Зуев")
    @Tag("api")
    @DisplayName("Проверка соответствия ответа с данными мобильного провайдера \"Мегафон\" Json-схеме")
    public void validateJsonSchemaOfMobileProviderTest() {
        mobileProviderModel.validateJsonSchemaOfMobileProvider("4");
    }

    @Test
    @Owner("Роман Зуев")
    @Tag("api")
    @DisplayName("Проверка соответствия ответа данных о карте")
    public void assertEqualsDataOfCardTest() {
        ParamsBuilder paramsBuilder = ParamsBuilder.builder()
                .product("opencard")
                .design("default")
                .paymentSystem("mc")
                .build();
        CardsData responseData = cardsModel.getCardsResponseData("debit", paramsBuilder);
        int responseId = responseData.getId();
        CardsData expectData = getCardsFromConfig(dataConfig, "debit", responseId);
        cardsModel.assertResponseData(expectData, responseData);
    }
}
