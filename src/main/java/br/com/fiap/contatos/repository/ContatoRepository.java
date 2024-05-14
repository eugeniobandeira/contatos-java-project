package br.com.fiap.contatos.repository;

import br.com.fiap.contatos.model.ContatoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ContatoRepository extends JpaRepository<ContatoModel, Long> {
    public Optional<ContatoModel> findByNome(String nome);

    public List<ContatoModel> findByDataNscimentoBetween(LocalDate dataInicial, LocalDate dataFinal);
}
