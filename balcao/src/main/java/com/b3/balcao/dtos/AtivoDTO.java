package com.b3.balcao.dtos;

import com.b3.balcao.enums.TipoAtivo;

public record AtivoDTO(Long id, String nome, TipoAtivo tipo, double valorAtual) {
}