package com.igor.biblioteca.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tb_exemplares")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExemplarLivro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Enumerated(EnumType.STRING)
    private StatusLivro status;

    @Enumerated(EnumType.STRING)
    private StatusConservacao statusConservacao;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;
} 
