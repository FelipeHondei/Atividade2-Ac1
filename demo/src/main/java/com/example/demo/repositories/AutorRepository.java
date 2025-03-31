package com.example.demo.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Autor;

@Repository
interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByNomeStartingWith(String nome);
    
    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.livros WHERE a.id = :id")
    Autor findByIdWithLivros(Long id);
}