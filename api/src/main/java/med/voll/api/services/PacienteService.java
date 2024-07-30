package med.voll.api.services;

import med.voll.api.domain.dtos.paciente.DadosCadastroPacienteDTO;
import med.voll.api.domain.dtos.paciente.DadosEditarPacienteDTO;
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
