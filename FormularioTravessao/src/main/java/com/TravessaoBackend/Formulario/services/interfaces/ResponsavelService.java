package com.TravessaoBackend.Formulario.services.interfaces;

import com.TravessaoBackend.Formulario.dtos.ResponsavelDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResponsavelService {

    ResponsavelDTO criarResponsavel(ResponsavelDTO responsavelDTO);

    Optional<ResponsavelDTO> buscarResponsavelPorId(UUID id);

    List<ResponsavelDTO> listarResponsaveis();

    ResponsavelDTO atualizarResponsavel(UUID id, ResponsavelDTO responsavelDTO);

    void deletarResponsavel(UUID id);
}
