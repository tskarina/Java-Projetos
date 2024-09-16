package com.tskarina.demo_carteira_investimento.repository;

import com.tskarina.demo_carteira_investimento.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
