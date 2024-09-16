package com.tskarina.demo_carteira_investimento.web.dto.mapper;

import com.tskarina.demo_carteira_investimento.entity.Carteira;
import com.tskarina.demo_carteira_investimento.web.dto.CarteiraCreateDto;
import com.tskarina.demo_carteira_investimento.web.dto.CarteiraResponseDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CarteiraMapper {

    public static Carteira toCarteira(CarteiraCreateDto createDto) {
        return new ModelMapper().map(createDto, Carteira.class);
    }

    public static CarteiraResponseDto toDto(Carteira carteira) {
        ModelMapper mapper =  new ModelMapper();
        return mapper.map(carteira, CarteiraResponseDto.class);
    }

    public static List<CarteiraResponseDto> toListDto(List<Carteira> carteiras) {
        return carteiras.stream()
                .map(carteira -> toDto(carteira)).collect(Collectors.toList());
    }
}
