package br.com.fiap.contatos.dto.contato;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateContatoDto(
        Long id,

        @NotBlank(message = "O nome do contato é obrigatório!")
        String nome,

        @NotBlank(message = "O email é obrigatório!")
        @Email(message = "O email está com o formato inválido!")
        String email,

        @NotNull(message = "A data de nascimento é obrigatória.")
        LocalDate dataNascimento
) {
}
