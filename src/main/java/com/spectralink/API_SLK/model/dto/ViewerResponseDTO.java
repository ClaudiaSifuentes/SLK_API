package com.spectralink.API_SLK.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewerResponseDTO {

    private int id;
    private String dni;
    private String codigoBoleto;
    private String nombre;
    private String apellido;
    private String silla;
    private LocalDateTime fechaEvento;
}
