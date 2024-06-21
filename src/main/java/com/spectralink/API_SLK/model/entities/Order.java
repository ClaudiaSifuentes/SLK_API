package com.spectralink.API_SLK.model.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orden")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int cantidadProducto;

    @Column(nullable = false)
    private int totalProductos;

    @Column(nullable = false, length = 20)
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "Staff_id")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "Viewer_id")
    private Viewer viewer;

    @ElementCollection
    @CollectionTable(name = "orden_productos", joinColumns = @JoinColumn(name = "orden_id"))
    @MapKeyJoinColumn(name = "producto_id")
    @Column(name = "cantidad")
    private Map<Product, Integer> productos;
}
