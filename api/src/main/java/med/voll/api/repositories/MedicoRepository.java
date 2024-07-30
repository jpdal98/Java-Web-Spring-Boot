package med.voll.api.repositories;

import med.voll.api.domain.models.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    boolean existsByNome(String str);

    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
}
