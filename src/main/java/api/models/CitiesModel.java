package api.models;

import api.data.CityData;
import api.specifications.CitiesSpecification;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class CitiesModel {

    CitiesSpecification citiesSpecification = new CitiesSpecification();

    @Step("Отправить запрос на получение всех городов и вернуть ответ")
    public Response getAllCitiesResponse() {
        return given()
                .spec(citiesSpecification.getRequestSpecification())
                .when()
                .get("/cities")
                .then()
                .spec(citiesSpecification.getResponseSpecification())
                .extract().response();
    }

    /**
     * Достаем из массива объектов городов нужный, и приводим в POJO
     */

    @Step("Получить объект города {city} из ответа и перевести в POJO")
    public CityData extractCityFromResponse(Response response, String city) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, ?> responseCity = response.path("find { it.title == '" + city + "' }");
        return mapper.convertValue(responseCity, CityData.class);
    }

    @Step("Проверить, что объект ответа соответствует объекту запроса")
    public void assertEqualsCityData(CityData responseData, CityData requestData) {
        assertThat(responseData).isEqualTo(requestData);
    }
}

