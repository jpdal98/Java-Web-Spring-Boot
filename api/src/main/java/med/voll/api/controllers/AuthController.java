package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.domain.dtos.DadosAuthDTO;
import med.voll.api.domain.dtos.DadosTokenJWTDTO;
import med.voll.api.domain.models.Usuario;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/login")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAuthDTO dados){
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var auth = manager.authenticate(token);
        var tokenJWT =  tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWTDTO(tokenJWT));
    }
}
