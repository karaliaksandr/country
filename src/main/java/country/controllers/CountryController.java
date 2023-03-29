package country.controllers;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;


public class CountryController {

    private final RequestSpecification requestSpecification;

    public CountryController() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://restcountries.com/v2/alpha")
                .setContentType(ContentType.JSON)
                .log(LogDetail.URI)
                .build();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(30000L))
                .build();
        RestAssured.defaultParser = Parser.JSON;
    }

    public Response getCountryInfo(String countryCode){
        return RestAssured.given(requestSpecification)
                .param("codes", countryCode)
                .get();
    }
}
