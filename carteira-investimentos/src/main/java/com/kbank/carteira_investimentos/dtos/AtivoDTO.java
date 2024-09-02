package com.kbank.carteira_investimentos.dtos;

import com.kbank.carteira_investimentos.enums.TipoAtivo;

public record AtivoDTO(Long id, String nome, TipoAtivo tipo) {
}
