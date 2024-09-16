package com.tskarina.demo_carteira_investimento.repository;

import com.tskarina.demo_carteira_investimento.entity.Acao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AcaoRepository extends JpaRepository<Acao, Long> {
    @Modifying
    @Query(value = "ALTER SEQUENCE acoes_id_seq RESTART WITH 1", nativeQuery = true)
    void reiniciarSequencia();
}
