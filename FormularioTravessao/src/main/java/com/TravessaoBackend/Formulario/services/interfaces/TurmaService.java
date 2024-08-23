package com.TravessaoBackend.Formulario.services.interfaces;

import com.TravessaoBackend.Formulario.models.TurmaModel;


import java.util.List;

public interface TurmaService {


    TurmaModel criarTurma(TurmaModel turmaModel);

    List<TurmaModel> listarTurmas();

}
