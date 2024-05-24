package br.com.fiap.contatos.controller;

import br.com.fiap.contatos.config.security.TokenService;
import br.com.fiap.contatos.dto.login.CreateLoginDto;
import br.com.fiap.contatos.dto.token.ReadTokenDto;
import br.com.fiap.contatos.dto.usuario.CreateUsuarioDto;
import br.com.fiap.contatos.dto.usuario.ReadUsuarioDto;
import br.com.fiap.contatos.model.UsuarioModel;
import br.com.fiap.contatos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager _authManager;

    @Autowired
    private UsuarioService _usuarioService;

    @Autowired
    private TokenService _tokenService;

    @PostMapping("/login")
    public ResponseEntity login(
            @RequestBody
            @Valid
            CreateLoginDto loginDto
    ) {
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        loginDto.email(),
                        loginDto.senha()
                );
        Authentication auth = _authManager.authenticate(usernamePassword);

        String token = _tokenService.gerarToken((UsuarioModel) auth.getPrincipal());

        return ResponseEntity.ok(new ReadTokenDto(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ReadUsuarioDto registrar(
            @RequestBody
            @Valid
            CreateUsuarioDto createUsuarioDto
            ) {
        ReadUsuarioDto usuarioSalvo = null;
        usuarioSalvo = _usuarioService.cadastrar(createUsuarioDto);
        return usuarioSalvo;
    }
}
