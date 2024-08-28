package com.kbank.carteira_investimentos.repositories;

import com.kbank.carteira_investimentos.models.Ativo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AtivoRepository extends JpaRepository<Ativo, Long> {
}