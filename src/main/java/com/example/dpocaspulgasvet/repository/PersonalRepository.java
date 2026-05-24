package com.example.dpocaspulgasvet.repository;


import com.example.dpocaspulgasvet.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PersonalRepository extends JpaRepository<Personal, Integer> {
    @Query("SELECT p FROM Personal p WHERE p.nombres LIKE %:search% OR p.apPaterno LIKE %:search% OR p.nroDoc LIKE %:search%")
    List<Personal> findByNombreOrDni(String search);
}