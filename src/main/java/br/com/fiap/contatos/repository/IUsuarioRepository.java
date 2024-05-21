package br.com.fiap.contatos.repository;

import br.com.fiap.contatos.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    UserDetails findByEmail(String email);
}
