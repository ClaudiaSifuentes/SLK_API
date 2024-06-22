package com.spectralink.API_SLK.model.dto;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private Long id;
    private Integer cantidadProducto;
    private Integer totalProductos;
    private Boolean estado;
    private Long staffId;
    private Long viewerId;
    private List<ProductResponseDTO> productos;
}
