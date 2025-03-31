package com.example.demo.models;
import jakarta.persistence.*;

@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Livro() {}

    public Livro(String titulo, Double preco, Autor autor) {
        this.titulo = titulo;
        this.preco = preco;
        this.autor = autor;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }


    @Override
    public String toString() {
        return "Livro{id=" + id + ", titulo='" + titulo + "', preco=" + preco + ", autor=" + autor.getNome() + "}";
    }
}