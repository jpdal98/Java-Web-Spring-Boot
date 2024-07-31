package med.voll.api.services.impl;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.dtos.paciente.DadosCadastroPacienteDTO;
import med.voll.api.domain.dtos.paciente.DadosEditarPacienteDTO;
import med.voll.api.domain.entities.Endereco;
import med.voll.api.domain.entities.Paciente;
import med.voll.api.infra.exception.TratadorDeErros;
import med.voll.api.domain.repositories.PacienteRepository;
import med.voll.api.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PacienteServiceImpl implements PacienteService {
    @Autowired
    private PacienteRepository repository;

    @Override
    @Transactional
    public ResponseEntity<?> cadastrar(DadosCadastroPacienteDTO dados) {
        try{
            repository.save(new Paciente(dados));
            return ResponseEntity.status(HttpStatus.OK).body("Cadastro realizado com sucesso!");
        }catch (DataIntegrityViolationException e) {
            return new TratadorDeErros().tratarErroIntegridadeBD(e);
        }catch (Exception e){
            return new TratadorDeErros().tratarErro500(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Page<?>> buscarPacientes(Pageable paginacao) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(repository.findAllByAtivoTrue(paginacao));
        }catch (Exception e){
            return new TratadorDeErros().tratarErro500(e);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> editar(DadosEditarPacienteDTO dados) {
        try{
            var paciente = repository.getById(dados.id());
            paciente.setNome(dados.nome());
            paciente.setTelefone(dados.telefone());
            paciente.setEndereco(new Endereco(dados.endereco()));

            return ResponseEntity.status(HttpStatus.OK).body("Dados atualizados com sucesso!");
        }catch (EntityNotFoundException e) {
            return new TratadorDeErros().tratarErro404();
        }catch (DataIntegrityViolationException e) {
            return new TratadorDeErros().tratarErroIntegridadeBD(e);
        }catch (Exception e){
            return new TratadorDeErros().tratarErro500(e);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> excluir(Long id) {
        try {
            repository.deleteById(id);

            return ResponseEntity.status(HttpStatus.OK).body("Paciente excluido com sucesso!");
        }catch (EntityNotFoundException e) {
            return new TratadorDeErros().tratarErro404();
        }catch (DataIntegrityViolationException e) {
            return new TratadorDeErros().tratarErroIntegridadeBD(e);
        }catch (Exception e){
            return new TratadorDeErros().tratarErro500(e);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> desativar(Long id) {
        try{
            var medico = repository.getById(id);
            medico.setAtivo(false);

            return ResponseEntity.status(HttpStatus.OK).body("Conta desativada com sucesso!");
        }catch (EntityNotFoundException e) {
            return new TratadorDeErros().tratarErro404();
        }catch (DataIntegrityViolationException e) {
            return new TratadorDeErros().tratarErroIntegridadeBD(e);
        }catch (Exception e){
            return new TratadorDeErros().tratarErro500(e);
        }
    }
}
