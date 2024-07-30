package med.voll.api.services.impl;

import med.voll.api.domain.dtos.usuario.DadosCadastroUsuarioDTO;
import med.voll.api.domain.dtos.usuario.DadosEditarUsuarioDTO;
import med.voll.api.domain.models.Endereco;
import med.voll.api.domain.models.Medico;
import med.voll.api.domain.models.Usuario;
import med.voll.api.infra.security.TokenService;
import med.voll.api.repositories.UsuarioRepository;
import med.voll.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService service;

    @Override
    @Transactional
    public ResponseEntity<?> cadastrar(DadosCadastroUsuarioDTO dados) {
        try{
            String senhaAutenticada = service.gerarToken(new Usuario(dados));
            repository.save(new Usuario(dados)).setSenha(senhaAutenticada);
            return ResponseEntity.status(HttpStatus.OK).body("Cadastro realizado com sucesso!");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Page<?>> buscarUsuarios(Pageable paginacao) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(repository.findAllByAtivoTrue(paginacao));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> editar(DadosEditarUsuarioDTO dados) {
        try{
            var usuario = repository.getReferenceById(dados.id());
            usuario.setSenha(dados.senha());
            usuario.setLogin(dados.login());
            usuario.setSenha(service.gerarToken(usuario));
            return ResponseEntity.status(HttpStatus.OK).body("Dados atualizados com sucesso!");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> excluir(Long id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Usuário excluido com sucesso!");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<?> desativar(Long id) {
        try{
            var usuario = repository.getReferenceById(id);
            usuario.setAtivo(false);
            return ResponseEntity.status(HttpStatus.OK).body("Conta desativada com sucesso!");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
