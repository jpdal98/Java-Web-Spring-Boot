package med.voll.api.domain.dto.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.dto.DadosEnderecoDTO;

public record DadosEditarMedicoDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEnderecoDTO endereco) {
}
