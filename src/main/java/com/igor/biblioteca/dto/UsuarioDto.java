package com.igor.biblioteca.dto;

import java.util.UUID;

import com.igor.biblioteca.model.UsuarioBiblioteca;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDto {

    private UUID id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato de email inválido")
    private String email;

    @NotBlank(message = "O CPF é obrigatório")
    private String cpf;

    public static UsuarioDto fromEntity(UsuarioBiblioteca usuario) {
        return UsuarioDto.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .cpf(usuario.getCpf())
                .build();
    }

    public UsuarioBiblioteca toEntity() {
        return UsuarioBiblioteca.builder()
                .id(this.id)
                .nome(this.nome)
                .email(this.email)
                .cpf(this.cpf)
                .build();
    }
}