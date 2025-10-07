package com.igor.biblioteca.repository;

import com.igor.biblioteca.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> { }