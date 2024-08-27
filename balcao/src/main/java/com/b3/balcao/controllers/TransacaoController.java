package com.b3.balcao.controllers;

import com.b3.balcao.dtos.TransacaoDTO;
import com.b3.balcao.models.Transacao;
import com.b3.balcao.services.TransacaoService;
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

    @PostMapping
    public ResponseEntity<Transacao> createTransacao(@RequestBody TransacaoDTO transacaoDTO) {
        Transacao novaTransacao = transacaoService.save(transacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTransacao);
    }

    @GetMapping
    public ResponseEntity<List<Transacao>> getAllTransacoes() {
        List<Transacao> transacoes = transacaoService.findAll();
        return ResponseEntity.ok(transacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transacao> getTransacaoById(@PathVariable Long id) {
        Optional<Transacao> transacao = transacaoService.findById(id);
        return transacao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
