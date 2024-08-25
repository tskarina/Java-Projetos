package com.tickets.ticket_sales_system.repositories;

import com.tickets.ticket_sales_system.models.Ingresso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngressoRepository extends JpaRepository<Ingresso, Long> {
}