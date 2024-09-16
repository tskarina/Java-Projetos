package com.tskarina.demo_carteira_investimento.web.controller;

import com.tskarina.demo_carteira_investimento.entity.ItemDaCarteira;
import com.tskarina.demo_carteira_investimento.service.ItemDaCarteiraService;
import com.tskarina.demo_carteira_investimento.web.dto.ItemDaCarteiraCreateDto;
import com.tskarina.demo_carteira_investimento.web.dto.ItemDaCarteiraResponseDto;
import com.tskarina.demo_carteira_investimento.web.dto.mapper.ItemDaCarteiraMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/itens-da-carteira")
public class ItemDaCarteiraController {

    private final ItemDaCarteiraService itemDaCarteiraService;

    @PostMapping
    public ResponseEntity<ItemDaCarteiraResponseDto> create(@Valid @RequestBody ItemDaCarteiraCreateDto createDto) {
        ItemDaCarteira item = ItemDaCarteiraMapper.toItem(createDto);
        ItemDaCarteiraResponseDto responseDto = itemDaCarteiraService.salvar(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDaCarteiraResponseDto> getById(@PathVariable Long id) {
        ItemDaCarteiraResponseDto responseDto = itemDaCarteiraService.buscarPorId(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<ItemDaCarteiraResponseDto>> getAll() {
        List <ItemDaCarteira> itemCarteira = itemDaCarteiraService.buscarTodos();
        return ResponseEntity.ok(ItemDaCarteiraMapper.toListDto(itemCarteira));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        itemDaCarteiraService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
