package com.spectralink.API_SLK.model.service;

import com.spectralink.API_SLK.model.dto.ViewerResponseDTO;
import com.spectralink.API_SLK.model.dto.ViewerValidationDTO;
import com.spectralink.API_SLK.model.entities.Viewer;
import com.spectralink.API_SLK.model.exception.BadRequestException;
import com.spectralink.API_SLK.model.exception.ResourceNotFoundException;
import com.spectralink.API_SLK.model.mapper.ViewerMapper;
import com.spectralink.API_SLK.model.repository.ViewerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ViewerService {

    private final ViewerRepository viewerRepository;
    private final ViewerMapper viewerMapper;


    public List<ViewerResponseDTO> getAllUser(){
        List<Viewer> viewers = viewerRepository.findAll();
        return viewerMapper.convertToDTO(viewers);
    }

    public ViewerResponseDTO getUserByDni(String dni){
        Viewer viewer = viewerRepository.findByDni(dni)
                .orElseThrow(() -> new ResourceNotFoundException("Viewer not found with dni " + dni));
         return viewerMapper.convertToDTO(viewer);
    }

    public boolean validateUserByDni(String dni, String codigoBoleto){
        Viewer viewer = viewerRepository.findByDniAAndAndCodigoBoleto(dni, codigoBoleto)
                .orElseThrow(() -> new ResourceNotFoundException("Viewer not found with dni "+dni));

        return viewer.getDni().equals(dni);
    }

    public boolean existsById(Long id){
        return viewerRepository.existsById(id);
    }

    public boolean existsByEmail(String dni){
        return viewerRepository.existsByDni(dni);
    }

    public Viewer getEntityById(Long id){
        return viewerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viewer not found with id: " + id));
    }

    public ViewerResponseDTO getViewerById(Long id){
        Viewer Viewer = viewerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viewer not found with id: " + id));
         return viewerMapper.convertToDTO(Viewer);
    }

    public boolean validateUserByEmail(String dni, String codigoBoleto) {
        Optional<Viewer> ViewerOptional = viewerRepository.findByDni(dni);
            if (ViewerOptional.isPresent()) {
                Viewer Viewer = ViewerOptional.get();
                return Viewer.getCodigoBoleto().equals(codigoBoleto);
            } else {
                return false;
            }
    }

    public boolean validateUser(ViewerValidationDTO viewerValidationDTO) {
        boolean isValid;
        if(viewerValidationDTO.getDni() != null) {
            isValid = validateUserByEmail(viewerValidationDTO.getDni(), viewerValidationDTO.getCodigoBoleto());
        }
        else {
            throw new BadRequestException("dni or codigo boleto must be provided");
        }
        return isValid;
    }




}
