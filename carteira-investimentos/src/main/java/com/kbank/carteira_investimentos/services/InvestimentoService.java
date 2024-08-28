package com.kbank.carteira_investimentos.services;

import com.kbank.carteira_investimentos.dtos.InvestimentoDTO;
import com.kbank.carteira_investimentos.models.Ativo;
import com.kbank.carteira_investimentos.models.Carteira;
import com.kbank.carteira_investimentos.models.Investimento;
import com.kbank.carteira_investimentos.repositories.AtivoRepository;
import com.kbank.carteira_investimentos.repositories.CarteiraRepository;
import com.kbank.carteira_investimentos.repositories.InvestimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvestimentoService {

    @Autowired
    private InvestimentoRepository investimentoRepository;

    @Autowired
    private AtivoRepository ativoRepository;

    @Autowired
    private CarteiraRepository carteiraRepository;

    public List<InvestimentoDTO> findAll() {
        return investimentoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public InvestimentoDTO findById(Long id) {
        Optional<Investimento> investimento = investimentoRepository.findById(id);
        return investimento.map(this::convertToDTO).orElse(null);
    }

    public InvestimentoDTO create(InvestimentoDTO investimentoDTO) {
        Optional<Ativo> ativo = ativoRepository.findById(investimentoDTO.ativoId());
        Optional<Carteira> carteira = carteiraRepository.findById(investimentoDTO.carteiraId());

        if (ativo.isEmpty() || carteira.isEmpty()) {
            throw new IllegalArgumentException("Ativo ou Carteira não encontrado");
        }

        Investimento investimento = new Investimento();
        investimento.setValor(investimentoDTO.valor());
        investimento.setQuantidade(investimentoDTO.quantidade());
        investimento.setDataCompra(investimentoDTO.dataCompra());
        investimento.setAtivo(ativo.get());
        investimento.setCarteira(carteira.get());

        investimento = investimentoRepository.save(investimento);
        return convertToDTO(investimento);
    }

    private InvestimentoDTO convertToDTO(Investimento investimento) {
        return new InvestimentoDTO(
                investimento.getId(),
                investimento.getValor(),
                investimento.getQuantidade(),
                investimento.getDataCompra(),
                investimento.getAtivo().getId(),
                investimento.getCarteira().getId()
        );
    }
}
