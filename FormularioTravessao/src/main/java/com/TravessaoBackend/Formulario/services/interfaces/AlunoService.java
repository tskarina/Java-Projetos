package com.TravessaoBackend.Formulario.services.interfaces;

import com.TravessaoBackend.Formulario.dtos.AlunoDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AlunoService {

    AlunoDTO criarAluno(AlunoDTO alunoDTO);

    Optional<AlunoDTO> buscarAlunoPorId(UUID id);

    List<AlunoDTO> listarAlunos();

    AlunoDTO atualizarAluno(UUID id, AlunoDTO alunoDTO);

    void deletarAluno(UUID id);
}