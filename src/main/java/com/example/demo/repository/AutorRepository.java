package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Integer> {

	Optional<Autor> findByEmail(String email);

}
