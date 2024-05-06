package med.voll.api.domain.dto;

import jakarta.validation.constraints.NotNull;

public record DadosEditarMedicoDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEnderecoDTO endereco) {
}
