package com.TravessaoBackend.Formulario.controllers;

import com.TravessaoBackend.Formulario.dtos.ResponsavelDTO;
import com.TravessaoBackend.Formulario.services.interfaces.ResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/responsaveis")
public class ResponsavelController {

    @Autowired
    private ResponsavelService responsavelService;

    @PostMapping
    public ResponseEntity<ResponsavelDTO> criarResponsavel(@RequestBody ResponsavelDTO responsavelDTO) {
        ResponsavelDTO criado = responsavelService.criarResponsavel(responsavelDTO);
        return new ResponseEntity<>(criado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsavelDTO> buscarResponsavelPorId(@PathVariable UUID id) {
        Optional<ResponsavelDTO> responsavelDTO = responsavelService.buscarResponsavelPorId(id);
        return responsavelDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ResponsavelDTO>> listarResponsaveis() {
        List<ResponsavelDTO> responsaveis = responsavelService.listarResponsaveis();
        return ResponseEntity.ok(responsaveis);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsavelDTO> atualizarResponsavel(@PathVariable UUID id, @RequestBody ResponsavelDTO responsavelDTO) {
        ResponsavelDTO atualizado = responsavelService.atualizarResponsavel(id, responsavelDTO);
        return atualizado != null ? ResponseEntity.ok(atualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarResponsavel(@PathVariable UUID id) {
        responsavelService.deletarResponsavel(id);
        return ResponseEntity.noContent().build();
    }
}
