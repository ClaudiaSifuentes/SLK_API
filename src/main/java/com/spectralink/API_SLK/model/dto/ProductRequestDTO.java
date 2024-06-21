package com.spectralink.API_SLK.model.dto;
import lombok.*;
import jakarta.validation.constraints.*;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {
    @NotBlank(message = "Nombre es obligatorio")
    private String nombre;

    @NotNull(message = "Precio es obligatorio")
    private int precio;

    @NotBlank(message = "Descripción es obligatoria")
    private String descripcion;

    @NotBlank(message = "Imagen es obligatoria")
    private String imagen;

    @NotBlank(message = "Stock es obligatori")
    private Integer stock;

    @NotNull(message = "ID de Orden es obligatorio")
    private int ordenId;
}
