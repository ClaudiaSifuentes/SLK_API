package com.spectralink.API_SLK.model.mapper;

import com.spectralink.API_SLK.model.dto.ProductRequestDTO;
import com.spectralink.API_SLK.model.dto.ProductResponseDTO;
import com.spectralink.API_SLK.model.entities.Product;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductMapper {

    private final ModelMapper modelMapper;

    public Product convertToEntity(ProductRequestDTO productRequestDTO) {
        return modelMapper.map(productRequestDTO, Product.class);
    }

    public ProductResponseDTO convertToDTO(Product product) {
        return modelMapper.map(product, ProductResponseDTO.class);
    }

    public List<ProductResponseDTO> convertToDTO(List<Product> products) {
        return products.stream().map(this::convertToDTO).toList();
    }

}
