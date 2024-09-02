package com.kbank.carteira_investimentos.controllers;

import com.kbank.carteira_investimentos.dtos.CarteiraDTO;
import com.kbank.carteira_investimentos.services.CarteiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carteiras")
public class CarteiraController {

    @Autowired
    private CarteiraService carteiraService;

    @GetMapping
    public ResponseEntity<List<CarteiraDTO>> getAllCarteiras() {
        List<CarteiraDTO> carteiras = carteiraService.findAll();
        return ResponseEntity.ok(carteiras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarteiraDTO> getCarteiraById(@PathVariable Long id) {
        CarteiraDTO carteira = carteiraService.findById(id);
        if (carteira != null) {
            return ResponseEntity.ok(carteira);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CarteiraDTO> createCarteira(@RequestBody CarteiraDTO carteiraDTO) {
        CarteiraDTO createdCarteira = carteiraService.create(carteiraDTO);
        return ResponseEntity.ok(createdCarteira);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarteira(@PathVariable Long id) {
        carteiraService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
