package com.TravessaoBackend.Formulario.dtos;

import com.TravessaoBackend.Formulario.enums.EstadoCivil;
import com.TravessaoBackend.Formulario.enums.GuardaMenor;

import java.time.LocalDate;
import java.util.UUID;

public record ResponsavelDTO(
        UUID id,
        String nomeCompleto,
        EstadoCivil estadoCivil,
        GuardaMenor guardaMenor,
        String cpf,
        String rg,
        String orgaoEmissor,
        LocalDate dataNascimento,
        String telefone,
        String email,
        String profissao
) {}
