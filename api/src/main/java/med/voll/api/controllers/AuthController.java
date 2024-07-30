package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.domain.dtos.DadosAuthDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/login")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAuthDTO dados){
        var token = new DadosAuthDTO(dados.login(), dados.senha());
        var auth = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }
}
