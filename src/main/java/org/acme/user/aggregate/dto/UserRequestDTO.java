package org.acme.user.aggregate.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class UserRequestDTO {
    @NotNull(message = "name bo'lishi kerak emas")
    public String name;
    @NotNull(message = "fullName bo'lishi kerak emas")
    public String fullName;
    @NotNull(message = "name bo'lishi kerak emas")
    public String username;
    @NotNull(message = "username bo'lishi kerak emas")
    public String password;
    @NotNull(message = "password bo'lishi kerak emas")
    public String email;
    @NotNull(message = "email bo'lishi kerak emas")
    public String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRequestDTO that = (UserRequestDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(fullName, that.fullName) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, fullName, username, password, email, phone);
    }

    @Override
    public String toString() {
        return "UserRequestDTO{" +
                "name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
