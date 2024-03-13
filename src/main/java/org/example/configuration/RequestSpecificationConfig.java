package org.example.configuration;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;

public class RequestSpecificationConfig {

  private static final String BASE_URI = "https://d2iwf0lpgyl88i.cloudfront.net";
  private static final String BASE_PATH = "/user/register";

  public static RequestSpecification getRequestSpecification() {
    Assertions.setPrintAssertionsDescription(true);
    return RestAssured.given().
        baseUri(BASE_URI).
        basePath(BASE_PATH).
        contentType(ContentType.JSON).
        filters(new AllureRestAssured());
  }
}
