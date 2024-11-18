package org.acme.controller;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.entity.User;
import org.acme.repository.UserRepository;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestTransaction
public class UserControllerTest {

    @Inject
    UserRepository userRepository;

    @Test
    public void testUserControllerFindByIdEndpoint() {
        User last = userRepository.findAll().stream().toList().getLast();
        given()
                .when().get("/api/users/get/" + last.getId())
                .then()
                .statusCode(200)
                .body("name", is(last.getName()))
                .body("fullname", is(last.getFullName()))
                .body("username", is(last.getUsername()))
                .body("phone", is(last.getPhone()))
                .body("password", is(last.getPassword()))
                .body("email", is(last.getEmail()));
    }

    @Test
    public void testUserControllerSaveEndpoint() {
        String newUser = """
                {
                    "name": "user name",
                    "fullname": "user full name",
                    "username": "user username",
                    "password": "user password",
                    "email": "user email",
                    "phone": "user phone"
                }
                """;

        given()
                .contentType("application/json")
                .body(newUser)
                .when().post("/api/users")
                .then()
                .statusCode(200)
                .body("name", is("user name"))
                .body("fullname", is("user full name"))
                .body("username", is("user username"))
                .body("email", is("user email"))
                .body("phone", is("user phone"));
    }

    @Test
    public void testUserControllerDeleteEndpoint() {
        User last = userRepository.findAll().stream().toList().getLast();
        Long id = last.getId();
        given()
                .when().get("/api/users/" + id)
                .then()
                .statusCode(200);

        given()
                .when().delete("/api/users/" + id)
                .then()
                .statusCode(204);

        given()
                .when().get("/api/users/" + id)
                .then()
                .statusCode(404);
    }
}
