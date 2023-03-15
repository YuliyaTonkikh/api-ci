package ru.netology.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;


class TestMobileBankAPI {
    /*
        TestMobileBankAPI тестирование REST API мобильного банка
    */

    @Test
    @DisplayName("Should 200Ok on get /demo/accounts")
    void shouldReturnDemoAccounts() {
        given().baseUri("http://localhost:9999/api/v1").
                when().get("/demo/accounts").
                then().statusCode(200);
    }

    @Test
    @DisplayName("Should return demo accounts with equal schema")
    void shouldReturnDemoAccountsWithEqSchema() {
        given().baseUri("http://localhost:9999/api/v1").
                when().get("/demo/accounts").
                then().statusCode(200).
                body(matchesJsonSchemaInClasspath("accounts.schema.json"));
    }

    @Test
    @DisplayName("should currency RUB")
    void shouldCurrencyRub() {
        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .body("[0].currency", equalTo("RUB"));

    }

    @Test
    @DisplayName("Should currency USD")
    void shouldCurrencyUsd() {
        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .body("[1].currency", equalTo("USD"));

    }

    @Test
    @DisplayName("Should currency RUB and USD")
    void shouldCurrencyRubUsd() {
        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .contentType(ContentType.JSON)
                .body("", hasSize(3))
                .body("[2].currency", equalTo("RUB"))
                .body("[1].currency", equalTo("USD"))
                .body("[0].balance", greaterThanOrEqualTo(0));

    }

}