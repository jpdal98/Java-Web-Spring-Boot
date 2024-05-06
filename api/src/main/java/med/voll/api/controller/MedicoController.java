package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.dto.DadosCadastroMedicoDTO;
import med.voll.api.domain.dto.DadosEditarMedicoDTO;
import med.voll.api.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @PostMapping(value="/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroMedicoDTO dados){
        return ResponseEntity.status(HttpStatus.OK).body(service.cadastrar(dados));
    }

    @GetMapping(value="/buscarMedicos")
    public ResponseEntity<Page<?>> buscarMedicos(@PageableDefault(size = 10, page = 0, sort = {"nome"})
                                                     Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarMedicos(paginacao).getBody());
    }

    @PutMapping(value="/atualizar")
    public ResponseEntity<?> atualizar(@RequestBody @Valid DadosEditarMedicoDTO dados){
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
