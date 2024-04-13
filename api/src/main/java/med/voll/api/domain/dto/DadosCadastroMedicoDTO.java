package med.voll.api.domain.dto;

import med.voll.api.domain.enums.Especialidade;

public record DadosCadastroMedicoDTO(String nome, String email, String crm, Especialidade especialidade, DadosEnderecoDTO endereco) {
}
