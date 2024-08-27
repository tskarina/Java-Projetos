package com.b3.balcao.controllers;

import com.b3.balcao.dtos.AtivoDTO;
import com.b3.balcao.models.Ativo;
import com.b3.balcao.services.AtivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ativos")
public class AtivoController {

    @Autowired
    private AtivoService ativoService;

    @GetMapping
    public ResponseEntity<List<Ativo>> getAllAtivos() {
        List<Ativo> ativos = ativoService.findAll();
        return ResponseEntity.ok(ativos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ativo> getAtivoById(@PathVariable Long id) {
        Optional<Ativo> ativo = ativoService.findById(id);
        return ativo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Ativo> createAtivo(@RequestBody AtivoDTO ativoDTO) {
        Ativo novoAtivo = ativoService.save(ativoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAtivo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAtivo(@PathVariable Long id) {
        ativoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
