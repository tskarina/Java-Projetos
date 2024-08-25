package com.tickets.ticket_sales_system.controllers;

import com.tickets.ticket_sales_system.models.Ingresso;
import com.tickets.ticket_sales_system.services.IngressoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingressos")
public class IngressoController {

    @Autowired
    private IngressoService ingressoService;

    @GetMapping
    public List<Ingresso> getAllIngressos() {
        return ingressoService.getAllIngressos();
    }

    @GetMapping("/{id}")
    public Ingresso getIngressoById(@PathVariable Long id) {
        return ingressoService.getIngressoById(id);
    }

    @PostMapping
    public Ingresso createIngresso(@RequestBody Ingresso ingresso) {
        return ingressoService.saveIngresso(ingresso);
    }

    @PutMapping("/{id}")
    public Ingresso updateIngresso(@PathVariable Long id, @RequestBody Ingresso ingresso) {
        ingresso.setId(id);
        return ingressoService.saveIngresso(ingresso);
    }

    @DeleteMapping("/{id}")
    public void deleteIngresso(@PathVariable Long id) {
        ingressoService.deleteIngresso(id);
    }
}