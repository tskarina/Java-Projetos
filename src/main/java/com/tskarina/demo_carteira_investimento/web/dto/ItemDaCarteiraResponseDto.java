package com.tskarina.demo_carteira_investimento.web.dto;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemDaCarteiraResponseDto {
    private Long id;
    private Long carteiraId;
    private String usuarioNome;
    private String carteiraNome;
    private String acaoNome;
    private Integer quantidade;
    private Double valorInvestido;
}
