package com.b3.balcao.repositories;

import com.b3.balcao.models.Ativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtivoRepository extends JpaRepository<Ativo, Long> {
}
