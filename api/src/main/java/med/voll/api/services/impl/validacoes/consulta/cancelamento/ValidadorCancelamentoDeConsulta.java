package med.voll.api.services.impl.validacoes.consulta.cancelamento;

import med.voll.api.domain.dtos.consulta.DadosCancelamentoConsultaDTO;

public interface ValidadorCancelamentoDeConsulta {

    void validar(DadosCancelamentoConsultaDTO dados);

}
