package formulario.FormularioTravessao.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataAgendamento;

    private String formaPagamento;

    private String horarioAgendamento;

    @ManyToOne(cascade = CascadeType.ALL)
    private Turma turma;

    @ManyToOne(cascade = CascadeType.ALL)
    private Aluno aluno;

    @ManyToOne(cascade = CascadeType.ALL)
    private Responsavel responsavel;

    private String horarios;

    private boolean termosConfirmados;

    private String tipoMatricula;
}
