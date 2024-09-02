package com.kbank.carteira_investimentos.enums;

import lombok.Getter;

@Getter
public enum TipoCarteira {
    POUPANCA("Carteira de Poupança"),
    INVESTIMENTO("Carteira de Investimento"),
    APOSENTADORIA("Carteira de Aposentadoria"),
    RENDA_FIXA("Carteira de Renda Fixa"),
    RENDA_VARIAVEL("Carteira de Renda Variável"),
    CRIPTOMOEDAS("Carteira de Criptomoedas"),
    IMOBILIARIO("Carteira Imobiliária"),
    EMERGENCIA("Carteira de Emergência");

    private final String descricao;

    TipoCarteira(String descricao) {
        this.descricao = descricao;
    }
}
