package med.voll.api.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.dtos.paciente.DadosCadastroPacienteDTO;
import med.voll.api.domain.dtos.paciente.DadosEditarPacienteDTO;
import med.voll.api.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroPacienteDTO dados) {
        return ResponseEntity.status(HttpStatus.OK).body(service.cadastrar(dados));
    }

    @GetMapping(value = "/buscarPacientes")
    public ResponseEntity<Page<?>> buscarPacientes(@PageableDefault(size = 10, page = 0, sort = {"nome"})
                                                   Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPacientes(paginacao).getBody());
    }

    @PutMapping(value = "/atualizar")
    public ResponseEntity<?> atualizar(@RequestBody @Valid DadosEditarPacienteDTO dados) {
        return ResponseEntity.status(HttpStatus.OK).body(service.editar(dados));
    }

    @DeleteMapping(value = "/excluir/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.excluir(id));
    }

    @PutMapping(value = "/desativar/{id}")
    public ResponseEntity<?> desativar(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.desativar(id));
    }
}
