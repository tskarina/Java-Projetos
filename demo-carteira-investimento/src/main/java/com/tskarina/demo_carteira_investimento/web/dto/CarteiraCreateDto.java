package com.tskarina.demo_carteira_investimento.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarteiraCreateDto {

    @NotBlank(message = "O nome da carteira é obrigatório")
    private String nome;

    @NotBlank(message = "Coloque uma descrição para sua carteira")
    private String descricao;

    @NotNull
    private Long usuarioId;
}
