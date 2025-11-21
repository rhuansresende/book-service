package br.com.desenvolvimento.logica.book_service.service;

import br.com.desenvolvimento.logica.book_service.dto.security.AccountCredentialsDTO;
import br.com.desenvolvimento.logica.book_service.dto.security.TokenDTO;
import br.com.desenvolvimento.logica.book_service.model.User;
import br.com.desenvolvimento.logica.book_service.repository.UserRepository;
import br.com.desenvolvimento.logica.book_service.security.jwt.JwtTokenProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    Logger logger = LogManager.getLogger(AuthService.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    public AuthService(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    public TokenDTO signIn(AccountCredentialsDTO credentials) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword()
                )
        );

        var user = userRepository.findByUsername(credentials.getUsername());
        if (user == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        return jwtTokenProvider.createAccessToken(
                credentials.getUsername(),
                user.getRoles()
        );
    }

    public TokenDTO refreshToken(String username, String refreshToken) {
        var user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("Usuário não encontrado");
        }
        return jwtTokenProvider.refreshToken(refreshToken);
    }

    public AccountCredentialsDTO createUser(AccountCredentialsDTO user) {

        if (user == null) {
            throw new RuntimeException("Preencha os dados!");
        }

        logger.info("Creating one new user!");

        var pass = generateHashedPassword(user.getPassword());

        var entity = new User();
        entity.setFullName(user.getFullname().trim().toUpperCase());
        entity.setUsername(user.getUsername());
        entity.setPassword(pass);
        entity.setAccountNonLocked(true);
        entity.setAccountNonExpired(true);
        entity.setCredentialsNonExpired(true);
        entity.setEnabled(true);
        userRepository.save(entity);

        AccountCredentialsDTO newUser = new AccountCredentialsDTO();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(pass);
        newUser.setFullname(user.getFullname().trim().toUpperCase());
        return newUser;

    }

    private String generateHashedPassword(final String password) {
        PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder(
                "",
                8,
                185000,
                Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("pbkdf2", pbkdf2Encoder);
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(
                "pbkdf2",
                encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
        return passwordEncoder.encode(password);
    }


}
