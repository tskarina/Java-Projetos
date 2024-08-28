package com.kbank.carteira_investimentos.controllers;

import com.kbank.carteira_investimentos.dtos.AtivoDTO;
import com.kbank.carteira_investimentos.services.AtivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ativos")
public class AtivoController {

    @Autowired
    private AtivoService ativoService;

    @GetMapping
    public ResponseEntity<List<AtivoDTO>> getAllAtivos() {
        List<AtivoDTO> ativos = ativoService.getAllAtivos();
        return ResponseEntity.ok(ativos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtivoDTO> getAtivoById(@PathVariable Long id) {
        AtivoDTO ativoDTO = ativoService.getAtivoById(id);
        if (ativoDTO != null) {
            return ResponseEntity.ok(ativoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AtivoDTO> createAtivo(@RequestBody AtivoDTO ativoDTO) {
        AtivoDTO novoAtivo = ativoService.saveAtivo(ativoDTO);
        return ResponseEntity.ok(novoAtivo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtivoDTO> updateAtivo(@PathVariable Long id, @RequestBody AtivoDTO ativoDTO) {
        AtivoDTO ativoExistente = ativoService.getAtivoById(id);
        if (ativoExistente != null) {
            ativoDTO = ativoService.saveAtivo(ativoDTO);
            return ResponseEntity.ok(ativoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAtivo(@PathVariable Long id) {
        AtivoDTO ativoExistente = ativoService.getAtivoById(id);
        if (ativoExistente != null) {
            ativoService.deleteAtivo(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
