package com.spectralink.API_SLK.model.mapper;

import com.spectralink.API_SLK.model.dto.ViewerRequestDTO;
import com.spectralink.API_SLK.model.dto.ViewerResponseDTO;
import com.spectralink.API_SLK.model.entities.Viewer;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ViewerMapper {
    private final ModelMapper modelMapper;

    public Viewer convertToEntity(ViewerRequestDTO viewerRequestDTO) {
        return modelMapper.map(viewerRequestDTO, Viewer.class);
    }

    public ViewerResponseDTO convertToDTO(Viewer viewers) {
        return modelMapper.map(viewers, ViewerResponseDTO.class);
    }

    public List<ViewerResponseDTO> convertToDTO(List<Viewer> viewers) {
        return viewers.stream().map(this::convertToDTO).toList();
    }
}
