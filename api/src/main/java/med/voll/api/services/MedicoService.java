package med.voll.api.services;

import med.voll.api.domain.dtos.medico.DadosCadastroMedicoDTO;
import med.voll.api.domain.dtos.medico.DadosEditarMedicoDTO;
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
