package com.spectralink.API_SLK.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    private int precio;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false, length = 255)
    private String imagen;

    @Column(nullable = false)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "Orden_id", nullable = false)
    private Order orden;
}
