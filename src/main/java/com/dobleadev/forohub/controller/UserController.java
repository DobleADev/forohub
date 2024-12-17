package com.dobleadev.forohub.controller;

import com.dobleadev.forohub.domain.user.*;
import com.dobleadev.forohub.domain.user.User;
import com.dobleadev.forohub.domain.user.UserRepository;
import com.dobleadev.forohub.infrastructure.security.DataJWToken;
import com.dobleadev.forohub.infrastructure.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService service;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid DataAuthUser dataAuthUser) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(dataAuthUser.email(),
                dataAuthUser.password());
        var usuarioAutenticado = manager.authenticate(authenticationToken);
        var JWTtoken = service.generateToken((User) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DataJWToken(JWTtoken));
    }
//    @GetMapping("/{id}")
//    public DataDetailsUser getById(@PathVariable Long id) {
//        System.out.println(id);
//        var user = userRepository.getReferenceById(id);
//        return new DataDetailsUser(user);
//    }

    @PostMapping("/register")
    @Transactional
    public void register(@RequestBody @Valid DataRegisterUser user) {
        String encodedPassword = passwordEncoder.encode(user.password());
        var newUser = userRepository.save(
                new User(
                        new DataRegisterUser(
                                user.name(),
                                user.email(),
                                encodedPassword)
                )
        );
        System.out.println("User creado exitosamente");
//        return new DataResultUser(newUser);
    }

//    @PostMapping("/logout")
//    @Transactional
//    public void logout(@RequestBody @Valid DataAuthUser user) {
//        System.out.println("Sesion cerrada exitosamente");
//    }
//
//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity delete(@PathVariable Long id) {
//        var userToDelete = userRepository.getReferenceById(id);
//        userToDelete.deactivate();
//        System.out.println("User eliminado exitosamente");
//        return ResponseEntity.noContent().build();
//    }
}
