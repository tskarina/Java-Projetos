package com.TravessaoBackend.Formulario.repositories;

import com.TravessaoBackend.Formulario.models.MatriculaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MatriculaRepository extends JpaRepository<MatriculaModel, UUID> {
}
