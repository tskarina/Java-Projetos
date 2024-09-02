package com.kbank.carteira_investimentos.repositories;

import com.kbank.carteira_investimentos.models.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByInvestimentoId(Long investimentoId);
}
