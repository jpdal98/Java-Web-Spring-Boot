package med.voll.api.service;

import med.voll.api.domain.dto.DadosCadastroMedicoDTO;
import org.springframework.http.ResponseEntity;

public interface MedicoService {
    ResponseEntity<?> cadastrar(DadosCadastroMedicoDTO dados);
}
