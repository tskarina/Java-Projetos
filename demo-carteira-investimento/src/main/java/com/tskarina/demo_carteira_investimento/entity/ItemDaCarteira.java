package com.tskarina.demo_carteira_investimento.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "itens_da_carteira")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemDaCarteira implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "carteira_id", nullable = false)
    private Carteira carteira;

    @ManyToOne
    @JoinColumn(name = "acao_id", nullable = false)
    private Acao acao;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "valor_investido", nullable = false)
    private Double valorInvestido;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDaCarteira that = (ItemDaCarteira) o;
        return Objects.equals(id, that.id) && Objects.equals(carteira, that.carteira) && Objects.equals(acao, that.acao) && Objects.equals(quantidade, that.quantidade) && Objects.equals(valorInvestido, that.valorInvestido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carteira, acao, quantidade, valorInvestido);
    }
}
