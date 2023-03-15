package ru.netology.rest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


class TestMobileBankAPI {
    /*
        TestMobileBankAPI тестирование REST API мобильного банка
    */

    @Test
    void shouldReturnDemoAccounts() {
        given().baseUri("http://localhost:9999/api/v1").
                when().get("/demo/accounts").
                then().statusCode(200);
    }


}