package org.acme.book.aggregate.dto;

import jakarta.persistence.ManyToMany;
import org.acme.user.aggregate.entity.User;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class BookResponseDTO {
    public Long id;
    public String name;
    public String title;
    public Set<User> authors;
    public Date published;
    public BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
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
        BookResponseDTO that = (BookResponseDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(title, that.title) && Objects.equals(authors, that.authors) && Objects.equals(published, that.published) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, title, authors, published, price);
    }

    @Override
    public String toString() {
        return "BookResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", published=" + published +
                ", price=" + price +
                '}';
    }
}
