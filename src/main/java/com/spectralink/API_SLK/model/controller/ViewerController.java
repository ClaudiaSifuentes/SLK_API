package com.spectralink.API_SLK.model.controller;
import com.spectralink.API_SLK.model.dto.ViewerResponseDTO;
import com.spectralink.API_SLK.model.dto.ViewerValidationDTO;
import com.spectralink.API_SLK.model.service.ViewerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class ViewerController {

    private final ViewerService viewerService;

    @GetMapping
    public ResponseEntity<List<ViewerResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(viewerService.getAllUser());
    }

    @Transactional
    @GetMapping("/dni/{dni}")
    public ResponseEntity<ViewerResponseDTO> getUserByDni(@PathVariable String dni) {
        return ResponseEntity.ok(viewerService.getUserByDni(dni));
    }

    @Transactional
    @PostMapping("/login")
    public ResponseEntity<Boolean> validateUser(@RequestBody ViewerValidationDTO viewerValidationDTO) {
        boolean isValid = viewerService.validateUser(viewerValidationDTO);
        return ResponseEntity.ok(isValid);
    }


}
