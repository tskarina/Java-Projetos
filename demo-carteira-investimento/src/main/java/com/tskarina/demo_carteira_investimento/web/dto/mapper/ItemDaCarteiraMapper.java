package com.tskarina.demo_carteira_investimento.web.dto.mapper;

import com.tskarina.demo_carteira_investimento.entity.Carteira;
import com.tskarina.demo_carteira_investimento.entity.Acao;
import com.tskarina.demo_carteira_investimento.entity.ItemDaCarteira;
import com.tskarina.demo_carteira_investimento.web.dto.CarteiraResponseDto;
import com.tskarina.demo_carteira_investimento.web.dto.ItemDaCarteiraCreateDto;
import com.tskarina.demo_carteira_investimento.web.dto.ItemDaCarteiraResponseDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ItemDaCarteiraMapper {

    public static ItemDaCarteira toItem(ItemDaCarteiraCreateDto createDto) {
        ItemDaCarteira itemDaCarteira = new ItemDaCarteira();
        Carteira carteira = new Carteira();
        carteira.setId(createDto.getCarteiraId());

        Acao acao = new Acao();
        acao.setId(createDto.getAcaoId());

        itemDaCarteira.setCarteira(carteira);
        itemDaCarteira.setAcao(acao);
        itemDaCarteira.setQuantidade(createDto.getQuantidade());
        itemDaCarteira.setValorInvestido(createDto.getValorInvestido());

        return itemDaCarteira;
    }

    public static ItemDaCarteiraResponseDto toDto(ItemDaCarteira itemDaCarteira) {
        ModelMapper mapper = new ModelMapper();
        ItemDaCarteiraResponseDto responseDto = mapper.map(itemDaCarteira, ItemDaCarteiraResponseDto.class);
        responseDto.setCarteiraNome(itemDaCarteira.getCarteira().getNome());
        responseDto.setAcaoNome(itemDaCarteira.getAcao().getNome());
        responseDto.setUsuarioNome(itemDaCarteira.getCarteira().getUsuario().getNome());

        return responseDto;
    }

    public static List<ItemDaCarteiraResponseDto> toListDto(List<ItemDaCarteira> itens) {
        return itens.stream()
                .map(itemCarteira -> toDto(itemCarteira)).collect(Collectors.toList());
    }
}
