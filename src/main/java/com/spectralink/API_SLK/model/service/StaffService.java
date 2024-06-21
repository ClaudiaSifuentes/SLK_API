package com.spectralink.API_SLK.model.service;
import com.spectralink.API_SLK.model.dto.StaffResponseDTO;
import com.spectralink.API_SLK.model.dto.StaffValidationDTO;
import com.spectralink.API_SLK.model.entities.Staff;
import com.spectralink.API_SLK.model.exception.BadRequestException;
import com.spectralink.API_SLK.model.exception.ResourceNotFoundException;
import com.spectralink.API_SLK.model.mapper.StaffMapper;
import com.spectralink.API_SLK.model.repository.StaffRepository;
import lombok.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@AllArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;

    public List<StaffResponseDTO> getAllUser(){
        List<Staff> staff = staffRepository.findAll();
        return staffMapper.convertToDTO(staff);
    }

    public StaffResponseDTO getUserByEmail(String email){
        Staff staff = staffRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with email " + email));
         return staffMapper.convertToDTO(staff);
    }

    public boolean validateUserByDni(String email, String dni){
        Staff staff = staffRepository.findByEmailAndDni(email,dni)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with email "+email));

        return staff.getDni().equals(dni);
    }



    public boolean existsById(Long id){
        return staffRepository.existsById(id);
    }

    public boolean existsByEmail(String email){
        return staffRepository.existsByEmail(email);
    }

    public Staff getEntityById(Long id){
        return staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));
    }

    public StaffResponseDTO getStaffById(Long id){
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));
         return staffMapper.convertToDTO(staff);
    }

    public boolean validateUserByEmail(String email, String dni) {
        Optional<Staff> staffOptional = staffRepository.findByEmail(email);
            if (staffOptional.isPresent()) {
                Staff staff = staffOptional.get();
                return staff.getDni().equals(dni);
            } else {
                return false;
            }
    }

    public boolean validateUser(StaffValidationDTO staffValidationDTO) {
        boolean isValid;
        if(staffValidationDTO.getEmail() != null) {
            isValid = validateUserByEmail(staffValidationDTO.getEmail(), staffValidationDTO.getPassword());
        }
        else {
            throw new BadRequestException("email or password must be provided");
        }
        return isValid;
    }

}
