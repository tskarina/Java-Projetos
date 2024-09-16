package com.tskarina.demo_carteira_investimento.service;

import com.tskarina.demo_carteira_investimento.entity.Carteira;
import com.tskarina.demo_carteira_investimento.exception.EntityNotFoundException;
import com.tskarina.demo_carteira_investimento.repository.CarteiraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarteiraService {

    private final CarteiraRepository carteiraRepository;

    @Transactional
    public Carteira salvar(Carteira carteira) {
        return carteiraRepository.save(carteira);
    }

    @Transactional(readOnly = true)
    public Carteira buscarPorId(Long id) {
        return carteiraRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Carteira id=%s não encontrada ", id))
        );
    }

    public List<Carteira> buscarTodas() {
        return carteiraRepository.findAll();
    }

    @Transactional
    public Carteira editarDescricaoNome(Long id,  String descricao, String nome) {
        Carteira carteira = buscarPorId(id);
        carteira.setDescricao(descricao);
        carteira.setNome(nome);
        return carteiraRepository.save(carteira);
    }
}
