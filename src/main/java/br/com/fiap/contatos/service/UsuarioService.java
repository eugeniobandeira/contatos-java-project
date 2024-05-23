package br.com.fiap.contatos.service;

import br.com.fiap.contatos.dto.contato.ReadContatoDto;
import br.com.fiap.contatos.dto.usuario.CreateUsuarioDto;
import br.com.fiap.contatos.dto.usuario.ReadUsuarioDto;
import br.com.fiap.contatos.exception.UserNotFoundException;
import br.com.fiap.contatos.model.ContatoModel;
import br.com.fiap.contatos.model.UsuarioModel;
import br.com.fiap.contatos.repository.IUsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    IUsuarioRepository _usuarioRepository;

    public ReadUsuarioDto cadastrar(CreateUsuarioDto createUsuarioDto) {

        String senhaCriptografada = new BCryptPasswordEncoder()
                .encode(createUsuarioDto.senha());

        UsuarioModel usuario = new UsuarioModel();
        BeanUtils.copyProperties(createUsuarioDto, usuario);
        usuario.setSenha(senhaCriptografada);

        return new ReadUsuarioDto(_usuarioRepository.save(usuario));
    }

    public Page<ReadUsuarioDto> listarTodosOsUsuarios(Pageable paginacao) {
        return _usuarioRepository
                .findAll(paginacao)
                .map(ReadUsuarioDto::new);
    }

    public ReadUsuarioDto buscarPorId(Long id) {
        Optional<UsuarioModel> usuarioOptional = _usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            return new ReadUsuarioDto(usuarioOptional.get());
        } else {
            throw new UserNotFoundException("Usuário não encontrado!");
        }
    }

    public void excluir(Long id) {
        Optional<UsuarioModel> usuarioOptional = _usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            _usuarioRepository.delete(usuarioOptional.get());
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }
}
