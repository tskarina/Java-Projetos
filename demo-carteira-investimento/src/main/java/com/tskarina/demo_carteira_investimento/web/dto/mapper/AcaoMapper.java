package com.tskarina.demo_carteira_investimento.web.dto.mapper;

import com.tskarina.demo_carteira_investimento.entity.Acao;
import com.tskarina.demo_carteira_investimento.web.dto.AcaoCreateDto;
import com.tskarina.demo_carteira_investimento.web.dto.AcaoResponseDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class AcaoMapper {

    public static Acao toAcao(AcaoCreateDto createDto) {
        return new ModelMapper().map(createDto, Acao.class);
    }

    public static AcaoResponseDto toDto(Acao acao) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(acao, AcaoResponseDto.class);
    }

    public static List<AcaoResponseDto> toListDto(List<Acao> acoes) {
        return acoes.stream()
                .map(acao -> toDto(acao)).collect(Collectors.toList());
    }
}
