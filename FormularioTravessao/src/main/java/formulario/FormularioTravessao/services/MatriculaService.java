package formulario.FormularioTravessao.services;

import formulario.FormularioTravessao.entities.Aluno;
import formulario.FormularioTravessao.entities.Responsavel;
import formulario.FormularioTravessao.entities.Turma;
import formulario.FormularioTravessao.repositories.AlunoRepository;
import formulario.FormularioTravessao.repositories.ResponsavelRepository;
import formulario.FormularioTravessao.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import formulario.FormularioTravessao.entities.Matricula;
import formulario.FormularioTravessao.repositories.MatriculaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    public Matricula createMatricula(Matricula matricula) {
        Optional<Aluno> existingAluno = alunoRepository.findById(matricula.getAluno().getId());
        existingAluno.ifPresent(matricula::setAluno);

        Optional<Responsavel> existingResponsavel = responsavelRepository.findById(matricula.getResponsavel().getId());
        existingResponsavel.ifPresent(matricula::setResponsavel);

        Optional<Turma> existingTurma = turmaRepository.findById(matricula.getTurma().getId());
        existingTurma.ifPresent(matricula::setTurma);

        return matriculaRepository.save(matricula);
    }

    public List<Matricula> getAllMatriculas() {
        return matriculaRepository.findAll();
    }

    public Optional<Matricula> getMatriculaById(Long id) {
        return matriculaRepository.findById(id);
    }

    public Matricula updateMatricula(Long id, Matricula matricula) {
        if (matriculaRepository.existsById(id)) {
            matricula.setId(id);
            return matriculaRepository.save(matricula);
        }
        return null;
    }

    public boolean deleteMatricula(Long id) {
        if (matriculaRepository.existsById(id)) {
            matriculaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
