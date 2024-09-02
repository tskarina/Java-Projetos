package com.kbank.carteira_investimentos.repositories;

import com.kbank.carteira_investimentos.models.Ativo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AtivoRepository extends JpaRepository<Ativo, Long> {
}