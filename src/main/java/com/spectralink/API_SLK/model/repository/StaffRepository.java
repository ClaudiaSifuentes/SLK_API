package com.spectralink.API_SLK.model.repository;
import com.spectralink.API_SLK.model.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;
public interface StaffRepository extends JpaRepository<Staff, Long> {

    @Query("SELECT u FROM Staff u WHERE u.email = :email")
    Optional<Staff> findByEmail(@Param("email") String email);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM Staff u WHERE u.email = :email")
    boolean existsByEmail(@Param("email") String email);

    @Query("SELECT u FROM Staff u WHERE u.email = :email AND u.dni = :dni")
    Optional<Staff> findByEmailAndDni(@Param("email") String email, @Param("dni") String dni);

    List<Staff> findAll();
    void deleteById(Long id);
}
