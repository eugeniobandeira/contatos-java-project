package br.com.fiap.contatos.controller;

import br.com.fiap.contatos.model.ContatoModel;
import br.com.fiap.contatos.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ContatoModel gravar(@RequestBody ContatoModel contatoModel) {
        return _service.cadastrar(contatoModel);
    }

    @GetMapping("/contatos")
    @ResponseStatus(HttpStatus.OK)
    public List<ContatoModel> listarContatos() {
        return _service.listarTodosOsContatos();
    }

    @GetMapping("/contatos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContatoModel listarContatoPorId(@PathVariable Long id) {
        return _service.buscarPorId(id);
    }

    @GetMapping("/contatos/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public ContatoModel listarContatoPorNome(@PathVariable String nome) {
        return _service.buscarContatoPorNome(nome);
    }

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
