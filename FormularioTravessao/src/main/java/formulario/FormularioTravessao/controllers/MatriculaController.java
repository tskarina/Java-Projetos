package formulario.FormularioTravessao.controllers;

import formulario.FormularioTravessao.entities.Matricula;
import formulario.FormularioTravessao.services.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @PostMapping
    public ResponseEntity<Matricula> createMatricula(@RequestBody Matricula matricula) {
        Matricula savedMatricula = matriculaService.createMatricula(matricula);
        return new ResponseEntity<>(savedMatricula, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Matricula>> getAllMatriculas() {
        List<Matricula> matriculas = matriculaService.getAllMatriculas();
        return new ResponseEntity<>(matriculas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> getMatriculaById(@PathVariable Long id) {
        Optional<Matricula> optionalMatricula = matriculaService.getMatriculaById(id);
        return optionalMatricula.map(matricula -> new ResponseEntity<>(matricula, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matricula> updateMatricula(@PathVariable Long id, @RequestBody Matricula matricula) {
        Matricula updatedMatricula = matriculaService.updateMatricula(id, matricula);
        return updatedMatricula != null ? new ResponseEntity<>(updatedMatricula, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatricula(@PathVariable Long id) {
        boolean isDeleted = matriculaService.deleteMatricula(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
