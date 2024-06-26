package br.com.fiap.contatos.service;

import br.com.fiap.contatos.dto.contato.CreateContatoDto;
import br.com.fiap.contatos.dto.contato.ReadContatoDto;
import br.com.fiap.contatos.exception.UserNotFoundException;
import br.com.fiap.contatos.model.ContatoModel;
import br.com.fiap.contatos.repository.IContatoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private IContatoRepository _contatoRepository;

    public ReadContatoDto cadastrar(CreateContatoDto createContatoDto) {
        ContatoModel contato = new ContatoModel();
        BeanUtils.copyProperties(createContatoDto, contato);
        return new ReadContatoDto(_contatoRepository.save(contato));
    }

    public ReadContatoDto buscarPorId(Long id) {
        Optional<ContatoModel> contatoOptional = _contatoRepository.findById(id);
        if (contatoOptional.isPresent()) {
            return new ReadContatoDto(contatoOptional.get());
        } else {
           throw new UserNotFoundException("Contato não encontrado!");
        }
    }

    public Page<ReadContatoDto> listarTodosOsContatos(Pageable paginacao) {
        return _contatoRepository
                .findAll(paginacao)
                .map(ReadContatoDto::new);
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
