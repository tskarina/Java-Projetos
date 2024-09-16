package com.tskarina.demo_carteira_investimento.web.dto;

import com.tskarina.demo_carteira_investimento.entity.Acao;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class AcaoResponseDto {

    private Long id;
    private String nome;
    private Acao.TipoAcao acao;
    private Acao.Empresa empresa;

}
