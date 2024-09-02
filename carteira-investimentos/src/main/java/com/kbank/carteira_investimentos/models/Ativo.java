package com.kbank.carteira_investimentos.models;

import com.kbank.carteira_investimentos.enums.TipoAtivo;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ATIVO_TB")
public class Ativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoAtivo tipo;
}
