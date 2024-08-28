package com.kbank.carteira_investimentos.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record InvestimentoDTO(
        Long id,
        BigDecimal valor,
        BigDecimal quantidade,
        LocalDateTime dataCompra,
        Long ativoId,
        Long carteiraId
) {}
