package med.voll.api.domain.dtos.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.dtos.DadosEnderecoDTO;

public record DadosEditarPacienteDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEnderecoDTO endereco) {
}
