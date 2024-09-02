package com.kbank.carteira_investimentos.services;

import com.kbank.carteira_investimentos.dtos.AtivoDTO;
import com.kbank.carteira_investimentos.dtos.InvestimentoDTO;
import com.kbank.carteira_investimentos.dtos.TransacaoDTO; // Importar DTO de transações
import com.kbank.carteira_investimentos.models.Ativo;
import com.kbank.carteira_investimentos.models.Carteira;
import com.kbank.carteira_investimentos.models.Investimento;
import com.kbank.carteira_investimentos.models.Transacao; // Importar modelo de transações
import com.kbank.carteira_investimentos.repositories.AtivoRepository;
import com.kbank.carteira_investimentos.repositories.CarteiraRepository;
import com.kbank.carteira_investimentos.repositories.InvestimentoRepository;
import com.kbank.carteira_investimentos.repositories.TransacaoRepository; // Importar repositório de transações
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

    @Autowired
    private TransacaoRepository transacaoRepository;

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
        Optional<Ativo> ativo = ativoRepository.findById(investimentoDTO.ativo().id());
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
        List<Transacao> transacoes = transacaoRepository.findByInvestimentoId(investimento.getId());
        List<TransacaoDTO> transacoesDTO = transacoes.stream()
                .map(transacao -> new TransacaoDTO(
                        transacao.getId(),
                        transacao.getValor(),
                        transacao.getData(),
                        transacao.getTipo(),
                        transacao.getInvestimento().getId()
                ))
                .collect(Collectors.toList());

        return new InvestimentoDTO(
                investimento.getId(),
                investimento.getValor(),
                investimento.getQuantidade(),
                investimento.getDataCompra(),
                new AtivoDTO(
                        investimento.getAtivo().getId(),
                        investimento.getAtivo().getNome(),
                        investimento.getAtivo().getTipo()
                ),
                investimento.getCarteira().getId(),
                transacoesDTO
        );
    }
}
