package med.voll.api.service;

import med.voll.api.domain.dto.medico.DadosCadastroMedicoDTO;
import med.voll.api.domain.dto.medico.DadosEditarMedicoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface MedicoService {
    ResponseEntity<?> cadastrar(DadosCadastroMedicoDTO dados);

    ResponseEntity<Page<?>> buscarMedicos(Pageable paginacao);

    ResponseEntity<?> editar(DadosEditarMedicoDTO dados);

    ResponseEntity<?> excluir(Long id);

    ResponseEntity<?> desativar(Long id);
}
