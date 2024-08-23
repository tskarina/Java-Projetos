package com.TravessaoBackend.Formulario.services.impl;

import com.TravessaoBackend.Formulario.dtos.AlunoDTO;
import com.TravessaoBackend.Formulario.models.AlunoModel;
import com.TravessaoBackend.Formulario.models.ResponsavelModel;
import com.TravessaoBackend.Formulario.repositories.AlunoRepository;
import com.TravessaoBackend.Formulario.repositories.ResponsavelRepository;
import com.TravessaoBackend.Formulario.services.interfaces.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Override
    public AlunoDTO criarAluno(AlunoDTO alunoDTO) {
        AlunoModel alunoModel = new AlunoModel();
        alunoModel.setNomeCompleto(alunoDTO.nomeCompleto());
        alunoModel.setDataNascimento(alunoDTO.dataNascimento());
        alunoModel.setRg(alunoDTO.rg());
        alunoModel.setEmail(alunoDTO.email());
        alunoModel.setTelefone(alunoDTO.telefone());
        alunoModel.setLogradouro(alunoDTO.logradouro());
        alunoModel.setBairro(alunoDTO.bairro());
        alunoModel.setCep(alunoDTO.cep());

        // Buscar o responsável pelo ID
        ResponsavelModel responsavel = responsavelRepository.findById(alunoDTO.responsavelId()).orElse(null);
        alunoModel.setResponsavel(responsavel);

        AlunoModel alunoSalvo = alunoRepository.save(alunoModel);
        return converterParaDTO(alunoSalvo);
    }

    @Override
    public Optional<AlunoDTO> buscarAlunoPorId(UUID id) {
        Optional<AlunoModel> alunoModel = alunoRepository.findById(id);
        return alunoModel.map(this::converterParaDTO);
    }

    @Override
    public List<AlunoDTO> listarAlunos() {
        List<AlunoModel> alunos = alunoRepository.findAll();
        return alunos.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    @Override
    public AlunoDTO atualizarAluno(UUID id, AlunoDTO alunoDTO) {
        if (alunoRepository.existsById(id)) {
            AlunoModel alunoModel = alunoRepository.findById(id).orElseThrow();
            alunoModel.setNomeCompleto(alunoDTO.nomeCompleto());
            alunoModel.setDataNascimento(alunoDTO.dataNascimento());
            alunoModel.setRg(alunoDTO.rg());
            alunoModel.setEmail(alunoDTO.email());
            alunoModel.setTelefone(alunoDTO.telefone());
            alunoModel.setLogradouro(alunoDTO.logradouro());
            alunoModel.setBairro(alunoDTO.bairro());
            alunoModel.setCep(alunoDTO.cep());

            // Atualizar o responsável
            ResponsavelModel responsavel = responsavelRepository.findById(alunoDTO.responsavelId()).orElse(null);
            alunoModel.setResponsavel(responsavel);

            AlunoModel alunoAtualizado = alunoRepository.save(alunoModel);
            return converterParaDTO(alunoAtualizado);
        }
        return null;
    }

    @Override
    public void deletarAluno(UUID id) {
        alunoRepository.deleteById(id);
    }

    private AlunoDTO converterParaDTO(AlunoModel alunoModel) {
        return new AlunoDTO(
                alunoModel.getId(),
                alunoModel.getNomeCompleto(),
                alunoModel.getDataNascimento(),
                alunoModel.getRg(),
                alunoModel.getEmail(),
                alunoModel.getTelefone(),
                alunoModel.getLogradouro(),
                alunoModel.getBairro(),
                alunoModel.getCep(),
                alunoModel.getResponsavel() != null ? alunoModel.getResponsavel().getId() : null
        );
    }
}
