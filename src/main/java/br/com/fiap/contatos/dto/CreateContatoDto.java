package br.com.fiap.contatos.dto;

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

        @NotBlank(message = "A senha é obrigatória!")
        @Size(min = 6, max = 10, message = "A senha deve ter entre 6 e 10 caracteres.")
        String senha,

        @NotNull(message = "A data de nascimento é obrigatória.")
        LocalDate dataNascimento
) {
}
