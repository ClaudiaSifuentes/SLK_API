package com.spectralink.API_SLK.model.controller;
import com.spectralink.API_SLK.model.dto.OrderRequestDTO;
import com.spectralink.API_SLK.model.dto.OrderResponseDTO;
import com.spectralink.API_SLK.model.dto.StaffResponseDTO;
import com.spectralink.API_SLK.model.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public List<OrderResponseDTO>getAllOrders(){
        return orderService.getAllOrders();
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderByid(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getByOrderID(id));
    }

    @PostMapping
    public OrderResponseDTO createOrder (@RequestBody OrderRequestDTO orderRequestDTO){
        return orderService.createOrder(orderRequestDTO);
    }





}
