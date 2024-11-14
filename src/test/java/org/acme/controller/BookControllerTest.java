package org.acme.controller;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class BookControllerTest {

    @Test
    public void testBookControllerFindByIdEndpoint() {
        given()
                .when().get("/api/books/find/1")
                .then()
                .statusCode(200)
                .body("name", is("Effective Java"))
                .body("title", is("A Guide to Best Practices in Java Programming"))
                .body("published", is("2018-01-01"))
                .body("price", is(45.99F));
    }

    @Test
    public void testBookControllerSaveEndpoint() {
        String newBook = """
                        {
                            "name": "Test book name",
                            "title": "Test book title",
                            "published": "2018-01-01",
                            "price": "45.99"
                        }
                """;

        given()
                .contentType("application/json")
                .body(newBook)
                .when().post("/api/books")
                .then()
                .statusCode(200)
                .body("name", is("Test book name"))
                .body("title", is("Test book title"))
                .body("published", is("2018-01-01"))
                .body("price", is(45.99F));
    }

    @Test
    public void testBookControllerDeleteEndpoint() {
        given()
                .when().get("/api/books/11")
                .then()
                .statusCode(200);

        given()
                .when().delete("/api/books/11")
                .then()
                .statusCode(204);

        given()
                .when().get("/api/books/11")
                .then()
                .statusCode(404);
    }
}
