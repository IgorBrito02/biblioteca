package com.igor.biblioteca.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_emprestimos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime dataHoraEmprestimo;

    private LocalDateTime dataHoraDevolucao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEmprestimo status; 

    // Um empréstimo pode ter vários exemplares
    @OneToMany
    @JoinColumn(name = "emprestimo_id")
    @Builder.Default
    private Set<ExemplarLivro> exemplares = new HashSet<>();

    // Muitos empréstimos podem ser feitos por 1 usuário
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioBiblioteca usuario;
}