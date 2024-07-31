package med.voll.api.services.impl;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.dtos.usuario.DadosCadastroUsuarioDTO;
import med.voll.api.domain.dtos.usuario.DadosEditarUsuarioDTO;
import med.voll.api.domain.entities.Usuario;
import med.voll.api.infra.exception.TratadorDeErros;
import med.voll.api.domain.repositories.UsuarioRepository;
import med.voll.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public ResponseEntity<?> cadastrar(DadosCadastroUsuarioDTO dados) {
        try{
            var usuario = new Usuario(dados);
            usuario.setSenha(passwordEncoder.encode(dados.senha()));
            repository.save(usuario);
            return ResponseEntity.status(HttpStatus.OK).body("Cadastro realizado com sucesso!");
        }catch (DataIntegrityViolationException e) {
            return new TratadorDeErros().tratarErroIntegridadeBD(e);
        }catch (Exception e){
            return new TratadorDeErros().tratarErro500(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Page<?>> buscarUsuarios(Pageable paginacao) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(repository.findAllByAtivoTrue(paginacao));
        }catch (Exception e){
            return new TratadorDeErros().tratarErro500(e);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> editar(DadosEditarUsuarioDTO dados) {
        try{
            var usuario = repository.getById(dados.id());
            usuario.setLogin(dados.login());
            usuario.setSenha(passwordEncoder.encode(dados.senha()));
            usuario.setAtivo(dados.ativo());
            return ResponseEntity.status(HttpStatus.OK).body("Dados atualizados com sucesso!");
        }catch (EntityNotFoundException e) {
            return new TratadorDeErros().tratarErro404();
        }catch (DataIntegrityViolationException e) {
            return new TratadorDeErros().tratarErroIntegridadeBD(e);
        }catch (Exception e){
            return new TratadorDeErros().tratarErro500(e);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> excluir(Long id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Usuário excluido com sucesso!");
        }catch (EntityNotFoundException e) {
            return new TratadorDeErros().tratarErro404();
        }catch (DataIntegrityViolationException e) {
            return new TratadorDeErros().tratarErroIntegridadeBD(e);
        }catch (Exception e){
            return new TratadorDeErros().tratarErro500(e);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> desativar(Long id) {
        try{
            var usuario = repository.getById(id);
            usuario.setAtivo(false);
            return ResponseEntity.status(HttpStatus.OK).body("Conta desativada com sucesso!");
        }catch (EntityNotFoundException e) {
            return new TratadorDeErros().tratarErro404();
        }catch (DataIntegrityViolationException e) {
            return new TratadorDeErros().tratarErroIntegridadeBD(e);
        }catch (Exception e){
            return new TratadorDeErros().tratarErro500(e);
        }
    }
}
