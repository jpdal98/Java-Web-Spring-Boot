package med.voll.api.service.impl;

import med.voll.api.domain.dto.DadosCadastroMedicoDTO;
import med.voll.api.domain.model.Medico;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private MedicoRepository repository;

    @Override
    @Transactional
    public ResponseEntity<?> cadastrar(DadosCadastroMedicoDTO dados) {
        try{
            if(repository.existsByNome(dados.nome())){
                return ResponseEntity.status(HttpStatus.OK).body("Este medico já foi cadastrado");
            }

            if(dados == null){
                return ResponseEntity.status(HttpStatus.OK).body("Os dados não foram passados");
            }

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
            return ResponseEntity.status(HttpStatus.OK).body(repository.findAll(paginacao));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
