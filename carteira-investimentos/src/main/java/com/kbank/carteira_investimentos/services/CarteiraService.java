package com.kbank.carteira_investimentos.services;

import com.kbank.carteira_investimentos.dtos.CarteiraDTO;
import com.kbank.carteira_investimentos.dtos.InvestimentoDTO;
import com.kbank.carteira_investimentos.models.Carteira;
import com.kbank.carteira_investimentos.models.Investimento;
import com.kbank.carteira_investimentos.models.Usuario;
import com.kbank.carteira_investimentos.repositories.CarteiraRepository;
import com.kbank.carteira_investimentos.repositories.InvestimentoRepository;
import com.kbank.carteira_investimentos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarteiraService {

    @Autowired
    private CarteiraRepository carteiraRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private InvestimentoRepository investimentoRepository;

    public List<CarteiraDTO> findAll() {
        return carteiraRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CarteiraDTO findById(Long id) {
        Optional<Carteira> carteira = carteiraRepository.findById(id);
        return carteira.map(this::convertToDTO).orElse(null);
    }

    public CarteiraDTO create(CarteiraDTO carteiraDTO) {
        Optional<Usuario> usuario = usuarioRepository.findById(carteiraDTO.usuarioId());
        if (usuario.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        Carteira carteira = new Carteira();
        carteira.setName(carteiraDTO.name());
        carteira.setTipo(carteiraDTO.tipo());
        carteira.setUsuario(usuario.get());
        carteira = carteiraRepository.save(carteira);
        return convertToDTO(carteira);
    }

    public CarteiraDTO update(Long id, CarteiraDTO carteiraDTO) {
        Optional<Carteira> optionalCarteira = carteiraRepository.findById(id);
        if (optionalCarteira.isPresent()) {
            Carteira carteira = optionalCarteira.get();
            Optional<Usuario> usuario = usuarioRepository.findById(carteiraDTO.usuarioId());
            if (usuario.isEmpty()) {
                throw new IllegalArgumentException("Usuário não encontrado");
            }
            carteira.setName(carteiraDTO.name());
            carteira.setTipo(carteiraDTO.tipo());
            carteira.setUsuario(usuario.get());
            carteira = carteiraRepository.save(carteira);
            return convertToDTO(carteira);
        }
        return null;
    }

    public void delete(Long id) {
        carteiraRepository.deleteById(id);
    }

    private CarteiraDTO convertToDTO(Carteira carteira) {
        List<InvestimentoDTO> investimentosDTO = investimentoRepository.findByCarteiraId(carteira.getId()).stream()
                .map(investimento -> new InvestimentoDTO(
                        investimento.getId(),
                        investimento.getValor(),
                        investimento.getQuantidade(),
                        investimento.getDataCompra(),
                        investimento.getAtivo().getId(),
                        investimento.getCarteira().getId()
                ))
                .collect(Collectors.toList());

        return new CarteiraDTO(
                carteira.getId(),
                carteira.getName(),
                carteira.getUsuario().getId(),
                carteira.getTipo(),
                investimentosDTO
        );
    }
}
