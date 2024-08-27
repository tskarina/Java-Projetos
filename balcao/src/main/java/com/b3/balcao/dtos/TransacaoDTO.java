package com.b3.balcao.dtos;

import com.b3.balcao.enums.TipoTransacao;

import java.time.LocalDateTime;

public record TransacaoDTO(
        Long id,
        AtivoDTO ativo,
        TipoTransacao tipo,
        double valor,
        int quantidade,
        LocalDateTime dataHora,
        ParticipanteDTO participante
) {
}
