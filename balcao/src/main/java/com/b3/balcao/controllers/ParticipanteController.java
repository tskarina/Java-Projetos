package com.b3.balcao.controllers;

import com.b3.balcao.dtos.ParticipanteDTO;
import com.b3.balcao.models.Participante;
import com.b3.balcao.services.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/participantes")
public class ParticipanteController {

    @Autowired
    private ParticipanteService participanteService;

    @GetMapping
    public ResponseEntity<List<Participante>> getAllParticipantes() {
        List<Participante> participantes = participanteService.findAll();
        return ResponseEntity.ok(participantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participante> getParticipanteById(@PathVariable Long id) {
        Optional<Participante> participante = participanteService.findById(id);
        return participante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Participante> createParticipante(@RequestBody ParticipanteDTO participanteDTO) {
        Participante novoParticipante = participanteService.save(participanteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoParticipante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipante(@PathVariable Long id) {
        participanteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
