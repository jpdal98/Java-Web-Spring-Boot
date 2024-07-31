package med.voll.api.services.impl;

import med.voll.api.domain.dtos.consulta.DadosAgendamentoConsultaDTO;
import med.voll.api.domain.dtos.consulta.DadosCancelamentoConsultaDTO;
import med.voll.api.domain.entities.Consulta;
import med.voll.api.domain.entities.Medico;
import med.voll.api.domain.repositories.ConsultaRepository;
import med.voll.api.domain.repositories.MedicoRepository;
import med.voll.api.domain.repositories.PacienteRepository;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.services.AgendaDeConsultas;
import med.voll.api.services.impl.validacoes.consulta.agendamento.ValidadorAgendamentoDeConsulta;
import med.voll.api.services.impl.validacoes.consulta.cancelamento.ValidadorCancelamentoDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AgendaDeConsultasImpl implements AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

    @Override
    @Transactional
    public ResponseEntity<?> agendar(DadosAgendamentoConsultaDTO dados) {
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do médico informado não existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        if (medico == null) {
            throw new ValidacaoException("Não existe médico disponível nessa data!");
        }

        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
        consultaRepository.save(consulta);

        return ResponseEntity.status(HttpStatus.OK).body("Consulta marcada com sucesso!");
    }

    @Override
    @Transactional
    public ResponseEntity<?> cancelar(DadosCancelamentoConsultaDTO dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
        return ResponseEntity.status(HttpStatus.OK).body("Consulta cancelada com sucesso!");
    }


    private Medico escolherMedico(DadosAgendamentoConsultaDTO dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

}
