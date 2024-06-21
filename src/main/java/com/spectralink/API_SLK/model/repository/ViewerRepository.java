package com.spectralink.API_SLK.model.repository;
import com.spectralink.API_SLK.model.entities.Viewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;
public interface ViewerRepository extends JpaRepository<Viewer, Long> {

    @Query("SELECT u FROM Viewer u WHERE u.dni = :dni")
    Optional<Viewer> findByDni(@Param("dni") String dni);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM Viewer u WHERE u.dni = :dni")
    boolean existsByDni(@Param("dni") String dni);

    @Query("SELECT u FROM Viewer u WHERE u.dni = :dni AND u.codigoBoleto = :codigoBoleto")
    Optional<Viewer> findByDniAAndAndCodigoBoleto(@Param("dni") String dni, @Param("codigoBoleto") String codigoBoleto);

    List<Viewer> findAll();
    void deleteById(Long id);

}
