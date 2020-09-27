package api.models;

import api.data.cards.CardsData;
import api.helpers.ParamsBuilder;
import api.specifications.CardsSpecification;
import io.qameta.allure.Step;

import static api.helpers.ParamsBuilder.paramsToMap;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

public class CardsModel {

    CardsSpecification cardsSpecification = new CardsSpecification();

    @Step("Отправить запрос на получение данных карты и проверить ответ на соответствие Json-схеме")
    public void validateJsonSchemaOfCards(String type, ParamsBuilder paramsBuilder) {
        given()
                .spec(cardsSpecification.getRequestSpecification())
                .queryParams(paramsToMap(paramsBuilder))
                .when()
                .get(type)
                .then()
                .spec(cardsSpecification.getResponseSpecification())
                .assertThat().body(matchesJsonSchemaInClasspath("cardsSchema.json"));
    }

    /**
     * Получим массив объектов данных по картам
     */

    @Step("Отправить запрос для карт типа \"{type}\" и сохранить ответ как POJO")
    public CardsData getCardsResponseData(String type, ParamsBuilder paramsBuilder) {
        CardsData[] cardsData =
                given()
                        .spec(cardsSpecification.getRequestSpecification())
                        .queryParams(paramsToMap(paramsBuilder))
                        .when()
                        .get(type)
                        .then()
                        .spec(cardsSpecification.getResponseSpecification())
                        .extract().as(CardsData[].class);
        return cardsData[0];
    }

    @Step("Проверить соответствие параметров ожидаемому результату")
    public void assertResponseData(CardsData expect, CardsData actual){
        assertThat(actual.getCurrencies().containsAll(expect.getCurrencies()));
        assertThat(actual.getProduct().getName()).isEqualTo(expect.getProduct().getName());
        assertThat(actual.getProduct().getType().getCode()).isEqualTo(expect.getProduct().getType().getCode());
        assertThat(actual.getCardType().getName()).isEqualTo(expect.getCardType().getName());
    }
}
