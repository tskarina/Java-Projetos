package com.kbank.carteira_investimentos.services;

import com.kbank.carteira_investimentos.dtos.*;
import com.kbank.carteira_investimentos.models.*;
import com.kbank.carteira_investimentos.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CarteiraRepository carteiraRepository;

    @Autowired
    private InvestimentoRepository investimentoRepository;

    @Autowired
    private AtivoRepository ativoRepository;

    public UsuarioDTO create(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.nome());
        usuario.setEmail(usuarioDTO.email());
        usuario.setSenha(usuarioDTO.senha());
        usuario.setDataCriacao(LocalDateTime.now());
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return convertToDTO(savedUsuario);
    }

    public UsuarioDTO update(Long id, UsuarioDTO usuarioDTO) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setNome(usuarioDTO.nome());
            usuario.setEmail(usuarioDTO.email());
            if (usuarioDTO.senha() != null && !usuarioDTO.senha().isEmpty()) {
                usuario.setSenha(usuarioDTO.senha());
            }
            Usuario updatedUsuario = usuarioRepository.save(usuario);
            return convertToDTO(updatedUsuario);
        }
        return null;
    }

    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO findById(Long id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        return optionalUsuario.map(this::convertToDTO).orElse(null);
    }

    public UsuarioDTO findUserWithDetails(Long id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            List<CarteiraDTO> carteirasDTO = carteiraRepository.findByUsuarioId(id).stream()
                    .map(carteira -> {
                        List<InvestimentoDTO> investimentosDTO = investimentoRepository.findByCarteiraId(carteira.getId()).stream()
                                .map(this::convertToDTO)
                                .collect(Collectors.toList());
                        return new CarteiraDTO(carteira.getId(), carteira.getName(), carteira.getUsuario().getId(), carteira.getTipo(), investimentosDTO);
                    })
                    .collect(Collectors.toList());
            return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getDataCriacao(), carteirasDTO);
        }
        return null;
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO convertToDTO(Usuario usuario) {
        List<CarteiraDTO> carteirasDTO = carteiraRepository.findByUsuarioId(usuario.getId()).stream()
                .map(carteira -> {
                    List<InvestimentoDTO> investimentosDTO = investimentoRepository.findByCarteiraId(carteira.getId()).stream()
                            .map(this::convertToDTO)
                            .collect(Collectors.toList());
                    return new CarteiraDTO(carteira.getId(), carteira.getName(), carteira.getUsuario().getId(), carteira.getTipo(), investimentosDTO);
                })
                .collect(Collectors.toList());

        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getDataCriacao(),
                carteirasDTO
        );
    }

    private InvestimentoDTO convertToDTO(Investimento investimento) {
        return new InvestimentoDTO(
                investimento.getId(),
                investimento.getValor(),
                investimento.getQuantidade(),
                investimento.getDataCompra(),
                investimento.getAtivo().getId(),
                investimento.getCarteira().getId()
        );
    }
}
