package ru.netology.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


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
}