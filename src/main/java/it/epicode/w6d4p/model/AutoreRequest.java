package it.epicode.w6d4p.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
@Data
public class AutoreRequest {
    @NotNull(message = "Nome obbligatorio")
    @NotEmpty(message = "il campo del Nome non deve essere vuoto")
    private String nome;

    @NotNull(message = "Cognome obbligatorio")
    @NotEmpty(message = "il campo del Cognome non deve essere vuoto")
    private String cognome;

    @NotNull(message = "Email obbligatorio")
    @NotEmpty(message = "il campo dell' email non deve essere vuoto")
    @Column(unique = true)
    @Email(message = "Inserire email valida")
    private String email;

    @NotNull(message = "data nascita obbligatoria")
    private LocalDate dataNascita;
}
