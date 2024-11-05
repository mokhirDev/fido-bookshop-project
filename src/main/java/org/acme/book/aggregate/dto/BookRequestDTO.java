package org.acme.book.aggregate.dto;

import jakarta.validation.constraints.NotNull;
import org.acme.user.aggregate.entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class BookRequestDTO {
    @NotNull(message = "Kitob nomi bo'sh bo'lishi kerak emas!")
    public String name;
    @NotNull(message = "Kitob sarlavhasi bo'sh bo'lishi kerak emas!")
    public String title;
    public Set<User> authors;
    @NotNull(message = "Kitob nashr etilgan sanasi bo'sh bo'lishi kerak emas!")
    public LocalDate published;
    @NotNull(message = "Kitob narhi bo'sh bo'lishi kerak emas!")
    public BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<User> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<User> authors) {
        this.authors = authors;
    }

    public LocalDate getPublished() {
        return published;
    }

    public void setPublished(LocalDate published) {
        this.published = published;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookRequestDTO that = (BookRequestDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(title, that.title) && Objects.equals(authors, that.authors) && Objects.equals(published, that.published) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, title, authors, published, price);
    }

    @Override
    public String toString() {
        return "BookRequestDTO{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", published=" + published +
                ", price=" + price +
                '}';
    }
}
