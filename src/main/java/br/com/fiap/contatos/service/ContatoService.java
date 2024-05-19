package br.com.fiap.contatos.service;

import br.com.fiap.contatos.dto.contato.ReadContatoDto;
import br.com.fiap.contatos.model.ContatoModel;
import br.com.fiap.contatos.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository _contatoRepository;

    public ReadContatoDto cadastrar(ContatoModel contatoModel) {
        return new ReadContatoDto(_contatoRepository.save(contatoModel));
    }

    public ReadContatoDto buscarPorId(Long id) {
        Optional<ContatoModel> contatoOptional = _contatoRepository.findById(id);
        if (contatoOptional.isPresent()) {
            return new ReadContatoDto(contatoOptional.get());
        } else {
           throw new RuntimeException("Contato não encontrado!");
        }
    }

    public List<ReadContatoDto> listarTodosOsContatos() {
        return _contatoRepository
                .findAll()
                .stream()
                .map(ReadContatoDto::new)
                .toList();
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
        return _contatoRepository.findByDataNascimentoBetween(dataInicial, dataFinal);
    }

    public ContatoModel atualizar(ContatoModel contatoModel) {
        Optional<ContatoModel> contatoOptional = _contatoRepository.findById(contatoModel.getId());

        if (contatoOptional.isPresent()) {
            return _contatoRepository.save(contatoModel);
        } else {
            throw new RuntimeException("Contato não encontrado!");
        }
    }

    public ContatoModel buscarContatoPorNome(String nome) {
        Optional<ContatoModel> contatoOptional = _contatoRepository.findByNome(nome);

        if (contatoOptional.isPresent()) {
            return contatoOptional.get();
        } else {
            throw new RuntimeException("Contato não encontrado!");
        }
    }

}
