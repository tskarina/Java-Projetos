package com.TravessaoBackend.Formulario.services.impl;

import com.TravessaoBackend.Formulario.dtos.MatriculaDTO;
import com.TravessaoBackend.Formulario.models.AlunoModel;
import com.TravessaoBackend.Formulario.models.MatriculaModel;
import com.TravessaoBackend.Formulario.models.ResponsavelModel;
import com.TravessaoBackend.Formulario.models.TurmaModel;
import com.TravessaoBackend.Formulario.repositories.AlunoRepository;
import com.TravessaoBackend.Formulario.repositories.MatriculaRepository;
import com.TravessaoBackend.Formulario.repositories.ResponsavelRepository;
import com.TravessaoBackend.Formulario.repositories.TurmaRepository;
import com.TravessaoBackend.Formulario.services.interfaces.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MatriculaServiceImpl implements MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Override
    public MatriculaDTO criarMatricula(MatriculaDTO matriculaDTO) {
        AlunoModel alunoModel = alunoRepository.findById(matriculaDTO.alunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        ResponsavelModel responsavelModel = responsavelRepository.findById(matriculaDTO.responsavelId())
                .orElseThrow(() -> new RuntimeException("Responsável não encontrado"));

        TurmaModel turmaModel = turmaRepository.findById(matriculaDTO.turmaId())
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        MatriculaModel matriculaModel = new MatriculaModel();
        matriculaModel.setTipoMatricula(matriculaDTO.tipoMatricula());
        matriculaModel.setAluno(alunoModel);
        matriculaModel.setResponsavel(responsavelModel);
        matriculaModel.setTurma(turmaModel);
        matriculaModel.setFormaPagamento(matriculaDTO.formaPagamento());
        matriculaModel.setDataAgendamento(matriculaDTO.dataAgendamento());
        matriculaModel.setHoraAgendamento(matriculaDTO.horaAgendamento());
        matriculaModel.setConfirmacaoTermos(matriculaDTO.confirmacaoTermos());

        MatriculaModel matriculaSalva = matriculaRepository.save(matriculaModel);

        return converterParaDTO(matriculaSalva);
    }

    @Override
    public Optional<MatriculaDTO> buscarMatriculaPorId(UUID id) {
        Optional<MatriculaModel> matriculaModel = matriculaRepository.findById(id);
        return matriculaModel.map(this::converterParaDTO);
    }

    @Override
    public List<MatriculaDTO> listarMatriculas() {
        List<MatriculaModel> matriculas = matriculaRepository.findAll();
        return matriculas.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    @Override
    public MatriculaDTO atualizarMatricula(UUID id, MatriculaDTO matriculaDTO) {
        return null;
    }


    @Override
    public void deletarMatricula(UUID id) {
        matriculaRepository.deleteById(id);
    }

    private MatriculaDTO converterParaDTO(MatriculaModel matriculaModel) {
        return new MatriculaDTO(
                matriculaModel.getId(),
                matriculaModel.getAluno().getId(),
                matriculaModel.getResponsavel().getId(),
                matriculaModel.getTurma().getId(),
                matriculaModel.getTipoMatricula(),
                matriculaModel.getFormaPagamento(),
                matriculaModel.getDataAgendamento(),
                matriculaModel.getHoraAgendamento(),
                matriculaModel.getConfirmacaoTermos()
        );
    }
}
