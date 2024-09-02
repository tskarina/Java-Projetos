package com.kbank.carteira_investimentos.dtos;

import java.time.LocalDateTime;
import java.util.List;


public record UsuarioDTO(
        Long id,
        String nome,
        String email,
        String senha,
        LocalDateTime dataCriacao,
        List<CarteiraDTO> carteiras
) {

}
