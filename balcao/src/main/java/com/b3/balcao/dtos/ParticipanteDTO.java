package com.b3.balcao.dtos;

import com.b3.balcao.enums.TipoParticipante;

public record ParticipanteDTO(
        Long id,
        String nome,
        TipoParticipante tipo,
        String cpf,
        String email
) {
}
