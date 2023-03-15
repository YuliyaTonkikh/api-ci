package ru.netology.rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;




class TestMobileBankAPI {
    /*
        TestMobileBankAPI тестирование REST API мобильного банка
    */

    private RequestSpecification specs = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setBasePath("/api/v1")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @Test
    @DisplayName("Should 200Ok on get /demo/accounts")
    void shouldReturnDemoAccounts() {
        given().spec(specs).
                when().get("/demo/accounts").
                then().statusCode(200);
    }

    @Test
    @DisplayName("Should return demo accounts with equal schema")
    void shouldReturnDemoAccountsWithEqSchema() {
        given().spec(specs).
                when().get("/demo/accounts").
                then().statusCode(200).
                body(matchesJsonSchemaInClasspath("accounts.schema.json"));
    }
}