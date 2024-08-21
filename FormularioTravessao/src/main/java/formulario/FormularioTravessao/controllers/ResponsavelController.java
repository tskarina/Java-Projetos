package formulario.FormularioTravessao.controllers;

import formulario.FormularioTravessao.entities.Responsavel;
import formulario.FormularioTravessao.services.ResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/responsaveis")
public class ResponsavelController {

    @Autowired
    private ResponsavelService responsavelService;

    @PostMapping
    public ResponseEntity<Responsavel> createResponsavel(@RequestBody Responsavel responsavel) {
        Responsavel savedResponsavel = responsavelService.createResponsavel(responsavel);
        return new ResponseEntity<>(savedResponsavel, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Responsavel>> getAllResponsaveis() {
        List<Responsavel> responsaveis = responsavelService.getAllResponsaveis();
        return new ResponseEntity<>(responsaveis, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Responsavel> getResponsavelById(@PathVariable Long id) {
        return responsavelService.getResponsavelById(id)
                .map(responsavel -> new ResponseEntity<>(responsavel, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Responsavel> updateResponsavel(@PathVariable Long id, @RequestBody Responsavel responsavel) {
        Responsavel updatedResponsavel = responsavelService.updateResponsavel(id, responsavel);
        return updatedResponsavel != null ? new ResponseEntity<>(updatedResponsavel, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponsavel(@PathVariable Long id) {
        responsavelService.deleteResponsavel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
