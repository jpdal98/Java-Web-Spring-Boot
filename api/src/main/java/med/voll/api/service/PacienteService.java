package med.voll.api.service;

import med.voll.api.domain.dto.paciente.DadosCadastroPacienteDTO;
import med.voll.api.domain.dto.paciente.DadosEditarPacienteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PacienteService {
    ResponseEntity<?> cadastrar(DadosCadastroPacienteDTO dados);

    ResponseEntity<Page<?>> buscarPacientes(Pageable paginacao);

    ResponseEntity<?> editar(DadosEditarPacienteDTO dados);

    ResponseEntity<?> excluir(Long id);

    ResponseEntity<?> desativar(Long id);
}
