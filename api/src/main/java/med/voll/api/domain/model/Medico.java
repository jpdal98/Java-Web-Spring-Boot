package med.voll.api.domain.model;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.dto.medico.DadosCadastroMedicoDTO;
import med.voll.api.domain.enums.Especialidade;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;
    private boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(DadosCadastroMedicoDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.telefone = dados.telefone();
        this.ativo = true;
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }
}
