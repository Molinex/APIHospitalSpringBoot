package spring.alura.med.voll.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private EnderecoMedico enderecoMedico;
    private Boolean ativo;

    public Medico(MedicoCadastroRequest medicoCadastroRequest) {
        this.ativo = true;
        this.nome = medicoCadastroRequest.nome();
        this.email = medicoCadastroRequest.email();
        this.telefone = medicoCadastroRequest.telefone();
        this.crm = medicoCadastroRequest.crm();
        this.especialidade = medicoCadastroRequest.especialidade();
        this.enderecoMedico = new EnderecoMedico(medicoCadastroRequest.endereco());
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCrm() {
        return crm;
    }

    public Long getId() {
        return id;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void atualizaDadosMedico(MedicoAlteracaoRequest medicoAlteracaoRequest) {
        if (medicoAlteracaoRequest.nome() != null) {
            this.nome = medicoAlteracaoRequest.nome();
        }

        if (medicoAlteracaoRequest.telefone() != null) {
            this.telefone = medicoAlteracaoRequest.telefone();
        }

        if (medicoAlteracaoRequest.endereco() != null) {
            this.enderecoMedico.alteraEnderecoMedico(medicoAlteracaoRequest.endereco());
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}
