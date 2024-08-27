package com.b3.balcao.repositories;

import com.b3.balcao.models.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}