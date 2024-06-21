package com.spectralink.API_SLK.model.dto;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
    private int id;
    private String nombre;
    private int precio;
    private String descripcion;
    private String imagen;
    private Integer stock;
    private int ordenId;
}
