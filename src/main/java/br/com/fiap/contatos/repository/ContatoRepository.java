package br.com.fiap.contatos.repository;

import br.com.fiap.contatos.model.ContatoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ContatoRepository extends JpaRepository<ContatoModel, Long> {
    public ContatoModel findByNome(String nome);

    public List<ContatoModel> findByDataNscimentoBetween(LocalDate dataInicial, LocalDate dataFinal);
}
