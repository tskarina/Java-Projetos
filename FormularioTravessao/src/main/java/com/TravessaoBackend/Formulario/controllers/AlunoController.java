package com.TravessaoBackend.Formulario.controllers;

import com.TravessaoBackend.Formulario.dtos.AlunoDTO;
import com.TravessaoBackend.Formulario.services.interfaces.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<AlunoDTO> criarAluno(@RequestBody AlunoDTO alunoDTO) {
        AlunoDTO alunoCriado = alunoService.criarAluno(alunoDTO);
        return new ResponseEntity<>(alunoCriado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> buscarAlunoPorId(@PathVariable UUID id) {
        Optional<AlunoDTO> alunoDTO = alunoService.buscarAlunoPorId(id);
        return alunoDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> listarAlunos() {
        List<AlunoDTO> alunos = alunoService.listarAlunos();
        return ResponseEntity.ok(alunos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTO> atualizarAluno(@PathVariable UUID id, @RequestBody AlunoDTO alunoDTO) {
        AlunoDTO alunoAtualizado = alunoService.atualizarAluno(id, alunoDTO);
        return alunoAtualizado != null ? ResponseEntity.ok(alunoAtualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable UUID id) {
        alunoService.deletarAluno(id);
        return ResponseEntity.noContent().build();
    }
}
