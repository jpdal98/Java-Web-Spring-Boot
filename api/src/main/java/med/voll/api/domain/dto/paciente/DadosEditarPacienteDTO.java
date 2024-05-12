package med.voll.api.domain.dto.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.dto.DadosEnderecoDTO;

public record DadosEditarPacienteDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEnderecoDTO endereco) {
}
