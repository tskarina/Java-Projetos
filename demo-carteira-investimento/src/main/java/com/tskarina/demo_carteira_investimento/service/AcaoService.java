package com.tskarina.demo_carteira_investimento.service;

import com.tskarina.demo_carteira_investimento.entity.Acao;
import com.tskarina.demo_carteira_investimento.repository.AcaoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class AcaoService {

    private final AcaoRepository acaoRepository;

    @Transactional
    public Acao salvar(Acao acao) {
        return acaoRepository.save(acao);
    }

    @Transactional
    public Acao buscarPorId(Long id) {
        return acaoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Ação id=%s não encontrada", id)));
    }

    @Transactional
    public void deletarPorId(Long id) {
        acaoRepository.deleteById(id);
    }

    @Transactional
    public void deletarTodasAcoes() {
        acaoRepository.deleteAll();
        acaoRepository.reiniciarSequencia();
    }

    public List<Acao> buscarTodos() {
        return acaoRepository.findAll();
    }
}
