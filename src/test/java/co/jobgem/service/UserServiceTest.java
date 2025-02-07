package co.jobgem.service;

import co.jobgem.dto.UserDTO;
import co.jobgem.entity.UserEntity;
import co.jobgem.exception.ResourceNotFoundException;
import co.jobgem.mapper.UserMapper;
import co.jobgem.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private UserEntity user;
    private UserDTO userDTO;

    @BeforeEach
    public void setup() {
        user = new UserEntity();
        user.setId(1L);
        user.setUsername("john");
        user.setEmail("john@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");

        userDTO = new UserDTO(1L, "john", "john@example.com", "John", "Doe", null, null, null);
    }

    @Test
    @DisplayName("Test get all users")
    void testGetAllUsers() {
        List<UserEntity> users = Collections.singletonList(user);
        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.userToUserDTO(any(UserEntity.class))).thenReturn(userDTO);

        List<UserDTO> result = userService.getAllUsers();

        assertEquals(1, result.size());
        assertEquals(userDTO, result.getFirst());
    }

    @Test
    @DisplayName("Test get user by id")
    void testGetUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.userToUserDTO(user)).thenReturn(userDTO);

        UserDTO result = userService.getUserById(1L);

        assertEquals(userDTO, result);
    }

    @Test
    @DisplayName("Test get user by id not found")
    void testGetUserByIdNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(1L));
    }

    @Test
    @DisplayName("Test create user")
    void testCreateUser() {
        when(userMapper.userDTOToUser(userDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.userToUserDTO(user)).thenReturn(userDTO);

        UserDTO result = userService.createUser(userDTO);

        assertEquals(userDTO, result);
    }

    @Test
    @DisplayName("Test update user")
    void testUpdateUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.userToUserDTO(user)).thenReturn(userDTO);

        UserDTO result = userService.updateUser(1L, userDTO);

        assertEquals(userDTO, result);
    }

    @Test
    @DisplayName("Test update user not found")
    void testUpdateUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(1L, userDTO));
    }

    @Test
    @DisplayName("Test delete user")
    void testDeleteUser() {
        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(any());
    }

}