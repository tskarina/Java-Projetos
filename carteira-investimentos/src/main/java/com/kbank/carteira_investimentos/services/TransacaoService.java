package com.kbank.carteira_investimentos.services;

import com.kbank.carteira_investimentos.dtos.TransacaoDTO;
import com.kbank.carteira_investimentos.models.Transacao;
import com.kbank.carteira_investimentos.models.Investimento;
import com.kbank.carteira_investimentos.repositories.TransacaoRepository;
import com.kbank.carteira_investimentos.repositories.InvestimentoRepository;
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
        if (transacao == null) {
            throw new IllegalArgumentException("Transação não pode ser nula");
        }
        return new TransacaoDTO(
                transacao.getId(),
                transacao.getValor(),
                transacao.getData(),
                transacao.getTipo(),
                transacao.getInvestimento() != null ? transacao.getInvestimento().getId() : null
        );
    }

    private Transacao convertToEntity(TransacaoDTO transacaoDTO) {
        if (transacaoDTO == null) {
            throw new IllegalArgumentException("TransacaoDTO não pode ser nulo");
        }

        Transacao transacao = new Transacao();
        transacao.setId(transacaoDTO.id());
        transacao.setValor(transacaoDTO.valor());
        transacao.setData(transacaoDTO.data());
        transacao.setTipo(transacaoDTO.tipo());

        Investimento investimento = investimentoRepository.findById(transacaoDTO.investimentoId())
                .orElseThrow(() -> new IllegalArgumentException("Investimento não encontrado com id: " + transacaoDTO.investimentoId()));
        transacao.setInvestimento(investimento);

        return transacao;
    }
}
