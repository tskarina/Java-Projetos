package com.tskarina.demo_carteira_investimento.web.dto.mapper;

import com.tskarina.demo_carteira_investimento.entity.Usuario;
import com.tskarina.demo_carteira_investimento.web.dto.UsuarioCreateDto;
import com.tskarina.demo_carteira_investimento.web.dto.UsuarioResponseDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static Usuario toUsuario(UsuarioCreateDto createDto) {
        return new ModelMapper().map(createDto, Usuario.class);
    }

    public static UsuarioResponseDto toDto(Usuario usuario) {
       ModelMapper mapper =  new ModelMapper();
        return mapper.map(usuario, UsuarioResponseDto.class);
    }

    public static List<UsuarioResponseDto> toListDto(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(user -> toDto(user)).collect(Collectors.toList());
    }
}
