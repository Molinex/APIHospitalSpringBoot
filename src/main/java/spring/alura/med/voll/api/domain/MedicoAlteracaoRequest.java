package spring.alura.med.voll.api.domain;

import jakarta.validation.constraints.NotNull;

public record MedicoAlteracaoRequest(
        @NotNull
        Long id,
        String nome,
        String telefone,
        Endereco endereco) {
}
