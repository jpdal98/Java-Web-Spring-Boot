package med.voll.api.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosAuthDTO(

        @NotBlank
        @Email
        String login,

        @NotBlank
        @Pattern(regexp = "\\d{1,255}")
        String senha) {
}
