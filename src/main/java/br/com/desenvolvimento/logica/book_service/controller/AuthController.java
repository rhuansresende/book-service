package br.com.desenvolvimento.logica.book_service.controller;

import br.com.desenvolvimento.logica.book_service.dto.security.AccountCredentialsDTO;
import br.com.desenvolvimento.logica.book_service.dto.security.TokenDTO;
import br.com.desenvolvimento.logica.book_service.service.AuthService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody AccountCredentialsDTO credentials) {
        if (credentials == null
                || StringUtils.isBlank(credentials.getDocument())
                || StringUtils.isBlank(credentials.getPassword())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }
        return ResponseEntity.ok(authService.signIn(credentials));
    }

    @PutMapping("/refresh/{username}")
    public ResponseEntity<?> refreshToken(@PathVariable(name = "username") String username,
                                        @RequestHeader("Authorization") String authorization) {

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(authorization)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }

        return  ResponseEntity.ok(authService.refreshToken(username, authorization));
    }

    @PostMapping(value = "/createUser",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createUser(@RequestBody AccountCredentialsDTO credentials) {

        if (StringUtils.isEmpty(credentials.getFullname())
                || StringUtils.isEmpty(credentials.getEmail())
                || StringUtils.isEmpty(credentials.getDocument())
                || StringUtils.isEmpty(credentials.getPassword())
                || credentials.getRoles() == null
                || credentials.getRoles().isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }

        return ResponseEntity.ok(authService.createUser(credentials));
    }
}
