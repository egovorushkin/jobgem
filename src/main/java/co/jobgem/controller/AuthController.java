package co.jobgem.controller;

import co.jobgem.entity.UserEntity;
import co.jobgem.payload.ApiResponse;
import co.jobgem.payload.JwtAuthenticationResponse;
import co.jobgem.payload.LoginRequest;
import co.jobgem.payload.SignUpRequest;
import co.jobgem.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        String jwt = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        UserEntity result = authService.registerUser(signUpRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true,
                "User registered successfully. Welcome, " + result.getFirstName() + " " + result.getLastName() + "!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse> logoutUser() {
        authService.logoutUser();
        return ResponseEntity.ok(new ApiResponse(true, "User logged out successfully"));
    }
}
