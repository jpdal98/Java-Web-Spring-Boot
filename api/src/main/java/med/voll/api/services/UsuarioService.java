package med.voll.api.services;

import med.voll.api.domain.dtos.usuario.DadosCadastroUsuarioDTO;
import med.voll.api.domain.dtos.usuario.DadosEditarUsuarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UsuarioService {

    ResponseEntity<?> cadastrar(DadosCadastroUsuarioDTO dados);

    ResponseEntity<Page<?>> buscarUsuarios(Pageable paginacao);

    ResponseEntity<?> editar(DadosEditarUsuarioDTO dados);

    ResponseEntity<?> excluir(Long id);

    ResponseEntity<?> desativar(Long id);
}
