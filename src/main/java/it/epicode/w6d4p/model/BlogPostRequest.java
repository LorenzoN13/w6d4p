package it.epicode.w6d4p.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BlogPostRequest {
    @NotNull(message = "contenuto obbligatorio")
    @NotEmpty(message = "contenuto non vuoto")
    private String contenuto;

    @NotNull(message = "titolo obbligatorio")
    @NotEmpty(message = "titolo non vuoto")
    private String titolo;

    @NotNull(message = "categoria obbligatoria")
    @NotEmpty(message = "categoria non vuoto")
    private String categoria;

    private int tempoLettura;

    @NotNull(message = "Autore obbligatoria")
    private Integer idAutore;

}
