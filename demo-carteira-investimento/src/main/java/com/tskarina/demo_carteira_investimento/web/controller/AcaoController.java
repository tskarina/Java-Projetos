package com.tskarina.demo_carteira_investimento.web.controller;

import com.tskarina.demo_carteira_investimento.entity.Acao;
import com.tskarina.demo_carteira_investimento.service.AcaoService;
import com.tskarina.demo_carteira_investimento.web.dto.AcaoCreateDto;
import com.tskarina.demo_carteira_investimento.web.dto.AcaoResponseDto;
import com.tskarina.demo_carteira_investimento.web.dto.mapper.AcaoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/acoes")
public class AcaoController {

    private final AcaoService acaoService;

    @PostMapping
    public ResponseEntity<AcaoResponseDto> create(@RequestBody AcaoCreateDto createDto) {
        Acao acao = acaoService.salvar(AcaoMapper.toAcao(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(AcaoMapper.toDto(acao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcaoResponseDto> getById(@PathVariable Long id) {
        Acao acao = acaoService.buscarPorId(id);
        return ResponseEntity.ok(AcaoMapper.toDto(acao));
    }

    @GetMapping
    public ResponseEntity<List<AcaoResponseDto>> getAll() {
        List<Acao> acoes = acaoService.buscarTodos();
        return ResponseEntity.ok(AcaoMapper.toListDto(acoes));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        acaoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/delete-all")
    public ResponseEntity<Void> deleteAllAcoes() {
        acaoService.deletarTodasAcoes();
        return ResponseEntity.noContent().build();
    }

}
