package com.kbank.carteira_investimentos.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransacaoDTO(
        Long id,
        BigDecimal valor,
        LocalDateTime data,
        String tipo,
        Long ativoId,
        Long investimentoId
) {}
