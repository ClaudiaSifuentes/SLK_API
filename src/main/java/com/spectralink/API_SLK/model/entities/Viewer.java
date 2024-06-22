package com.spectralink.API_SLK.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Viewer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @Column(nullable = false, length = 50, unique = true)
    private String dni;

    @Column(nullable = false, length = 50)
    private String codigoBoleto;

    @Column(nullable = false, length = 50)
    private String silla;

    @Column(nullable = false)
    private LocalDateTime fechaEvento;
}
