package com.TravessaoBackend.Formulario.services.interfaces;

import com.TravessaoBackend.Formulario.dtos.MatriculaDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MatriculaService {

    MatriculaDTO criarMatricula(MatriculaDTO matriculaDTO);

    Optional<MatriculaDTO> buscarMatriculaPorId(UUID id);

    List<MatriculaDTO> listarMatriculas();

    MatriculaDTO atualizarMatricula(UUID id, MatriculaDTO matriculaDTO);

    void deletarMatricula(UUID id);
}
