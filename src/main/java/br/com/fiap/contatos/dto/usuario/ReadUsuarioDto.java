package br.com.fiap.contatos.dto.usuario;

import br.com.fiap.contatos.enums.UsuarioRoleEnum;
import br.com.fiap.contatos.model.UsuarioModel;

public record ReadUsuarioDto(
        Long id,
        String nome,
        String email,
        UsuarioRoleEnum role
) {
    public ReadUsuarioDto(UsuarioModel usuarioModel) {
        this(
                usuarioModel.getId(),
                usuarioModel.getNome(),
                usuarioModel.getEmail(),
                usuarioModel.getRole()

        );
    }
}
