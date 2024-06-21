package com.spectralink.API_SLK.model.dto;
import lombok.*;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewerValidationDTO {
    @NotBlank(message = "DNI es obligatorio")
    @Size(min = 8, max = 20, message = "DNI debe tener entre 8 y 20 dígitos")
    private String dni;

    @NotBlank(message = "codigoBoleto es obligatorio")
    private String codigoBoleto;
}
