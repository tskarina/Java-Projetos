package com.tskarina.demo_carteira_investimento.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class UsuarioResponseDto {
    private Long id;
    private String nome;
    private String email;
}
