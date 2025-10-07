package com.igor.biblioteca.repository;

import com.igor.biblioteca.model.UsuarioBiblioteca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioBiblioteca, UUID> { }