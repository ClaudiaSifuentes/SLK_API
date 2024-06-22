package com.spectralink.API_SLK.model.repository;

import com.spectralink.API_SLK.model.entities.Order;
import com.spectralink.API_SLK.model.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findById(Long id);
    List<Order> findAll();
    void deleteById(Long id);







}
