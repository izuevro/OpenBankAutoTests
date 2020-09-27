package api.models;

import api.data.MobileProviderData;
import api.helpers.OverrideContentTypeFilter;
import api.specifications.MobileProvidersSpecification;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

public class MobileProvidersModel {

    MobileProvidersSpecification mobileProviderSpecification = new MobileProvidersSpecification();

    @Step("Отправить запрос на получение данных мобильного провайдера и проверить ответ на соответствие Json-схеме")
    public void validateJsonSchemaOfMobileProvider(String serviceId) {
        given()
                .formParam("serviceId", serviceId)
                .spec(mobileProviderSpecification.getRequestSpecification())
                .when()
                .post("/providers.do")
                .then()
                .spec(mobileProviderSpecification.getResponseSpecification())
                .assertThat().body(matchesJsonSchemaInClasspath("mobileProviderSchema.json"));
    }

    /**
     * Тут получим не весь ответ в виде POJO, а только часть из основного объекта
     */

    @Step("Получить данные поставщика с serviceId=\"{serviceId}\" из ответа и перевести в POJO")
    public MobileProviderData getMobileProviderResponseData(String serviceId) {
        return given()
                .filter(new OverrideContentTypeFilter())
                .formParam("serviceId", serviceId)
                .spec(mobileProviderSpecification.getRequestSpecification())
                .when()
                .post("/providers.do")
                .then()
                .spec(mobileProviderSpecification.getResponseSpecification())
                .extract().as(MobileProviderData.class);
    }

    @Step("Проверить, что объект ответа соответствует объекту запроса")
    public void assertEqualsProviderData(MobileProviderData responseData, MobileProviderData requestData) {
        assertThat(responseData).isEqualTo(requestData);
    }

}
