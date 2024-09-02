package com.kbank.carteira_investimentos.dtos;

import com.kbank.carteira_investimentos.enums.TipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransacaoDTO(
        Long id,
        BigDecimal valor,
        LocalDateTime data,
        TipoTransacao tipo,
        Long investimentoId
) {}
