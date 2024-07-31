package med.voll.api.domain.dtos.usuario;

import jakarta.validation.constraints.NotNull;

public record DadosEditarUsuarioDTO(
        @NotNull
        Long id,
        String login,
        String senha,
        boolean ativo) {
}
