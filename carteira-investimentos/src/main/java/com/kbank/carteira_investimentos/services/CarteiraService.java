package com.kbank.carteira_investimentos.services;

import com.kbank.carteira_investimentos.dtos.AtivoDTO;
import com.kbank.carteira_investimentos.dtos.CarteiraDTO;
import com.kbank.carteira_investimentos.dtos.InvestimentoDTO;
import com.kbank.carteira_investimentos.models.Ativo;
import com.kbank.carteira_investimentos.models.Carteira;
import com.kbank.carteira_investimentos.models.Investimento;
import com.kbank.carteira_investimentos.models.Usuario;
import com.kbank.carteira_investimentos.repositories.AtivoRepository;
import com.kbank.carteira_investimentos.repositories.CarteiraRepository;
import com.kbank.carteira_investimentos.repositories.InvestimentoRepository;
import com.kbank.carteira_investimentos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @Autowired
    private AtivoRepository ativoRepository;

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

    public void adicionarAtivos(Long carteiraId, List<Long> ativosIds) {
        Optional<Carteira> optionalCarteira = carteiraRepository.findById(carteiraId);

        if (optionalCarteira.isPresent()) {
            Carteira carteira = optionalCarteira.get();

            for (Long ativoId : ativosIds) {
                Optional<Ativo> optionalAtivo = ativoRepository.findById(ativoId);
                if (optionalAtivo.isPresent()) {
                    Ativo ativo = optionalAtivo.get();
                    Investimento investimento = new Investimento();
                    investimento.setAtivo(ativo);
                    investimento.setCarteira(carteira);
                    investimento.setValor(BigDecimal.ZERO);
                    investimento.setQuantidade(BigDecimal.ZERO);
                    investimento.setDataCompra(LocalDateTime.now());

                    investimentoRepository.save(investimento);
                }
            }
        } else {
            throw new IllegalArgumentException("Carteira não encontrada");
        }
    }

    private CarteiraDTO convertToDTO(Carteira carteira) {
        List<InvestimentoDTO> investimentosDTO = investimentoRepository.findByCarteiraId(carteira.getId()).stream()
                .map(investimento -> {
                    Ativo ativo = investimento.getAtivo();
                    if (ativo == null) {
                        throw new IllegalStateException("Ativo não encontrado para o investimento com ID: " + investimento.getId());
                    }
                    return new InvestimentoDTO(
                            investimento.getId(),
                            investimento.getValor(),
                            investimento.getQuantidade(),
                            investimento.getDataCompra(),
                            new AtivoDTO(
                                    ativo.getId(),
                                    ativo.getNome(),
                                    ativo.getTipo()
                            ),
                            investimento.getCarteira().getId(),
                            List.of()
                    );
                })
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
