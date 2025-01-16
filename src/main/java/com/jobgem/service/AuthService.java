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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthService {

    private  final AuthenticationManager authenticationManager;
    private  final UserRepository userRepository;
    private  final RoleRepository roleRepository;
    private  final PasswordEncoder passwordEncoder;
    private  final JwtTokenProvider tokenProvider;

    public AuthService(AuthenticationManager authenticationManager,
                       UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder,
                       JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    public String authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.generateToken(authentication);
    }

    public UserEntity registerUser(SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new AppException("Username is already taken!");
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new AppException("Email Address already in use!");
        }

        // Creating user's account
        UserEntity user = new UserEntity(signUpRequest.getFirstName(), signUpRequest.getLastName(),
                signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        return userRepository.save(user);
    }

    public void logoutUser() {
        SecurityContextHolder.clearContext();
    }
}