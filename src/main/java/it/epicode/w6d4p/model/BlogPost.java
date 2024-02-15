package it.epicode.w6d4p.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String contenuto;
    private String titolo;
    private String cover;
    private String categoria;
    private int tempoLettura;

    @ManyToOne
    @JoinColumn(name = "autore_id")
    private Autore autore;

    public BlogPost(String contenuto, String titolo, String categoria, int tempoLettura, Autore autore) {
        this.contenuto = contenuto;
        this.titolo = titolo;
        this.categoria = categoria;
        this.tempoLettura = tempoLettura;
        this.cover ="https://picsum.photos/200/300";
        this.autore = autore;
    }

    public BlogPost(){

    }
}
