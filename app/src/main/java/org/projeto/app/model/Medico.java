package org.projeto.app.model;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record Medico(
        @NotBlank(message = "O nome precisa ser fornecido!")
        String nome,

        @Email(message = "O email precisa ser válido!")
        String email,

        @NotBlank(message = "CRM precisa ser fornecido!")
        @Size(min = 6, max = 6)
        String CRM,

        Especialidade especialidade,

        @DecimalMin(value = "100", message = "O valor mínimo da consulta é R$ 100 reais!")
        @DecimalMax(value = "250", message = "O valor máximo da consulta não pode passar de R$ 250 reais!")
        BigDecimal valorConsulta

) {
}
