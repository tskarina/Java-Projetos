package com.tskarina.demo_carteira_investimento.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemDaCarteiraCreateDto {

    @NotNull(message = "Carteira ID não pode ser nulo")
    private Long carteiraId;

    @NotNull(message = "Ação ID não pode ser nulo")
    private Long acaoId;

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade deve ser pelo menos 1")
    private Integer quantidade;

    @NotNull(message = "O valor investido é obrigatório")
    @Min(value = 0, message = "O valor investido deve ser positivo")
    private Double valorInvestido;
}
