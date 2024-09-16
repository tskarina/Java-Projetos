package com.tskarina.demo_carteira_investimento.service;

import com.tskarina.demo_carteira_investimento.entity.ItemDaCarteira;
import com.tskarina.demo_carteira_investimento.entity.Carteira;
import com.tskarina.demo_carteira_investimento.entity.Acao;
import com.tskarina.demo_carteira_investimento.exception.EntityNotFoundException;
import com.tskarina.demo_carteira_investimento.repository.AcaoRepository;
import com.tskarina.demo_carteira_investimento.repository.CarteiraRepository;
import com.tskarina.demo_carteira_investimento.repository.ItemDaCarteiraRepository;
import com.tskarina.demo_carteira_investimento.web.dto.ItemDaCarteiraResponseDto;
import com.tskarina.demo_carteira_investimento.web.dto.mapper.ItemDaCarteiraMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemDaCarteiraService {

    private final ItemDaCarteiraRepository itemRepository;
    private final CarteiraRepository carteiraRepository;
    private final AcaoRepository acaoRepository;

    @Transactional
    public ItemDaCarteiraResponseDto salvar(ItemDaCarteira item) {
        Carteira carteira = carteiraRepository.findById(item.getCarteira().getId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("Carteira id=%s não encontrada", item.getCarteira().getId())));

        Acao acao = acaoRepository.findById(item.getAcao().getId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("Ação id=%s não encontrada", item.getAcao().getId())));

        item.setCarteira(carteira);
        item.setAcao(acao);

        ItemDaCarteira itemSalvo = itemRepository.save(item);

        ItemDaCarteiraResponseDto responseDto = ItemDaCarteiraMapper.toDto(itemSalvo);
        responseDto.setCarteiraNome(carteira.getNome());
        responseDto.setAcaoNome(acao.getNome());

        return responseDto;
    }

    @Transactional(readOnly = true)
    public ItemDaCarteiraResponseDto buscarPorId(Long id) {
        ItemDaCarteira item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Item da Carteira id=%s não encontrado", id)));

        ItemDaCarteiraResponseDto responseDto = ItemDaCarteiraMapper.toDto(item);
        responseDto.setCarteiraNome(item.getCarteira().getNome());
        responseDto.setAcaoNome(item.getAcao().getNome());

        return responseDto;
    }

    @Transactional(readOnly = true)
    public List<ItemDaCarteira> buscarTodos() {
        return itemRepository.findAll();
    }

    @Transactional
    public void deletarPorId(Long id) {
        itemRepository.deleteById(id);
    }
}
