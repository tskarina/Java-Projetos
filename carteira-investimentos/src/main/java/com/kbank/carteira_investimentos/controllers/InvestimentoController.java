package com.kbank.carteira_investimentos.controllers;

import com.kbank.carteira_investimentos.dtos.InvestimentoDTO;
import com.kbank.carteira_investimentos.services.InvestimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/investimentos")
public class InvestimentoController {

    @Autowired
    private InvestimentoService investimentoService;

    @GetMapping
    public ResponseEntity<List<InvestimentoDTO>> getAllInvestimentos() {
        List<InvestimentoDTO> investimentos = investimentoService.findAll();
        return ResponseEntity.ok(investimentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestimentoDTO> getInvestimentoById(@PathVariable Long id) {
        InvestimentoDTO investimento = investimentoService.findById(id);
        if (investimento != null) {
            return ResponseEntity.ok(investimento);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<InvestimentoDTO> createInvestimento(@RequestBody InvestimentoDTO investimentoDTO) {
        try {
            InvestimentoDTO novoInvestimento = investimentoService.create(investimentoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoInvestimento);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
