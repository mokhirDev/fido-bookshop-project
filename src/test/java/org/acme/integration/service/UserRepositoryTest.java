package org.acme.integration.service;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.entity.User;
import org.acme.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class UserRepositoryTest {

    @Inject
    UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
        userRepository.persist(new User("Mokhirbek", "Makhkamov", "mokhirDev", "password123", "mokhirDev@example.com", "+123456789", null));
        userRepository.persist(new User("Davron", "Davronov", "davronDev", "password456", "davronDev@example.com", "+987654321", null));
    }

    @Test
    @Transactional
    public void testFindAll() {
        List<User> users = userRepository.listAll();
        assertEquals(2, users.size(), "Должны быть два пользователя в базе данных");
    }

    @Test
    @Transactional
    public void testFindById() {
        User user = userRepository.findByIdOptional(2L).orElse(null);
        assertNotNull(user, "Пользователь с именем 'johndoe' должен существовать");
        assertEquals("mokhiDev", user.getUsername());
        assertEquals("mokhirDev@example.com", user.getEmail());
        assertEquals("+123456789", user.getPassword());
        assertEquals("password123", user.getPassword());
    }

    @Test
    @Transactional
    public void testSaveAndDeleteUser() {
//        User newUser = new User(null, "Alice", "Smith", "alicesmith", "pass789", "alice@example.com", "+1122334455");
//        userRepository.persist(newUser);
//        assertNotNull(newUser.getId(), "ID пользователя должен быть назначен после сохранения");
//
//        User savedUser = userRepository.findById(newUser.getId());
//        assertNotNull(savedUser, "Сохранённый пользователь должен быть найден");
//        assertEquals("alicesmith", savedUser.getUsername());
//
//        // Удаляем пользователя и проверяем, что он удален
//        userRepository.delete(savedUser);
//        assertFalse(userRepository.findByIdOptional(savedUser.getId()).isPresent(), "Пользователь должен быть удален");
    }
}

