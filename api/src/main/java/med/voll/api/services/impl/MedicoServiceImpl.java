package med.voll.api.services.impl;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.dtos.medico.DadosCadastroMedicoDTO;
import med.voll.api.domain.dtos.medico.DadosEditarMedicoDTO;
import med.voll.api.domain.entities.Endereco;
import med.voll.api.domain.entities.Medico;
import med.voll.api.infra.exception.TratadorDeErros;
import med.voll.api.domain.repositories.MedicoRepository;
import med.voll.api.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private MedicoRepository repository;

    @Override
    @Transactional
    public ResponseEntity<?> cadastrar(DadosCadastroMedicoDTO dados) {
        try{
            repository.save(new Medico(dados));
            return ResponseEntity.status(HttpStatus.OK).body("Cadastro realizado com sucesso!");
        }catch (DataIntegrityViolationException e) {
            return new TratadorDeErros().tratarErroIntegridadeBD(e);
        }catch (Exception e){
            return new TratadorDeErros().tratarErro500(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Page<?>> buscarMedicos(Pageable paginacao) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(repository.findAllByAtivoTrue(paginacao));
        }catch (Exception e){
            return new TratadorDeErros().tratarErro500(e);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> editar(DadosEditarMedicoDTO dados) {
        try{
            var medico = repository.getById(dados.id());
            medico.setNome(dados.nome());
            medico.setTelefone(dados.telefone());
            medico.setEndereco(new Endereco(dados.endereco()));

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
            return ResponseEntity.status(HttpStatus.OK).body("Medico excluido com sucesso!");
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
