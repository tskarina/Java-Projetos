package formulario.FormularioTravessao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import formulario.FormularioTravessao.entities.Responsavel;
import formulario.FormularioTravessao.repositories.ResponsavelRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ResponsavelService {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    public Responsavel createResponsavel(Responsavel responsavel) {
        return responsavelRepository.save(responsavel);
    }

    public List<Responsavel> getAllResponsaveis() {
        return responsavelRepository.findAll();
    }

    public Optional<Responsavel> getResponsavelById(Long id) {
        return responsavelRepository.findById(id);
    }

    public Responsavel updateResponsavel(Long id, Responsavel responsavel) {
        if (responsavelRepository.existsById(id)) {
            responsavel.setId(id);
            return responsavelRepository.save(responsavel);
        }
        return null;
    }

    public void deleteResponsavel(Long id) {
        responsavelRepository.deleteById(id);
    }
}
