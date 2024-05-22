package br.com.fiap.contatos.controller;

import br.com.fiap.contatos.dto.usuario.CreateUsuarioDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager _authManager;

    @PostMapping("/login")
    public ResponseEntity login(
            @RequestBody
            @Valid
            CreateUsuarioDto userDto
    ) {
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        userDto.email(),
                        userDto.senha()
                );
        Authentication auth = _authManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }
}
