package br.com.fiap.contatos.service;

import br.com.fiap.contatos.model.ContatoModel;
import br.com.fiap.contatos.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    //Indica que este atributo é injetado de forma automática, sem a necessidade de instanciar
    @Autowired
    private ContatoRepository _contatoRepository;

    public ContatoModel cadastrar(ContatoModel contatoModel) {
        return _contatoRepository.save(contatoModel);
    }

    public ContatoModel buscarPorId(Long id) {
        Optional<ContatoModel> contatoOptional = _contatoRepository.findById(id);
        if (contatoOptional.isPresent()) {
            return contatoOptional.get();
        } else {
           throw new RuntimeException("Contato não encontrado!");
        }
    }

    public List<ContatoModel> listarTodosOsContatos() {
        return _contatoRepository.findAll();
    }

    public void excluir(Long id) {
        Optional<ContatoModel> contatoOptional = _contatoRepository.findById(id);
        if (contatoOptional.isPresent()) {
            _contatoRepository.delete(contatoOptional.get());
        } else {
            throw new RuntimeException("Contato não encontrado!");
        }
    }

    public List<ContatoModel> listarAniversariantes(LocalDate dataInicial, LocalDate dataFinal) {
        return _contatoRepository.findByDataNscimentoBetween(dataInicial, dataFinal);
    }

    public ContatoModel atualizar(ContatoModel contatoModel) {
        Optional<ContatoModel> contatoOptional = _contatoRepository.findById(contatoModel.getId());
        if (contatoOptional.isPresent()) {
            return _contatoRepository.save(contatoModel);
        } else {
            throw new RuntimeException("Contato não encontrado!");
        }
    }

}
