package med.voll.api.services.impl;

import med.voll.api.repositories.UsuarioRepository;
import med.voll.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;
}
