package med.voll.api.services.impl;

import med.voll.api.domain.dtos.paciente.DadosCadastroPacienteDTO;
import med.voll.api.domain.dtos.paciente.DadosEditarPacienteDTO;
import med.voll.api.domain.models.Endereco;
import med.voll.api.domain.models.Paciente;
import med.voll.api.repositories.PacienteRepository;
import med.voll.api.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
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
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Page<?>> buscarPacientes(Pageable paginacao) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(repository.findAllByAtivoTrue(paginacao));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> editar(DadosEditarPacienteDTO dados) {
        try{
            var paciente = repository.getReferenceById(dados.id());
            paciente.setNome(dados.nome());
            paciente.setTelefone(dados.telefone());
            paciente.setEndereco(new Endereco(dados.endereco()));

            return ResponseEntity.status(HttpStatus.OK).body("Dados atualizados com sucesso!");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> excluir(Long id) {
        try {
            repository.deleteById(id);

            return ResponseEntity.status(HttpStatus.OK).body("Paciente excluido com sucesso!");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<?> desativar(Long id) {
        try{
            var medico = repository.getReferenceById(id);
            medico.setAtivo(false);

            return ResponseEntity.status(HttpStatus.OK).body("Conta desativada com sucesso!");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
