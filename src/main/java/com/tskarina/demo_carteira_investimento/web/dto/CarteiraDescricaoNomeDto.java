package com.tskarina.demo_carteira_investimento.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarteiraDescricaoNomeDto {

    @NotBlank
    private String descricao;
    @NotBlank
    private String nome;
}
