package med.voll.api.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.dtos.consulta.DadosAgendamentoConsultaDTO;
import med.voll.api.domain.dtos.consulta.DadosCancelamentoConsultaDTO;
import med.voll.api.services.AgendaDeConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda;

    @PostMapping(value="/agendar")
    public ResponseEntity<?> agendar(@RequestBody @Valid DadosAgendamentoConsultaDTO dados) {
        return ResponseEntity.status(HttpStatus.OK).body(agenda.agendar(dados));
    }

    @DeleteMapping
    public ResponseEntity<?> cancelar(@RequestBody @Valid DadosCancelamentoConsultaDTO dados) {
        return ResponseEntity.status(HttpStatus.OK).body(agenda.cancelar(dados));
    }

}
