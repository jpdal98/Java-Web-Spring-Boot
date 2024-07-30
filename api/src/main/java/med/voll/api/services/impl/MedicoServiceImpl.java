package med.voll.api.services.impl;

import med.voll.api.domain.dtos.medico.DadosCadastroMedicoDTO;
import med.voll.api.domain.dtos.medico.DadosEditarMedicoDTO;
import med.voll.api.domain.models.Endereco;
import med.voll.api.domain.models.Medico;
import med.voll.api.repositories.MedicoRepository;
import med.voll.api.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
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
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Page<?>> buscarMedicos(Pageable paginacao) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(repository.findAllByAtivoTrue(paginacao));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> editar(DadosEditarMedicoDTO dados) {
        try{
            var medico = repository.getReferenceById(dados.id());
            medico.setNome(dados.nome());
            medico.setTelefone(dados.telefone());
            medico.setEndereco(new Endereco(dados.endereco()));

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

            return ResponseEntity.status(HttpStatus.OK).body("Medico excluido com sucesso!");
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
