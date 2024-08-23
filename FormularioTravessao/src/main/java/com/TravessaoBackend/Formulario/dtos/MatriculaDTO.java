package com.TravessaoBackend.Formulario.dtos;

import com.TravessaoBackend.Formulario.enums.FormaPagamento;
import com.TravessaoBackend.Formulario.enums.TipoMatricula;
import com.TravessaoBackend.Formulario.models.TurmaModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record MatriculaDTO(
        UUID id,
        UUID alunoId,
        UUID responsavelId,
        UUID turmaId,
        TipoMatricula tipoMatricula,
        FormaPagamento formaPagamento,
        LocalDate dataAgendamento,
        LocalTime horaAgendamento,
        Boolean confirmacaoTermos
) {}
