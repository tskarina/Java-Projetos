package com.b3.balcao.services;

import com.b3.balcao.dtos.ParticipanteDTO;
import com.b3.balcao.models.Participante;
import com.b3.balcao.repositories.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipanteService {

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Transactional(readOnly = true)
    public List<Participante> findAll() {
        return participanteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Participante> findById(Long id) {
        return participanteRepository.findById(id);
    }

    public Participante save(ParticipanteDTO participanteDTO) {
        Participante participante = new Participante();
        participante.setId(participanteDTO.id());
        participante.setNome(participanteDTO.nome());
        participante.setTipo(participanteDTO.tipo());
        participante.setCpf(participanteDTO.cpf());
        participante.setEmail(participanteDTO.email());
        return participanteRepository.save(participante);
    }

    public void deleteById(Long id) {
        participanteRepository.deleteById(id);
    }
}
