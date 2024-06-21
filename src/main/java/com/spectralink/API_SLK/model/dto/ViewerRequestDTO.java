package com.spectralink.API_SLK.model.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewerRequestDTO {
    @NotBlank(message = "DNI es obligatorio")
    @Size(min = 8, max = 20, message = "DNI debe tener entre 8 y 20 dígitos")
    private String dni;

    @NotBlank(message = "Código de boleto es obligatorio")
    @Size(min = 10, max = 20, message = "El código debe tener entre 10 y 20 dígitos")
    private String codigoBoleto;

    @NotBlank(message = "Nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "Apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "Silla es obligatoria")
    private String silla;

    @NotNull(message = "Fecha del evento es obligatoria")
    private LocalDateTime fechaEvento;
}
