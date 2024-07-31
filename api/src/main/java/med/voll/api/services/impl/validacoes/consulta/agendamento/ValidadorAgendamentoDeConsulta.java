package med.voll.api.services.impl.validacoes.consulta.agendamento;

import med.voll.api.domain.dtos.consulta.DadosAgendamentoConsultaDTO;

public interface ValidadorAgendamentoDeConsulta {

    void validar(DadosAgendamentoConsultaDTO dados);

}
