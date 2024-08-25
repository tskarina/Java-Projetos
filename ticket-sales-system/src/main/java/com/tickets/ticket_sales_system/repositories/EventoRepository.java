package com.tickets.ticket_sales_system.repositories;

import com.tickets.ticket_sales_system.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}