package com.kbank.carteira_investimentos.repositories;

import com.kbank.carteira_investimentos.models.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
    List<Carteira> findByUsuarioId(Long usuarioId);
}
