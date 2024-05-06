package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.dto.DadosCadastroMedicoDTO;
import med.voll.api.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Page<?>> buscarMedicos(Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarMedicos(paginacao).getBody());
    }

}
