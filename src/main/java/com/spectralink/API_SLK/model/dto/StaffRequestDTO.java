package com.spectralink.API_SLK.model.dto;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffRequestDTO {
    @NotBlank(message = "DNI es obligatorio")
    @Size(min = 8, max = 20, message = "DNI debe tener entre 8 y 20 dígitos")
    private String dni;

    @NotBlank(message = "Nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "Apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "Email es obligatorio")
    @Email(message = "Email debe ser válido")
    private String email;

    @NotBlank(message = "IP es obligatoria")
    private String ip;

    @NotNull(message = "Fecha es obligatoria")
    private LocalDateTime fecha;
}
