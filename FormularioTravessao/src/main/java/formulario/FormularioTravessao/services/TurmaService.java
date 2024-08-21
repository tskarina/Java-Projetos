package formulario.FormularioTravessao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import formulario.FormularioTravessao.entities.Turma;
import formulario.FormularioTravessao.repositories.TurmaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    public Turma createTurma(Turma turma) {
        return turmaRepository.save(turma);
    }

    public List<Turma> getAllTurmas() {
        return turmaRepository.findAll();
    }

    public Optional<Turma> getTurmaById(Long id) {
        return turmaRepository.findById(id);
    }

    public Turma updateTurma(Long id, Turma turma) {
        if (turmaRepository.existsById(id)) {
            turma.setId(id);
            return turmaRepository.save(turma);
        }
        return null;
    }

    public void deleteTurma(Long id) {
        turmaRepository.deleteById(id);
    }
}
