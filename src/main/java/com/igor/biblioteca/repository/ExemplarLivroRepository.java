package com.igor.biblioteca.repository;

import com.igor.biblioteca.model.ExemplarLivro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ExemplarLivroRepository extends JpaRepository<ExemplarLivro, UUID> {

    @Query(value = "SELECT COUNT(*) FROM tb_exemplares e WHERE e.livro_id = :livroId", nativeQuery = true)
    long countByLivroId(@Param("livroId") UUID livroId);

    @Query(value = "SELECT COUNT(*) FROM tb_exemplares e WHERE e.livro_id = :livroId AND e.status = :status", nativeQuery = true)
    long countByLivroIdAndStatus(@Param("livroId") UUID livroId, @Param("status") String status);
}
