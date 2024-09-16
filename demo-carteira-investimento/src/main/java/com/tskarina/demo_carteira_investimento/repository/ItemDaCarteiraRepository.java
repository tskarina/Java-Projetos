package com.tskarina.demo_carteira_investimento.repository;

import com.tskarina.demo_carteira_investimento.entity.ItemDaCarteira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDaCarteiraRepository extends JpaRepository<ItemDaCarteira, Long> {
}
