package com.tickets.ticket_sales_system.services;


import com.tickets.ticket_sales_system.models.Ingresso;
import com.tickets.ticket_sales_system.repositories.IngressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngressoService {

    @Autowired
    private IngressoRepository ingressoRepository;

    public Ingresso getIngressoById(Long id) {
        return ingressoRepository.findById(id).orElse(null);
    }

    public List<Ingresso> getAllIngressos() {
        return ingressoRepository.findAll();
    }

    public Ingresso saveIngresso(Ingresso ingresso) {
        return ingressoRepository.save(ingresso);
    }

    public void deleteIngresso(Long id) {
        ingressoRepository.deleteById(id);
    }
}