package com.TravessaoBackend.Formulario.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record AlunoDTO(
        UUID id,
        String nomeCompleto,
        LocalDate dataNascimento,
        String rg,
        String email,
        String telefone,
        String logradouro,
        String bairro,
        String cep,
        UUID responsavelId
) {}
