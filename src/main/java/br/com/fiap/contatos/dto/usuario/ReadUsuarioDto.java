package br.com.fiap.contatos.dto.usuario;

public record ReadUsuarioDto(
        Long id,
        String nome,
        String email
) {
}
