package api.specifications;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.lessThan;

public class CitiesSpecification {

    public RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri("https://www.open.ru")
                .setBasePath("/api/v1")
                .addFilter(new AllureRestAssured())
                .setContentType(ContentType.JSON)
                .build();
    }

    public ResponseSpecification getResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .expectResponseTime(lessThan((10000L)))
                .build();
    }
}
