package br.com.fiap.contatos.dto.contato;

import br.com.fiap.contatos.model.ContatoModel;

import java.time.LocalDate;

public record ReadContatoDto(
        Long id,
        String nome,
        String email,
        LocalDate dataNascimento
) {
    public ReadContatoDto(ContatoModel contatoModel) {
        this(
                contatoModel.getId(),
                contatoModel.getNome(),
                contatoModel.getEmail(),
                contatoModel.getDataNascimento()
        );
    }
}
