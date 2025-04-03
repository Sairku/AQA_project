package com.aqa.AQA_project.tests;

import com.aqa.AQA_project.util.BaseTest;
import com.github.javafaker.Faker;
import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("User Management API")
@Feature("User CRUD Operations")
public class UserApiTests extends BaseTest {

    private static final String BASE_URL = "http://localhost:8080";
    private static final Faker faker = new Faker();

//    @BeforeClass
//    public void setup() {
//        RestAssured.baseURI = "http://localhost:8080";
//        RestAssured.filters(new AllureRestAssured());
//    }

    @Test
    @Story("Create a new user")
    @Description("Verify that a new user can be created via API")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateUser() {
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        int age = faker.number().numberBetween(18, 60);

        Allure.step("Step 1: Send GET request to /users/1", () -> {
            given().get("/users/1").then().statusCode(200);
        });

        String requestBody = String.format("{\"name\": \"%s\", \"email\": \"%s\", \"age\": %d}", name, email, age);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/users");

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("name")).isEqualTo(name);
    }

    @Test
    @Story("Get user by ID")
    @Description("Test retrieving a user by ID")
    public void testGetUserById() {
        Response response = given()
                .pathParam("id", 1)
                .get("/users/{id}");

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getLong("id")).isEqualTo(1);
    }
    @Test
    public void testWithAttachment() {
        String response = given().get("/users/1").asString();
        Allure.addAttachment("API Response", "text/plain", response);
    }
    @Test
    @Description("Тест для перевірки Allure")
    public void testAllure() {
        assertThat(1 + 1).isEqualTo(2);
    }
}
