package com.example.demo.repositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.example.demo.models.Autor;
import com.example.demo.models.Livro;



@Repository
interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByPrecoGreaterThan(Double valor);
    List<Livro> findByPrecoLessThanEqual(Double valor);
    List<Livro> findByTituloStartingWith(String titulo);
}

@SpringBootApplication
public class LivroApplication implements CommandLineRunner {
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public LivroApplication(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(LivroApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Autor autor1 = new Autor("Carlos Drummond");
        Autor autor2 = new Autor("Machado de Assis");
        
        autorRepository.save(autor1);
        autorRepository.save(autor2);

        livroRepository.save(new Livro("Java Avançado", 59.90,autor1));
        livroRepository.save(new Livro("Spring Boot", 89.90,autor2));
        livroRepository.save(new Livro("Hibernate", 49.90,autor2));

        System.out.println("Livros com preço maior que 50.00:");
        livroRepository.findByPrecoGreaterThan(50.00).forEach(System.out::println);

        System.out.println("Livros com preço menor ou igual a 50.00:");
        livroRepository.findByPrecoLessThanEqual(50.00).forEach(System.out::println);

        System.out.println("Livros que começam com 'Java':");
        livroRepository.findByTituloStartingWith("Java").forEach(System.out::println);

        System.out.println("Autores cujo nome começa com 'C':");
        autorRepository.findByNomeStartingWith("C").forEach(System.out::println);

        System.out.println("Autor com ID 2 e seus livros:");
        System.out.println(autorRepository.findByIdWithLivros(2L));
    }
}
