package br.com.desenvolvimento.logica.book_service.service;

import br.com.desenvolvimento.logica.book_service.dto.PersonDTO;
import br.com.desenvolvimento.logica.book_service.dto.security.AccountCredentialsDTO;
import br.com.desenvolvimento.logica.book_service.dto.security.TokenDTO;
import br.com.desenvolvimento.logica.book_service.model.Permission;
import br.com.desenvolvimento.logica.book_service.model.Person;
import br.com.desenvolvimento.logica.book_service.model.User;
import br.com.desenvolvimento.logica.book_service.repository.PermissionRepository;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private PersonService personService;

    @Autowired
    private PermissionService permissionService;

    public TokenDTO signIn(AccountCredentialsDTO credentials) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getDocument(),
                        credentials.getPassword()
                )
        );

        var user = userRepository.findByUsername(credentials.getDocument());
        if (user == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        return jwtTokenProvider.createAccessToken(
                credentials.getDocument(),
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

    public PersonDTO createUser(AccountCredentialsDTO user) {
        logger.info("Creating one new user!");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        var person = personService.findByDocument(user.getDocument());

        if (person == null) {
            person = new Person();
            person.setFullname(user.getFullname().trim().toUpperCase());
            person.setBirthDate(LocalDate.parse(user.getBirthDate(), formatter));
            person.setDocument(user.getDocument());
            person.setEmail(user.getEmail());
            person.setPhone(user.getPhone());
            personService.save(person);
        }

        var pass = generateHashedPassword(user.getPassword());

        var entity = new User();
        entity.setUsername(user.getDocument());
        entity.setPassword(pass);
        entity.setAccountNonLocked(true);
        entity.setAccountNonExpired(true);
        entity.setCredentialsNonExpired(true);
        entity.setEnabled(true);
        entity.setPerson(person);

        List<Permission> permissions = new ArrayList<>();

        user.getRoles().forEach(role -> {
            var permission = permissionService.findByDescription(role);
            permissions.add(permission);
        });

        entity.setPermissions(permissions);
        userRepository.save(entity);

        PersonDTO personDTO = new PersonDTO();
        personDTO.setFullname(person.getFullname());
        personDTO.setDocument(user.getDocument());
        personDTO.setEmail(person.getEmail());
        personDTO.setActive(entity.getEnabled());
        personDTO.setRoles(entity.getRoles());

        return personDTO;
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
