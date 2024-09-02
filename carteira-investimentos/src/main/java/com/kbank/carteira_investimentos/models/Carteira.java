package com.kbank.carteira_investimentos.models;

import com.kbank.carteira_investimentos.enums.TipoCarteira;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="CARTEIRA_TB")
public class Carteira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCarteira tipo;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
