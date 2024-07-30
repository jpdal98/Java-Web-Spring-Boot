package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.domain.dtos.usuario.DadosCadastroUsuarioDTO;
import med.voll.api.domain.dtos.usuario.DadosEditarUsuarioDTO;
import med.voll.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping(value="/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroUsuarioDTO dados){
        return ResponseEntity.status(HttpStatus.OK).body(service.cadastrar(dados));
    }

    @GetMapping(value="/buscarUsuarios")
    public ResponseEntity<Page<?>> buscarUsuarios(@PageableDefault(size = 10, page = 0, sort = {"nome"})
                                                 Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarUsuarios(paginacao).getBody());
    }

    @PutMapping(value="/atualizar")
    public ResponseEntity<?> atualizar(@RequestBody @Valid DadosEditarUsuarioDTO dados){
        return ResponseEntity.status(HttpStatus.OK).body(service.editar(dados));
    }

    @DeleteMapping(value="/excluir/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.excluir(id));
    }

    @PutMapping(value="/desativar/{id}")
    public ResponseEntity<?> desativar(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.desativar(id));
    }
}
