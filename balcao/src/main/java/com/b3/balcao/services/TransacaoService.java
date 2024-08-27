package com.b3.balcao.services;

import com.b3.balcao.dtos.TransacaoDTO;
import com.b3.balcao.models.Ativo;
import com.b3.balcao.models.Participante;
import com.b3.balcao.models.Transacao;
import com.b3.balcao.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private AtivoService ativoService;

    @Autowired
    private ParticipanteService participanteService;

    public Transacao save(TransacaoDTO transacaoDTO) {
        Transacao transacao = new Transacao();
        transacao.setId(transacaoDTO.id());
        transacao.setValor(transacaoDTO.valor());
        transacao.setQuantidade(transacaoDTO.quantidade());
        transacao.setDataHora(transacaoDTO.dataHora());
        transacao.setTipo(transacaoDTO.tipo());

        Ativo ativo = ativoService.findById(transacaoDTO.ativo().id())
                .orElseThrow(() -> new RuntimeException("Ativo não encontrado"));
        transacao.setAtivo(ativo);

        Participante participante = participanteService.findById(transacaoDTO.participante().id())
                .orElseThrow(() -> new RuntimeException("Participante não encontrado"));
        transacao.setParticipante(participante);

        return transacaoRepository.save(transacao);
    }

    public List<Transacao> findAll() {
        return transacaoRepository.findAll();
    }

    public Optional<Transacao> findById(Long id) {
        return transacaoRepository.findById(id);
    }
}
