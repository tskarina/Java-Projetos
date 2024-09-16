package com.tskarina.demo_carteira_investimento.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UsuarioCreateDto {

    @NotBlank
    private String nome;

    @NotBlank
    @Email(message = "formato de e-mail está invalido", regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$" )
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
}
