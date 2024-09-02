package com.kbank.carteira_investimentos.dtos;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record InvestimentoDTO(
        Long id,
        BigDecimal valor,
        BigDecimal quantidade,
        LocalDateTime dataCompra,
        AtivoDTO ativo,
        Long carteiraId,
        List<TransacaoDTO> transacoes
) {}
