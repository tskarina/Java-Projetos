package com.tskarina.demo_carteira_investimento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "acoes")
public class Acao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "acao", nullable = false)
    private TipoAcao acao;

    @Enumerated(EnumType.STRING)
    @Column(name = "empresa", nullable = false)
    private Empresa empresa;

    public enum TipoAcao {
        TECNOLOGIA,
        SAUDE,
        FINANCEIRO,
        ENERGIA,
        CONSUMO
    }

    public enum Empresa {
        TOTVS,
        MOVILE,
        FLEURY,
        DASA,
        ITAU,
        BANCO_DO_BRASIL,
        PETROBRAS,
        ELETROBRAS,
        AMBEV,
        MAGAZINE_LUIZA
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Acao acoes = (Acao) o;
        return Objects.equals(id, acoes.id) && Objects.equals(nome, acoes.nome) && acao == acoes.acao && empresa == acoes.empresa;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, acao, empresa);
    }

    @Override
    public String toString() {
        return "Acoes{" +
                "id=" + id +
                '}';
    }
}
