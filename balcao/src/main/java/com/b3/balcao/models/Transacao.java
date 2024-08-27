package com.b3.balcao.models;


import com.b3.balcao.enums.TipoTransacao;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ativo_id", nullable = false)
    private Ativo ativo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTransacao tipo;

    private double valor;
    private int quantidade;
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "participante_id", nullable = false)

    private Participante participante;
}
