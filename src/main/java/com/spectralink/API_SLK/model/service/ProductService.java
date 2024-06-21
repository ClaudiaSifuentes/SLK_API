package com.spectralink.API_SLK.model.service;

import com.spectralink.API_SLK.model.dto.ProductResponseDTO;
import com.spectralink.API_SLK.model.dto.StaffResponseDTO;
import com.spectralink.API_SLK.model.dto.UpdateRequestDTO;
import com.spectralink.API_SLK.model.entities.Product;
import com.spectralink.API_SLK.model.entities.Staff;
import com.spectralink.API_SLK.model.exception.ResourceNotFoundException;
import com.spectralink.API_SLK.model.mapper.ProductMapper;
import com.spectralink.API_SLK.model.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Boolean existsByNombre(String nombre){
        return productRepository.existsByNombre(nombre);
    }

    public boolean existsById(Long id){
        return productRepository.existsById(id);
    }

    public List<ProductResponseDTO> getAllProduct(){
        List<Product> product = productRepository.findAll();
        return productMapper.convertToDTO(product);
    }


    public ProductResponseDTO getProductoById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id " + id));
         return productMapper.convertToDTO(product);
    }

     public Optional<Product> updateProducto(Long id, UpdateRequestDTO requestDTO) {
        return productRepository.findById(id).map(producto -> {
            producto.setStock(producto.getStock() - requestDTO.getCantidad());
            return productRepository.save(producto);
        });
    }

    public void updateProductoStocktwo(Long id, UpdateRequestDTO requestDTO) {
    productRepository.findById(id).ifPresent(producto -> {
        int newStock = producto.getStock() - requestDTO.getCantidad();
        producto.setStock(newStock);
        productRepository.save(producto);
    });
}




    public Optional<Product> findByNombre(String nombre) {
        return productRepository.findByNombre(nombre);
    }

    public List<Product> findByOrdenId(Long ordenId) {
        return productRepository.findByOrdenId(ordenId);
    }









    public ProductResponseDTO getProductById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return productMapper.convertToDTO(product);
    }

    public ProductResponseDTO getProductByNombre(String nombre){
        Product product = productRepository.findByNombre(nombre)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with name: " + nombre));
        return productMapper.convertToDTO(product);
    }

    public List<ProductResponseDTO> getProductByOrdenId(Long ordenId){
        List<Product> products = productRepository.findByOrdenId(ordenId);
        return productMapper.convertToDTO(products);
    }

    public ProductResponseDTO updateProduct(Product product){
        Product updatedProduct = productRepository.save(product);
        return productMapper.convertToDTO(updatedProduct);
    }




}
