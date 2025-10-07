package com.igor.biblioteca.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.igor.biblioteca.dto.EmprestimoDto;
import com.igor.biblioteca.exception.ExemplarIndisponivelException;
import com.igor.biblioteca.model.Emprestimo;
import com.igor.biblioteca.model.ExemplarLivro;
import com.igor.biblioteca.model.StatusEmprestimo;
import com.igor.biblioteca.model.StatusLivro;
import com.igor.biblioteca.repository.EmprestimoRepository;
import com.igor.biblioteca.repository.ExemplarLivroRepository;
import com.igor.biblioteca.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmprestimoServiceImpl implements EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ExemplarLivroRepository exemplarRepository;

    @Override
    public EmprestimoDto salvar(EmprestimoDto dto) {
        var usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        Set<ExemplarLivro> exemplares = exemplarRepository.findAllById(dto.getExemplaresIds())
                .stream().collect(Collectors.toSet());

        // Verifica se todos os exemplares existem (tamanho)
        if (exemplares.size() != dto.getExemplaresIds().size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Um ou mais exemplares informados não foram encontrados.");
        }

        // Verifica disponibilidade
        if (exemplares.stream().anyMatch(e -> e.getStatus() != StatusLivro.DISPONIVEL)) {
            throw new ExemplarIndisponivelException("Alguns exemplares não estão disponíveis para empréstimo.");
        }

        // marca como emprestado e salva exemplares
        exemplares.forEach(e -> e.setStatus(StatusLivro.EMPRESTADO));
        exemplarRepository.saveAll(exemplares);

        Emprestimo emprestimo = Emprestimo.builder()
                .usuario(usuario)
                .exemplares(exemplares)
                .dataHoraEmprestimo(LocalDateTime.now())
                .status(StatusEmprestimo.ATIVO)
                .build();

        return EmprestimoDto.fromEntity(emprestimoRepository.save(emprestimo));
    }

    @Override
    public EmprestimoDto atualizar(UUID id, EmprestimoDto dto) {
        var emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empréstimo não encontrado"));

        if (dto.getDataHoraDevolucao() != null) {
            emprestimo.setDataHoraDevolucao(dto.getDataHoraDevolucao());
            emprestimo.setStatus(StatusEmprestimo.FINALIZADO);

            Set<ExemplarLivro> exemplaresDevolvidos = emprestimo.getExemplares();
            exemplaresDevolvidos.forEach(e -> e.setStatus(StatusLivro.DISPONIVEL));
            exemplarRepository.saveAll(exemplaresDevolvidos);
        } else {
            emprestimo.setDataHoraDevolucao(dto.getDataHoraDevolucao());
        }

        return EmprestimoDto.fromEntity(emprestimoRepository.save(emprestimo));
    }

    @Override
    public void deletar(UUID id) {
        emprestimoRepository.deleteById(id);
    }

    @Override
    public EmprestimoDto buscarPorId(UUID id) {
        return emprestimoRepository.findById(id)
                .map(EmprestimoDto::fromEntity)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empréstimo não encontrado"));
    }

    @Override
    public List<EmprestimoDto> listarTodos() {
        return emprestimoRepository.findAll().stream()
                .map(EmprestimoDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmprestimoDto> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return emprestimoRepository.findByDataHoraEmprestimoBetween(inicio, fim)
                .stream().map(EmprestimoDto::fromEntity).collect(Collectors.toList());
    }
}