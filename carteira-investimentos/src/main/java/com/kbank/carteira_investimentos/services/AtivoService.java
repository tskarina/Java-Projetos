package com.kbank.carteira_investimentos.services;

import com.kbank.carteira_investimentos.dtos.AtivoDTO;
import com.kbank.carteira_investimentos.models.Ativo;
import com.kbank.carteira_investimentos.repositories.AtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtivoService {

    @Autowired
    private AtivoRepository ativoRepository;

    public List<AtivoDTO> getAllAtivos() {
        return ativoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AtivoDTO getAtivoById(Long id) {
        return ativoRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public AtivoDTO saveAtivo(AtivoDTO ativoDTO) {
        Ativo ativo = convertToEntity(ativoDTO);
        ativo = ativoRepository.save(ativo);
        return convertToDTO(ativo);
    }

    public void deleteAtivo(Long id) {
        ativoRepository.deleteById(id);
    }

    private AtivoDTO convertToDTO(Ativo ativo) {
        return new AtivoDTO(ativo.getId(), ativo.getNome(), ativo.getTipo());
    }

    private Ativo convertToEntity(AtivoDTO dto) {
        Ativo ativo = new Ativo();
        ativo.setId(dto.id());
        ativo.setNome(dto.nome());
        ativo.setTipo(dto.tipo());
        return ativo;
    }
}
