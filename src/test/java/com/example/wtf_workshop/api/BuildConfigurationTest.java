package com.example.wtf_workshop.api;


import com.example.wtf_workshop.api.models.User;
import com.example.wtf_workshop.api.spec.Specifications;
import io.restassured.RestAssured;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.annotations.Test;

public class BuildConfigurationTest extends BaseApiTest {
    @Test
    public void buildonfigurationTest() {
        var user = User.builder()
                .username("admin")
                .password("admin")
                .build();

        var token = RestAssured
                .given()
                .spec(Specifications.getSpec().authSpec(user))
                .get("/authenticationTest.html?csrf")
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().asString();

        System.out.println(token);
    }
}
