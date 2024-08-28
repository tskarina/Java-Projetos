package com.kbank.carteira_investimentos.services;

import com.kbank.carteira_investimentos.dtos.TransacaoDTO;
import com.kbank.carteira_investimentos.models.Transacao;
import com.kbank.carteira_investimentos.models.Ativo;
import com.kbank.carteira_investimentos.models.Investimento;
import com.kbank.carteira_investimentos.repositories.TransacaoRepository;
import com.kbank.carteira_investimentos.repositories.AtivoRepository;
import com.kbank.carteira_investimentos.repositories.InvestimentoRepository;
import com.kbank.carteira_investimentos.enums.TipoTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private AtivoRepository ativoRepository;

    @Autowired
    private InvestimentoRepository investimentoRepository;

    public List<TransacaoDTO> findAll() {
        return transacaoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<TransacaoDTO> findById(Long id) {
        return transacaoRepository.findById(id).map(this::convertToDTO);
    }

    public TransacaoDTO save(TransacaoDTO transacaoDTO) {
        Transacao transacao = convertToEntity(transacaoDTO);
        Transacao savedTransacao = transacaoRepository.save(transacao);
        return convertToDTO(savedTransacao);
    }

    public void deleteById(Long id) {
        transacaoRepository.deleteById(id);
    }

    private TransacaoDTO convertToDTO(Transacao transacao) {
        return new TransacaoDTO(
                transacao.getId(),
                transacao.getValor(),
                transacao.getData(),
                transacao.getTipo().name(),
                transacao.getAtivo().getId(),
                transacao.getInvestimento().getId()
        );
    }

    private Transacao convertToEntity(TransacaoDTO transacaoDTO) {
        Transacao transacao = new Transacao();
        transacao.setId(transacaoDTO.id());
        transacao.setValor(transacaoDTO.valor());
        transacao.setData(transacaoDTO.data());
        transacao.setTipo(TipoTransacao.valueOf(transacaoDTO.tipo()));

        Ativo ativo = ativoRepository.findById(transacaoDTO.ativoId())
                .orElseThrow(() -> new IllegalArgumentException("Ativo não encontrado"));
        transacao.setAtivo(ativo);

        Investimento investimento = investimentoRepository.findById(transacaoDTO.investimentoId())
                .orElseThrow(() -> new IllegalArgumentException("Investimento não encontrado"));
        transacao.setInvestimento(investimento);

        return transacao;
    }
}
