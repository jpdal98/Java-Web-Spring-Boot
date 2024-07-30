package med.voll.api.domain.dtos.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.dtos.DadosEnderecoDTO;

public record DadosEditarMedicoDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEnderecoDTO endereco) {
}
