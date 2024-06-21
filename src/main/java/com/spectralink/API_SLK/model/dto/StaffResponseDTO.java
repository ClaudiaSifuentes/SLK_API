package com.spectralink.API_SLK.model.dto;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffResponseDTO {

    private int id;
    private String dni;
    private String nombre;
    private String apellido;
    private String email;
    private String ip;
    private LocalDateTime fecha;
}
