package med.voll.api.service;

import med.voll.api.domain.dto.DadosCadastroMedicoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;

import java.util.List;

public interface MedicoService {
    ResponseEntity<?> cadastrar(DadosCadastroMedicoDTO dados);

    ResponseEntity<Page<?>> buscarMedicos(Pageable paginacao);
}
