package com.kbank.carteira_investimentos.controllers;

import com.kbank.carteira_investimentos.dtos.TransacaoDTO;
import com.kbank.carteira_investimentos.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping
    public ResponseEntity<List<TransacaoDTO>> getAllTransacoes() {
        List<TransacaoDTO> transacoes = transacaoService.findAll();
        return ResponseEntity.ok(transacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoDTO> getTransacaoById(@PathVariable Long id) {
        Optional<TransacaoDTO> transacao = transacaoService.findById(id);
        return transacao.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TransacaoDTO> createTransacao(@RequestBody TransacaoDTO transacaoDTO) {
        TransacaoDTO createdTransacao = transacaoService.save(transacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTransacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransacao(@PathVariable Long id) {
        transacaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
