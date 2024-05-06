package med.voll.api.service.impl;

import med.voll.api.domain.dto.DadosCadastroMedicoDTO;
import med.voll.api.domain.model.Medico;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private MedicoRepository repository;

    @Override
    public ResponseEntity<?> cadastrar(DadosCadastroMedicoDTO dados) {
        try{
            if(dados == null){
                return ResponseEntity.status(HttpStatus.OK).body("Os dados não foram passados");
            }

            repository.save(new Medico(dados));

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
