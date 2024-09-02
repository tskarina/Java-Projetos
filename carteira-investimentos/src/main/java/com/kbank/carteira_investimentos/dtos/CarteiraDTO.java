package com.kbank.carteira_investimentos.dtos;

import com.kbank.carteira_investimentos.enums.TipoCarteira;

import java.util.List;

public record CarteiraDTO(
        Long id,
        String name,
        Long usuarioId,
        TipoCarteira tipo,
        List<InvestimentoDTO> investimentos
) {}
