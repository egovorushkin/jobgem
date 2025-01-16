package com.jobgem.service;

import com.jobgem.entity.Role;
import com.jobgem.entity.RoleName;
import com.jobgem.entity.UserEntity;
import com.jobgem.exception.AppException;
import com.jobgem.payload.LoginRequest;
import com.jobgem.payload.SignUpRequest;
import com.jobgem.repository.RoleRepository;
import com.jobgem.repository.UserRepository;
import com.jobgem.security.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider tokenProvider;

    @InjectMocks
    private AuthService authService;

    private LoginRequest loginRequest;
    private SignUpRequest signUpRequest;
    private UserEntity user;
    private Role role;

    @BeforeEach
    public void setup() {
        loginRequest = new LoginRequest();
        loginRequest.setUsernameOrEmail("testuser");
        loginRequest.setPassword("password");

        signUpRequest = new SignUpRequest();
        signUpRequest.setFirstName("John");
        signUpRequest.setLastName("Doe");
        signUpRequest.setUsername("johndoe");
        signUpRequest.setEmail("john.doe@example.com");
        signUpRequest.setPassword("password");

        user = new UserEntity();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUsername("johndoe");
        user.setEmail("john.doe@example.com");

        role = new Role();
        role.setId(1L);
        role.setName(RoleName.ROLE_USER);
    }

    @Test
    @DisplayName("Test authenticate user")
    void testAuthenticateUser() {
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(tokenProvider.generateToken(authentication)).thenReturn("testJwtToken");

        String token = authService.authenticateUser(loginRequest);

        assertEquals("testJwtToken", token);
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(tokenProvider).generateToken(authentication);
    }

    @Test
    @DisplayName("Test register user")
    void testRegisterUser() {
        when(userRepository.existsByUsername(signUpRequest.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(signUpRequest.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(signUpRequest.getPassword())).thenReturn("encodedPassword");
        when(roleRepository.findByName(RoleName.ROLE_USER)).thenReturn(Optional.of(role));
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        UserEntity registeredUser = authService.registerUser(signUpRequest);

        assertNotNull(registeredUser);
        assertEquals(user.getUsername(), registeredUser.getUsername());
        assertEquals(user.getEmail(), registeredUser.getEmail());
        verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    @DisplayName("Test register user username already taken")
    void testRegisterUserUsernameAlreadyTaken() {
        when(userRepository.existsByUsername(signUpRequest.getUsername())).thenReturn(true);

        assertThrows(AppException.class, () -> authService.registerUser(signUpRequest));
    }

    @Test
    @DisplayName("Test register user email already in use")
    void testRegisterUserEmailAlreadyInUse() {
        when(userRepository.existsByUsername(signUpRequest.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(signUpRequest.getEmail())).thenReturn(true);

        assertThrows(AppException.class, () -> authService.registerUser(signUpRequest));
    }
}