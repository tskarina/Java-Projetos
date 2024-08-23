package com.TravessaoBackend.Formulario.services.impl;

import com.TravessaoBackend.Formulario.dtos.ResponsavelDTO;
import com.TravessaoBackend.Formulario.models.ResponsavelModel;
import com.TravessaoBackend.Formulario.repositories.ResponsavelRepository;
import com.TravessaoBackend.Formulario.services.interfaces.ResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ResponsavelServiceImpl implements ResponsavelService {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Override
    public ResponsavelDTO criarResponsavel(ResponsavelDTO responsavelDTO) {
        ResponsavelModel responsavelModel = converterParaModel(responsavelDTO);
        ResponsavelModel responsavelSalvo = responsavelRepository.save(responsavelModel);
        return converterParaDTO(responsavelSalvo);
    }

    @Override
    public Optional<ResponsavelDTO> buscarResponsavelPorId(UUID id) {
        return responsavelRepository.findById(id).map(this::converterParaDTO);
    }

    @Override
    public List<ResponsavelDTO> listarResponsaveis() {
        return responsavelRepository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ResponsavelDTO atualizarResponsavel(UUID id, ResponsavelDTO responsavelDTO) {
        if (responsavelRepository.existsById(id)) {
            ResponsavelModel responsavelModel = converterParaModel(responsavelDTO);
            responsavelModel.setId(id); // Certifique-se de manter o ID ao atualizar
            ResponsavelModel responsavelAtualizado = responsavelRepository.save(responsavelModel);
            return converterParaDTO(responsavelAtualizado);
        }
        return null;
    }

    @Override
    public void deletarResponsavel(UUID id) {
        responsavelRepository.deleteById(id);
    }

    private ResponsavelDTO converterParaDTO(ResponsavelModel responsavelModel) {
        return new ResponsavelDTO(
                responsavelModel.getId(),
                responsavelModel.getNomeCompleto(),
                responsavelModel.getEstadoCivil(),
                responsavelModel.getGuardaMenor(),
                responsavelModel.getCpf(),
                responsavelModel.getRg(),
                responsavelModel.getOrgaoEmissor(),
                responsavelModel.getDataNascimento(),
                responsavelModel.getTelefone(),
                responsavelModel.getEmail(),
                responsavelModel.getProfissao()
        );
    }

    private ResponsavelModel converterParaModel(ResponsavelDTO responsavelDTO) {
        ResponsavelModel responsavelModel = new ResponsavelModel();
        responsavelModel.setNomeCompleto(responsavelDTO.nomeCompleto());
        responsavelModel.setEstadoCivil(responsavelDTO.estadoCivil());
        responsavelModel.setGuardaMenor(responsavelDTO.guardaMenor());
        responsavelModel.setCpf(responsavelDTO.cpf());
        responsavelModel.setRg(responsavelDTO.rg());
        responsavelModel.setOrgaoEmissor(responsavelDTO.orgaoEmissor());
        responsavelModel.setDataNascimento(responsavelDTO.dataNascimento());
        responsavelModel.setTelefone(responsavelDTO.telefone());
        responsavelModel.setEmail(responsavelDTO.email());
        responsavelModel.setProfissao(responsavelDTO.profissao());
        return responsavelModel;
    }
}
