package com.tskarina.demo_carteira_investimento.repository;

import com.tskarina.demo_carteira_investimento.entity.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
}
