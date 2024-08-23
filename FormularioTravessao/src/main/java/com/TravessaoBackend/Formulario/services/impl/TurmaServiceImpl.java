package com.TravessaoBackend.Formulario.services.impl;

import com.TravessaoBackend.Formulario.models.TurmaModel;
import com.TravessaoBackend.Formulario.repositories.TurmaRepository;
import com.TravessaoBackend.Formulario.services.interfaces.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaServiceImpl implements TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Override
    public TurmaModel criarTurma(TurmaModel turmaModel) {
        return turmaRepository.save(turmaModel);
    }

    @Override
    public List<TurmaModel> listarTurmas() {
        return turmaRepository.findAll();
    }
}
