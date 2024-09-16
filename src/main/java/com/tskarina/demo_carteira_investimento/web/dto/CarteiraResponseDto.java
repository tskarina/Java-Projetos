package com.tskarina.demo_carteira_investimento.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarteiraResponseDto {
    private Long id;
    private String nome;
    private String descricao;
    private Long usuarioId;
    private String usuarioNome;
}
