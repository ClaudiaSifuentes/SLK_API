package com.spectralink.API_SLK.model.mapper;

import com.spectralink.API_SLK.model.dto.StaffRequestDTO;
import com.spectralink.API_SLK.model.dto.StaffResponseDTO;
import com.spectralink.API_SLK.model.entities.Staff;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class StaffMapper {

    private final ModelMapper modelMapper;

    public Staff convertToEntity(StaffRequestDTO staffRequestDTO) {
        return modelMapper.map(staffRequestDTO, Staff.class);
    }

    public StaffResponseDTO convertToDTO(Staff staff) {
        return modelMapper.map(staff, StaffResponseDTO.class);
    }

    public List<StaffResponseDTO> convertToDTO(List<Staff> staff) {
        return staff.stream().map(this::convertToDTO).toList();
    }

}
