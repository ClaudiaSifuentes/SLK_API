package com.spectralink.API_SLK.model.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
    private Long id;
    private String nombre;
    private Integer precio;
    private String descripcion;
    private String imagen;
    private Integer stock;
    private Long ordenId;
}
