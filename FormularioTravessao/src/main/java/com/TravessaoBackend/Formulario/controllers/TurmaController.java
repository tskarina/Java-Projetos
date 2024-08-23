package com.TravessaoBackend.Formulario.controllers;

import com.TravessaoBackend.Formulario.models.TurmaModel;
import com.TravessaoBackend.Formulario.services.interfaces.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @PostMapping
    public ResponseEntity<TurmaModel> criarTurma(@RequestBody TurmaModel turmaModel) {
        TurmaModel turmaSalva = turmaService.criarTurma(turmaModel);
        return ResponseEntity.ok(turmaSalva);
    }

    @GetMapping
    public ResponseEntity<List<TurmaModel>> listarTurmas() {
        List<TurmaModel> turmas = turmaService.listarTurmas();
        return new ResponseEntity<>(turmas, HttpStatus.OK);
    }
}
