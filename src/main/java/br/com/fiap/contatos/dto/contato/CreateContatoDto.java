package br.com.fiap.contatos.dto.contato;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CreateContatoDto(
        Long id,
        String nome,
        String email,
        String senha,
        LocalDate dataNascimento
) {
}
