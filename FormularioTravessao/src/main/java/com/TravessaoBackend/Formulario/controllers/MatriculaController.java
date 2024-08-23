package com.TravessaoBackend.Formulario.controllers;

import com.TravessaoBackend.Formulario.dtos.MatriculaDTO;
import com.TravessaoBackend.Formulario.services.interfaces.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @PostMapping
    public ResponseEntity<MatriculaDTO> criarMatricula(@RequestBody MatriculaDTO matriculaDTO) {
        try {
            MatriculaDTO novaMatricula = matriculaService.criarMatricula(matriculaDTO);
            return new ResponseEntity<>(novaMatricula, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaDTO> buscarMatriculaPorId(@PathVariable UUID id) {
        Optional<MatriculaDTO> matricula = matriculaService.buscarMatriculaPorId(id);
        return matricula.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<MatriculaDTO>> listarMatriculas() {
        List<MatriculaDTO> matriculas = matriculaService.listarMatriculas();
        return new ResponseEntity<>(matriculas, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculaDTO> atualizarMatricula(@PathVariable UUID id, @RequestBody MatriculaDTO matriculaDTO) {
        try {
            MatriculaDTO matriculaAtualizada = matriculaService.atualizarMatricula(id, matriculaDTO);
            return new ResponseEntity<>(matriculaAtualizada, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMatricula(@PathVariable UUID id) {
        try {
            matriculaService.deletarMatricula(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
