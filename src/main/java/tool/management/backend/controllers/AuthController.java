package tool.management.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tool.management.backend.dto.AuthRequest;
import tool.management.backend.dto.AuthResponse;
import tool.management.backend.dto.RegisterRequest;
import tool.management.backend.dto.TokenValidationRequest;
import tool.management.backend.dto.TokenValidationResponse;
import tool.management.backend.models.User;
import tool.management.backend.repositories.UserRepository;
import tool.management.backend.security.JwtUtil;
import tool.management.backend.services.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest request) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody RegisterRequest request) {
        userService.save(request);
    }

    @PostMapping("/token/validation")
    public TokenValidationResponse postMethodName(@RequestBody TokenValidationRequest request) {

        String token = request.getToken();

        try {
            String username = jwtUtil.extractUsername(token);
            boolean isValido = jwtUtil.validateToken(token, username);
            if (isValido) {
                return new TokenValidationResponse(true);
            } else {
                return new TokenValidationResponse(false);
            }
        } catch (Exception e) {
            return new TokenValidationResponse(false);
        }
    }

}
