package org.acme.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.acme.entity.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestTransaction
public class UserRepositoryTest {

    @Inject
    UserRepository userRepository;

    @Test
    public void testUserRepositoryFindById() {
        User last = userRepository.findAll().stream().toList().getLast();
        User actual = userRepository.findById(last.getId());
        assertUserEntityEquals(last, actual);
    }


    @Test
    public void testUserRepositorySave() {
        User expected = User
                .builder()
                .name("Test user name")
                .fullName("Test user full name")
                .phone("Test user phone")
                .email("Test user email")
                .username("Test username")
                .password("Test user password")
                .build();
        userRepository.persist(expected);
        assertNotNull(expected.getId(), "ID должна быть назначена после сохранения");
        User savedBook = userRepository.findById(expected.getId());
        assertNotNull(savedBook, "Сохранённая книга должна быть найдена");
        assertUserEntityEquals(expected, savedBook);

    }

    @Test
    public void testUserRepositoryDeleteById() {
        User user = userRepository.findAll().stream().findFirst().orElse(null);
        assertNotNull(user, "Результат не должен быть null");
        Long id = user.getId();
        userRepository.deleteById(user.getId());
        User response = userRepository.findByIdOptional(id).orElse(null);
        assertNull(response, "Результат должен быть равен 'null'");
    }

    private void assertUserEntityEquals(User expected, User actual) {
        assertNotNull(actual, "Результат не должен быть null");
        assertEquals(expected.getName(), actual.getName(), "Имена не совподают");
        assertEquals(expected.getFullName(), actual.getFullName(), "Фамилии не совподают");
        assertEquals(expected.getPassword(), actual.getPassword(), "Пароли не совподают");
        assertEquals(expected.getUsername(), actual.getUsername(), "Юзернеймы не совподают");
        assertEquals(expected.getPhone(), actual.getPhone(), "Номера телефонов не совподают");
        assertEquals(expected.getEmail(), actual.getEmail(), "Названии почты не совподают");
    }
}