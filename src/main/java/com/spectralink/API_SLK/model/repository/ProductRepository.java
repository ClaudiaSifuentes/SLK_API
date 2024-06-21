package com.spectralink.API_SLK.model.repository;

import com.spectralink.API_SLK.model.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();
    Optional<Product> findById(Long id);
    void deleteById(Long id);
    boolean existsById(Long id);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM Product p WHERE p.nombre = :nombre")
    boolean existsByNombre(@Param("nombre") String nombre);

    @Query("SELECT p FROM Product p WHERE p.nombre = :nombre")
    Optional<Product> findByNombre(@Param("nombre") String nombre);

    @Query("SELECT p FROM Product p JOIN Order o WHERE o.id = :ordenId")
    List<Product> findByOrdenId(@Param("ordenId") Long ordenId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Product p WHERE p.orden.id = :ordenId")
    void deleteByOrdenId(@Param("ordenId") Long ordenId);
}
