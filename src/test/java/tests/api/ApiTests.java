package tests.api;

import api.data.CityData;
import api.data.MobileProviderData;
import api.data.cards.CardsData;
import api.helpers.ParamsBuilder;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import tests.BaseApiTest;

import static api.data.CityData.createCityData;
import static api.data.MobileProviderData.getMobileProviderFromConfig;
import static api.data.cards.CardsData.getCardsFromConfig;

public class ApiTests extends BaseApiTest {

    @Tag("api")
    @Owner("Роман Зуев")
    @ParameterizedTest(name = "Тест #{index}. Проверка наличия города \"{1}\" c id=\"{0}\" в справочнике городов")
    @CsvSource(value = {"1204:Москва", "2309:Челябинск", "1751:Санкт-Петербург"}, delimiter = ':')
    public void checkPresenceCityInCitiesDirectoryTest(int id, String city) {
        CityData cityData = createCityData(id, city);
        Response response = citiesModel.getAllCitiesResponse();
        CityData cityFromResponse = citiesModel.extractCityFromResponse(response, cityData.getTitle());
        citiesModel.assertEqualsCityData(cityFromResponse, cityData);
    }

    @Tag("api")
    @Owner("Роман Зуев")
    @ParameterizedTest(name = "Тест #{index}. Проверка соответствия ответа с данными мобильного провайдера serviceId=\"{0}\"")
    @ValueSource(strings = {"4", "2", "6", "3", "1847"})
    public void checkEqualsMobileProviderDataResponseTest(String serviceId) {
        MobileProviderData expectData = getMobileProviderFromConfig(dataConfig, serviceId);
        MobileProviderData responseData = mobileProviderModel.getMobileProviderResponseData(expectData.getServiceId());
        mobileProviderModel.assertEqualsProviderData(responseData, expectData);
    }

    @Tag("api")
    @Owner("Роман Зуев")
    @ParameterizedTest(name = "Тест #{index}. Проверка соответствия ответа с данными мобильного провайдера serviceId=\"{0}\" Json-схеме")
    @ValueSource(strings = {"4", "2", "6", "3", "1847"})
    public void validateJsonSchemaOfMobileProviderTest(String serviceId) {
        mobileProviderModel.validateJsonSchemaOfMobileProvider(serviceId);
    }

    @Tag("api")
    @Owner("Роман Зуев")
    @ParameterizedTest(name = "Тест #{index}. Проверка соответствия ответа данных о {0}-{1} карте с параметрами [{2},{3}]")
    @CsvFileSource(resources = "/cardsApiTestsData.csv", numLinesToSkip = 1, delimiter = '|')
    public void assertEqualsDataOfCardTest(String type, String product, String design, String payment) {
        ParamsBuilder paramsBuilder = ParamsBuilder.builder()
                .product(product)
                .design(design)
                .paymentSystem(payment)
                .build();
        CardsData responseData = cardsModel.getCardsResponseData(type, paramsBuilder);
        int responseId = responseData.getId();
        CardsData expectData = getCardsFromConfig(dataConfig, type, responseId);
        cardsModel.assertResponseData(expectData, responseData);
    }

    @Tag("api")
    @Owner("Роман Зуев")
    @ParameterizedTest(name = "Тест #{index}. Проверка соответствия ответа с данными карты {0}-{1} и параметрами [{2},{3}], Json-схеме")
    @CsvFileSource(resources = "/cardsApiTestsData.csv", numLinesToSkip = 1, delimiter = '|')
    public void validateJsonSchemaOfCardsTest(String type, String product, String design, String payment) {
        ParamsBuilder paramsBuilder = ParamsBuilder.builder()
                .product(product)
                .design(design)
                .paymentSystem(payment)
                .build();
        cardsModel.validateJsonSchemaOfCards(type, paramsBuilder);
    }
}
