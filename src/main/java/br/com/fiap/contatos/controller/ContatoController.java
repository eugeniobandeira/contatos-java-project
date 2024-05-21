package br.com.fiap.contatos.controller;

import br.com.fiap.contatos.dto.contato.CreateContatoDto;
import br.com.fiap.contatos.dto.contato.ReadContatoDto;
import br.com.fiap.contatos.exception.UserNotFoundException;
import br.com.fiap.contatos.model.ContatoModel;
import br.com.fiap.contatos.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ContatoController {

    @Autowired
    private ContatoService _service;

    @PostMapping("/contatos")
    @ResponseStatus(HttpStatus.CREATED)
    public ReadContatoDto gravar(@RequestBody @Valid CreateContatoDto createContatoDto) {
        return _service.cadastrar(createContatoDto);
    }

    @GetMapping("/contatos")
    @ResponseStatus(HttpStatus.OK)
    public Page<ReadContatoDto> listarContatos(Pageable paginacao) {
        return _service.listarTodosOsContatos(paginacao);
    }

    @GetMapping("/contatos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ReadContatoDto> listarContatoPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(_service.buscarPorId(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

//    @GetMapping("/contatos/{nome}")
//    @ResponseStatus(HttpStatus.OK)
//    public ContatoModel listarContatoPorNome(@PathVariable String nome) {
//        return _service.buscarContatoPorNome(nome);
//    }

    @GetMapping("/contatos/{dataInicial}/{dataFinal}")
    @ResponseStatus(HttpStatus.OK)
    public List<ContatoModel> listarAniversariantesPorIntervaloDeData(
            @PathVariable LocalDate dataInicial,
            @PathVariable LocalDate dataFinal
    ) {
        return _service.listarAniversariantes(dataInicial, dataFinal);
    }

    @DeleteMapping("/contatos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        _service.excluir(id);
    }

    @PutMapping("/contatos")
    @ResponseStatus(HttpStatus.OK)
    public ContatoModel atualizar(@RequestBody ContatoModel contatoModel) {
        return _service.atualizar(contatoModel);
    }
}
