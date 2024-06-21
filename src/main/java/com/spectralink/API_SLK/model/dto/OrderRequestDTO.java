package com.spectralink.API_SLK.model.dto;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {

    @NotNull(message = "Cantidad de productos es obligatoria")
    private int cantidadProducto;

    @NotNull(message = "Total de productos es obligatorio")
    private int totalProductos;

    @NotNull(message = "Estado es obligatorio")
    private boolean estado;

    @NotNull(message = "ID del Staff es obligatorio")
    private Long staffId;

    @NotNull(message = "ID del Espectador es obligatorio")
    private Long viewer;

    @NotEmpty(message = "La lista de productos no puede estar vacía")
    private List<ProductRequestDTO> productos;
}
