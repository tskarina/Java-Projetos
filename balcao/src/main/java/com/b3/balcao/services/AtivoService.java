package com.b3.balcao.services;

import com.b3.balcao.dtos.AtivoDTO;
import com.b3.balcao.models.Ativo;
import com.b3.balcao.repositories.AtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtivoService {

    @Autowired
    private AtivoRepository ativoRepository;

    public List<Ativo> findAll() {
        return ativoRepository.findAll();
    }

    public Optional<Ativo> findById(Long id) {
        return ativoRepository.findById(id);
    }

    public Ativo save(AtivoDTO ativoDTO) {
        Ativo ativo = new Ativo();
        ativo.setId(ativoDTO.id());
        ativo.setNome(ativoDTO.nome());
        ativo.setTipo(ativoDTO.tipo());
        ativo.setValorAtual(ativoDTO.valorAtual());
        return ativoRepository.save(ativo);
    }

    public void deleteById(Long id) {
        ativoRepository.deleteById(id);
    }
}
