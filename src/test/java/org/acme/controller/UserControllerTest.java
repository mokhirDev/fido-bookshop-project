package org.acme.controller;


import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.entity.User;
import org.acme.repository.UserRepository;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class UserControllerTest {

    @Inject
    UserRepository userRepository;

    @Test
    @Transactional
    public void testUserControllerFindByIdEndpoint() {
        given()
                .when().get("/api/users/get/1")
                .then()
                .statusCode(200)
                .body("name", is("Mokhirbek"))
                .body("fullName", is("Makhkamov"))
                .body("username", is("mokhirDev"))
                .body("phone", is("+998903571847"))
                .body("password", is("123123"))
                .body("email", is("mokhirbek.makhkam@gmail.com"));
    }

    @Test
    @Transactional
    public void testUserControllerSaveEndpoint() {
        String newBook = """
                {
                    "name": "user name",
                    "fullName": "user full name",
                    "username": "user username",
                    "password": "user password",
                    "email": "user email",
                    "phone": "user phone"
                }
                """;

        given()
                .contentType("application/json")
                .body(newBook)
                .when().post("/api/users")
                .then()
                .statusCode(200)
                .body("name", is("user name"))
                .body("fullName", is("user full name"))
                .body("username", is("user username"))
                .body("password", is("user password"))
                .body("email", is("user email"))
                .body("phone", is("user phone"));
    }

    @Test
    @Transactional
    public void testUserControllerDeleteEndpoint() {
        User last = userRepository.findAll().stream().toList().getLast();
        Long id = last.getId();
        given()
                .when().get("/api/users/"+id)
                .then()
                .statusCode(200);

        given()
                .when().delete("/api/users/"+id)
                .then()
                .statusCode(204);

        given()
                .when().get("/api/users/"+id)
                .then()
                .statusCode(404);
    }
}
