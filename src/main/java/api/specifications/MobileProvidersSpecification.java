package api.specifications;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.lessThan;

public class MobileProvidersSpecification {

    public RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri("https://secure.openbank.ru")
                .setBasePath("/openapi/deploy/3.6/open_site")
                .addFilter(new AllureRestAssured())
                .addFormParam("categoryId", "1090")
                .build();
    }

    public ResponseSpecification getResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectResponseTime(lessThan((10000L)))
                .build();
    }
}
