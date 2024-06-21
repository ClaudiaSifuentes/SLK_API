package com.spectralink.API_SLK.model.controller;

import com.spectralink.API_SLK.model.dto.StaffResponseDTO;
import com.spectralink.API_SLK.model.dto.StaffValidationDTO;
import com.spectralink.API_SLK.model.service.StaffService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
@AllArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @GetMapping
    public ResponseEntity<List<StaffResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(staffService.getAllUser());
    }

    @Transactional
    @GetMapping("/email/{email}")
    public ResponseEntity<StaffResponseDTO> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(staffService.getUserByEmail(email));
    }

    @Transactional
    @PostMapping("/login")
    public ResponseEntity<Boolean> validateUser(@RequestBody StaffValidationDTO staffValidationDTO) {
        boolean isValid = staffService.validateUser(staffValidationDTO);
        return ResponseEntity.ok(isValid);
    }


}
