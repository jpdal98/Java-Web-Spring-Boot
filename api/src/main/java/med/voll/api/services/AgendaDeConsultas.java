package med.voll.api.services;

import med.voll.api.domain.dtos.consulta.DadosAgendamentoConsultaDTO;
import med.voll.api.domain.dtos.consulta.DadosCancelamentoConsultaDTO;
import org.springframework.http.ResponseEntity;

public interface AgendaDeConsultas {

    ResponseEntity<?> agendar(DadosAgendamentoConsultaDTO dados);

    ResponseEntity<?> cancelar(DadosCancelamentoConsultaDTO dados);
}
