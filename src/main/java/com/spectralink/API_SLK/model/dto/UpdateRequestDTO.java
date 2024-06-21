package com.spectralink.API_SLK.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRequestDTO {
    @NotBlank(message = "cantidad es obligatorio")
    private int cantidad;
}
