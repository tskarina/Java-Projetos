package com.b3.balcao.models;

import com.b3.balcao.enums.TipoParticipante;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_participantes")
public class Participante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoParticipante tipo;

    private String cpf;
    private String email;

    @OneToMany(mappedBy = "participante", fetch = FetchType.LAZY)
    @JsonBackReference

    private List<Transacao> transacoes;
}
