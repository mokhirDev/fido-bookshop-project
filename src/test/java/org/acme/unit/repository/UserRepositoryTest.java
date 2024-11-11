package org.acme.unit.repository;

import org.acme.entity.User;
import org.acme.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRepositoryTest {
    UserRepository userRepository = Mockito.mock(UserRepository.class);
//    UserMapper userMapper = Mockito.mock(UserMapper.class);

    @Test
    public void findUserByIdTest() {
        User expectedUser = User
                .builder()
                .name("Mokhirbek")
                .fullName("Makhkamov")
                .username("mokhirDev")
                .phone("+998903571847")
                .build();
        Mockito.when(userRepository.findById(1L)).thenReturn(expectedUser);
        User result = userRepository.findById(1L);
        assertEquals(expectedUser.id, result.id, "ID пользователя не совпадает");
        assertEquals(expectedUser.name, result.name, "Имя пользователя не совпадает");
        assertEquals(expectedUser.fullName, result.fullName, "Полное имя пользователя не совпадает");
        assertEquals(expectedUser.username, result.username, "Имя пользователя (username) не совпадает");
        assertEquals(expectedUser.phone, result.phone, "Телефон пользователя не совпадает");
//
//        BookResponseDTO bookResponseDTO = userResponseDTO.getBooks()
//                .stream()
//                .filter(b -> b.getId().equals(1L)).findFirst().get();
//        assertNotNull(bookResponseDTO);
//        assertEquals(1L, bookResponseDTO.getId());
//        assertEquals("Effective Java", bookResponseDTO.getName());
//        assertEquals("A Guide to Best Practices in Java Programming", bookResponseDTO.getTitle());

    }
}
