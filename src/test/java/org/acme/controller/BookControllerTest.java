package org.acme.controller;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.acme.entity.Book;
import org.acme.repository.BookRepository;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestTransaction
public class BookControllerTest {
    @Inject
    BookRepository bookRepository;

    @Test
    public void testBookControllerFindByIdEndpoint() {
        Book last = bookRepository.findAll().stream().toList().getLast();
        given()
                .when().get("/api/books/find/" + last.getId())
                .then()
                .statusCode(200)
                .body("name", is(last.getName()))
                .body("title", is(last.getTitle()))
                .body("published", is(String.valueOf(last.getPublished())))
                .body("price", is(Float.valueOf(String.valueOf(last.getPrice()))));
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
        Book last = bookRepository.findAll().stream().toList().getLast();
        Long id = last.getId();
        given()
                .when().get("/api/books/" + id)
                .then()
                .statusCode(200);

        given()
                .when().delete("/api/books/" + id)
                .then()
                .statusCode(204);

        given()
                .when().get("/api/books/" + id)
                .then()
                .statusCode(404);
    }
}
