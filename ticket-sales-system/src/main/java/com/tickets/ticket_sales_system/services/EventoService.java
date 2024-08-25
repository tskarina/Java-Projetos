package com.tickets.ticket_sales_system.services;


import com.tickets.ticket_sales_system.models.Evento;
import com.tickets.ticket_sales_system.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public Evento getEventoById(Long id) {
        return eventoRepository.findById(id).orElse(null);
    }

    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    public Evento saveEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public void deleteEvento(Long id) {
        eventoRepository.deleteById(id);
    }
}