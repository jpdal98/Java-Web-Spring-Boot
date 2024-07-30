package med.voll.api.domain.dtos.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.enums.Role;

public record DadosCadastroUsuarioDTO(
        @NotBlank
        @Email
        String login,
        @NotBlank
        @Pattern(regexp = "\\d{1,255}")
        String senha,
        @NotNull
        Role role) {
}
