package com.tskarina.demo_carteira_investimento.web.controller;

import com.tskarina.demo_carteira_investimento.entity.Carteira;
import com.tskarina.demo_carteira_investimento.service.CarteiraService;
import com.tskarina.demo_carteira_investimento.web.dto.CarteiraCreateDto;
import com.tskarina.demo_carteira_investimento.web.dto.CarteiraDescricaoNomeDto;
import com.tskarina.demo_carteira_investimento.web.dto.mapper.CarteiraMapper;
import com.tskarina.demo_carteira_investimento.web.dto.CarteiraResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/carteiras")
public class CarteiraController {

    private final CarteiraService carteiraService;

    @PostMapping
    public ResponseEntity<CarteiraResponseDto> create(@Valid @RequestBody CarteiraCreateDto createDto) {
        Carteira carteira = carteiraService.salvar(CarteiraMapper.toCarteira(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(CarteiraMapper.toDto(carteira));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarteiraResponseDto> getById(@PathVariable Long id) {
       Carteira carteira = carteiraService.buscarPorId(id);
        return ResponseEntity.ok(CarteiraMapper.toDto(carteira));
    }

    @GetMapping
    public ResponseEntity <List<CarteiraResponseDto>> getAll() {
        List <Carteira> carteiras = carteiraService.buscarTodas();
        return ResponseEntity.ok(CarteiraMapper.toListDto(carteiras));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CarteiraResponseDto> updateNameAndDescription(@PathVariable Long id, @Valid @RequestBody CarteiraDescricaoNomeDto dto) {
        Carteira carteira = carteiraService.editarDescricaoNome(id, dto.getDescricao(), dto.getNome());
        return ResponseEntity.status(HttpStatus.OK).body(CarteiraMapper.toDto(carteira));
    }
}
