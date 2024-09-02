package com.kbank.carteira_investimentos.repositories;

import com.kbank.carteira_investimentos.models.Investimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestimentoRepository extends JpaRepository<Investimento, Long> {
    List<Investimento> findByCarteiraId(Long carteiraId);
}
