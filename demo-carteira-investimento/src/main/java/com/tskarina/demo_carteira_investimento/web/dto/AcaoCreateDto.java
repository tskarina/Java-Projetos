package com.tskarina.demo_carteira_investimento.web.dto;

import com.tskarina.demo_carteira_investimento.entity.Acao;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class AcaoCreateDto {

    @NotBlank(message = "Nome não pode estar vazio")
    private String nome;

    @NotBlank(message = "Tipo de ação não pode ser nulo")
    private Acao.TipoAcao acao;

    @NotBlank(message = "Empresa não pode ser nula")
    private Acao.Empresa empresa;
}
